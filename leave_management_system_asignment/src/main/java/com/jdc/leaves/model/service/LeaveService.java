package com.jdc.leaves.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.leaves.model.dto.input.LeaveForm;
import com.jdc.leaves.model.dto.output.LeaveListVO;
import com.jdc.leaves.model.dto.output.LeaveSummaryVO;

@Service
public class LeaveService {

	private SimpleJdbcInsert leavesInsert;
	private SimpleJdbcInsert dayInsert;
	private NamedParameterJdbcTemplate template;

	@Autowired
	private ClassService classService;

	private final String SELECT_PROJECTION = """
			SELECT DISTINCT l.apply_date applyDate, l.classes_id classId, l.student_id studentId, l.start_date startDate,
			l.days, l.reason, sa.name student, s.phone studentPhone, c.teacher_id teacherId, at.name teacher,
			c.start_date classStart, c.description classInfo
			FROM leaves l JOIN classes c ON l.classes_id = c.id
			JOIN teacher t ON c.teacher_id = t.id
			JOIN account at ON t.id = at.id
			JOIN student s ON l.student_id = s.id
			JOIN account sa ON s.id = sa.id
			JOIN leaves_day ld ON
			l.apply_date = ld.leaves_apply_date and l.classes_id = ld.leaves_classes_id and
			l.student_id = ld.leaves_student_id
			WHERE 1 = 1
			""";

	public LeaveService(DataSource dataSource) {

		template = new NamedParameterJdbcTemplate(dataSource);

		leavesInsert = new SimpleJdbcInsert(dataSource);
		leavesInsert.setTableName("leaves");

		dayInsert = new SimpleJdbcInsert(dataSource);
		dayInsert.setTableName("leaves_day");

	}

	// Search Process
	public List<LeaveListVO> search(Optional<Integer> classId, Optional<LocalDate> from,
			Optional<LocalDate> to) {

		var where = new StringBuffer();
		var params = new HashMap<String, Object>();
		
		//Authentication Process
		var auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.isAuthenticated() && auth.getAuthorities().contains(authority("Student"))) {
			
			if(auth instanceof UsernamePasswordAuthenticationToken token) {
				where.append(" AND sa.email = :username");
				params.put("username", token.getName());
			}
		}

		where.append(classId.filter(a -> a != null && a > 0).map(a -> {
			params.put("classId", a);
			return " AND classes_id =:classId";
		}).orElse(""));

	
		where.append(from.filter(a -> a != null).map(a -> {
			params.put("from", Date.valueOf(a));
			return " AND ld.leaves_apply_date >= :from ";
		}).orElse(""));

		where.append(to.filter(a -> a != null).map(a -> {
			params.put("to", Date.valueOf(a));
			return " AND ld.leaves_apply_date <= :to";
		}).orElse(""));

		final String SEARCH_SQL = "%s %s".formatted(SELECT_PROJECTION, where.toString());

		return template.query(SEARCH_SQL, params, new BeanPropertyRowMapper<>(LeaveListVO.class));
	}

	// Save Form
	@Transactional
	public int save(LeaveForm form) {
		// Insert into Leaves Process
		var params = new HashMap<String, Object>();
		params.put("classes_id", form.getClassId());
		params.put("student_id", form.getStudentId());
		params.put("apply_date", Date.valueOf(form.getApplyDate()));
		params.put("start_date", Date.valueOf(form.getStartDate()));
		params.put("days", form.getDays());
		params.put("reason", form.getReason());
		var leaveResult = leavesInsert.execute(params);

		// Insert Into Leave Days
		for (var result : form.leavesDaysInsert()) {
			dayInsert.execute(result);
		}

		return leaveResult;
	}

	// Search Summary Process
	public List<LeaveSummaryVO> searchSummary(Optional<LocalDate> target) {
		//For Class
		var classData = classService.search(Optional.ofNullable(null), Optional.ofNullable(null),
						Optional.ofNullable(null));
	  //For result data
	   var list = classData.stream().map(LeaveSummaryVO::new).toList();
	  
	   for(var vo : list) {
		   var leaves  = findLeaveForClass(vo.getClassId(),target.orElse(LocalDate.now()));
		   vo.setLeaves(leaves);
	   }
				
		return list;

	}

	// Find Leave For Class
	public long findLeaveForClass(int classId, LocalDate date) {
		final String LEAVES_COUNT = """
				SELECT COUNT(leave_date) FROM leaves_day WHERE
				leave_date =:leaveDate AND leaves_classes_id =:classId
				""";
		var params = new HashMap<String, Object>();
		params.put("leaveDate", Date.valueOf(date));
		params.put("classId", classId);

		return template.queryForObject(LEAVES_COUNT, params, Long.class);
	}
	
	private GrantedAuthority authority(String role) {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(role).get(0);
	}

}
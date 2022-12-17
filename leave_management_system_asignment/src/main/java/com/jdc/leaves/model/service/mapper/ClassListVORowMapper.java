package com.jdc.leaves.model.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jdc.leaves.model.dto.output.ClassListVO;

public class ClassListVORowMapper implements RowMapper<ClassListVO> {

	@Override
	public ClassListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		var vo = new ClassListVO();
		vo.setId(rs.getInt("id"));
		vo.setTeacherName(rs.getString("teacherName"));
		vo.setTeacherId(rs.getInt("teacherId"));
		vo.setTeacherPhone(rs.getString("teacherPhone"));
		vo.setStartDate(rs.getDate("startDate").toLocalDate());
		vo.setMonths(rs.getInt("months"));
		vo.setDescription(rs.getString("description"));
		vo.setStudentCount(rs.getLong("studentCount"));

		return vo;
	}

}

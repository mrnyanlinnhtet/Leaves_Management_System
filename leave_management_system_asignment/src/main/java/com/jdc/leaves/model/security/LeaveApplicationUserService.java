package com.jdc.leaves.model.security;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LeaveApplicationUserService implements UserDetailsService {

	private NamedParameterJdbcTemplate template;

	public LeaveApplicationUserService(DataSource dataSource) {

		template = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final String SQL = "SELECT * FROM account WHERE email =:email";
		var param = new HashMap<String,Object>();
		param.put("email", username);
		
		var list = template.query(SQL,param,new BeanPropertyRowMapper<>(AccountDao.class));
		
		if(!list.isEmpty()) {
			
			var account = list.get(0);
			
			return User.builder()
					   .username(account.getEmail())
					   .password(account.getPassword())
					   .authorities(account.getRole())
					   .accountExpired(account.isDeleted())
					   .build();
		}
		 
          throw new UsernameNotFoundException(username + "Not Found !");
	}

}
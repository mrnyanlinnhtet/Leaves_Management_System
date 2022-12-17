package com.jdc.leaves.model.security;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class AdminUserInitializer {

	private DataSource dataSource;

	@Autowired
	private PasswordEncoder encoder;

	public AdminUserInitializer(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@EventListener(classes = ContextRefreshedEvent.class)
	void initAdminUser() {
		if (isHasAdminAccount()) {
			// Create Admin Account
			createAdminAccount();
		}

	}

	private boolean isHasAdminAccount() {
		var template = new NamedParameterJdbcTemplate(dataSource);
		var SQL = "SELECT COUNT(id) FROM account WHERE role = :role";
		var param = new HashMap<String, Object>();
		param.put("role", "Admin");

		var hasAccount = template.queryForObject(SQL, param, Long.class);
		return hasAccount == 0;
	}

	@Transactional
	private void createAdminAccount() {
		var insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("account");
		insert.setGeneratedKeyName("id");
		insert.setColumnNames(List.of("name", "role", "email", "password"));

		var params = new HashMap<String, Object>();
		params.put("name", "admin_user");
		params.put("role", "Admin");
		params.put("email", "admin@gmail.com");
		params.put("password", encoder.encode("adminpwd"));

		insert.execute(params);

	}

}

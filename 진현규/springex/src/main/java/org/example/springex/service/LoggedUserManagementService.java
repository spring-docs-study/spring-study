package org.example.springex.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope // 빈의 스코프를 세션으로 변경한다.
@Service
public class LoggedUserManagementService {
	private String username;

	public String getUsername () {
		return username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

}

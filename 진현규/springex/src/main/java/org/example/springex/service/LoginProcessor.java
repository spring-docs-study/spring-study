package org.example.springex.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component // 빈을 요청 스코프로 지정한다, 즉 스프링은 HTTP 요청마다 새로운 인스턴스를 생성한다.
public class LoginProcessor {

	private final LoggedUserManagementService loggedUserManagementService;
	private final LoginCountService loginCountService;

	public LoginProcessor (LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
		this.loggedUserManagementService = loggedUserManagementService;
		this.loginCountService = loginCountService;
	}

	private String username;
	private String password;

	public boolean login() {
		loginCountService.increase();

		String username = this.getUsername();
		String password = this.getPassword();

		System.out.println(username);
		System.out.println(password);

		boolean loginResult = false;

		if("kyu".equals(username) && "password".equals(password)) {

			loginResult = true;
			loggedUserManagementService.setUsername(username); // 사용자 로그인 자격 증명이 올바르다면 이름을 세션 스코프에 저장한다.
		}

		return loginResult;
	}

	public String getUsername () {
		return username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

}

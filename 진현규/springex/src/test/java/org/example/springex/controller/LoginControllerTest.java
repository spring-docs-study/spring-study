package org.example.springex.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.example.springex.service.LoginProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
	@Mock
	private Model model;

	@Mock
	private LoginProcessor loginProcessor;

	@InjectMocks
	private LoginController loginController;
	// 생성자 주입으로 만 mocks 를 주입하려고 시도한다.

	@Test
	@DisplayName("")
	void LoginControllerTest() {
	    // given
		BDDMockito.given(loginProcessor.login()).willReturn(true);

		String result = loginController.loginPost("username","password",model);

		Assertions.assertEquals("login.html",result);

		BDDMockito.verify(model).addAttribute("message","u are now logged");

	    // then
	}


}
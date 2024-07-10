package org.example.springex.service;


import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope // 애플리케이션 스코프에 대한 빈 스코프를 정의한다
@Service
public class LoginCountService {
	private int count;

	public void increase() {
		this.count++;
	}

	public int getCount () {
		return this.count;
	}

}

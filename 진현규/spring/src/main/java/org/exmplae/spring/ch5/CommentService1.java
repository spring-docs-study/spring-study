package org.exmplae.spring.ch5;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy // 누군가 처음 이 빈을 참조할 때만 스프링에 빈을 생성하도록 지시한다.
@Service
public class CommentService1 {

	public CommentService1 () {
		System.out.println("CommentService1 instance created!");
	}

}

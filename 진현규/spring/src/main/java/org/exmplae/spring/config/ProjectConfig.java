package org.exmplae.spring.config;

import org.exmplae.spring.ch1.Parrot;
import org.exmplae.spring.ch1.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("org.exmplae.spring.*")
@Configuration
public class ProjectConfig {

	@Bean
	public Parrot parrot1() {
		var p = new Parrot();
		p.setName("parrot1");
		return p;
	}

	@Bean
	public Parrot parrot2() {
		var p = new Parrot();
		p.setName("parrot2");
		return p;
	}


	@Bean
	public Person person(@Qualifier("parrot2") Parrot parrot) {
		Person p = new Person(parrot1());
		p.setName("Jin Hyeon kyu");
		return p;
	}

/*	@Bean
	String Hello() {
		return "Hello";
	}

	@Bean
	Integer ten() {
		return 10;
	}*/
}

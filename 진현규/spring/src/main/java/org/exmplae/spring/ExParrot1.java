package org.exmplae.spring;

import org.springframework.stereotype.Component;

@Component
public class ExParrot1 {
	private String name;

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

}

package org.exmplae.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Parrot {
	private String name = "jin";

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

}

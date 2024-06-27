package org.exmplae.spring.ch1;

import org.exmplae.spring.ch6.ProjectConfig6;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main (String[] args) throws Exception {
			var context = new AnnotationConfigApplicationContext(ProjectConfig6.class);
			Parrot p = context.getBean(Parrot.class);
			System.out.println(p.getName());

		System.out.println();
			// context.registerBean();

		Person person = context.getBean(Person.class);
		Parrot parrot = context.getBean(Parrot.class);
		System.out.println("parrot = " + parrot.getName());
		System.out.println("person = " + person.getName());
		System.out.println("person's parrot = " + person.getParrot());

		System.out.println();

		System.out.println("parrot = " + parrot.getName());
		System.out.println("person = " + person.getName());
	}
}

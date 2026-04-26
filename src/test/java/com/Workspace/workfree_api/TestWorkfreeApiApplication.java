package com.Workspace.workfree_api;

import org.springframework.boot.SpringApplication;

public class TestWorkfreeApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(WorkfreeApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

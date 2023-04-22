package com.assignment.busstops;

import static org.assertj.core.api.Assertions.assertThat;

import com.assignment.busstops.controller.ApiController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//Simple test class to just see that the context loads.
@SpringBootTest
class BusstopsApplicationTests {
	@Autowired
	private ApiController controller;

	@Test
	void contextLoads() {assertThat(controller).isNotNull();}
}

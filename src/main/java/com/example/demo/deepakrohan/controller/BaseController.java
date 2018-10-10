package com.example.demo.deepakrohan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BaseController {

	@RequestMapping("/")
	public String intialPageOutput() {
		return "Hello World";
	}
	

}

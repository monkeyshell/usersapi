package com.users.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.users.pojo.AuthResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public AuthResponse authorizeUser(){
		LOGGER.info("Authorizing user request");
		AuthResponse ar = new AuthResponse();
		ar.setAnyError(false);
		ar.setStatus("OK");
		return ar;
	}
}

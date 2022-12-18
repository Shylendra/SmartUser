package com.smartapps.smartuser.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HealthCheckController {

	@GetMapping("/")
	public ResponseEntity<Void> health(){
		System.out.println("HealthCheckController::health()::OK");
		log.info("HealthCheckController::health()::OK");
		return ResponseEntity.ok().build();
	}
	
}

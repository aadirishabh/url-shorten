package com.cybrilla.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybrilla.app.service.I_Service;
import com.cybrilla.app.utils.ControllerConstants;

@RestController
@RequestMapping(value = ControllerConstants.API)
public class UrlRedirectionController{
	
	Logger logger = LoggerFactory.getLogger(UrlRedirectionController.class);
	
	@Autowired
	private I_Service service;

	@PostMapping (value = ControllerConstants.SHORTEN_URL)
	public ResponseEntity<String> shortenUrl(@RequestBody String url) {
		logger.info("Shortening url: {}", url);
		String shortUrl = service.shortenUrl(url);
		return ResponseEntity.ok(shortUrl);
	}
	
	@GetMapping (value = ControllerConstants.SHORT_URL)
	public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) {
		logger.info("Return original url for : {}", shortUrl);
		String url = service.getOriginalUrl(shortUrl);
		return ResponseEntity.ok(url);
	}
	
}

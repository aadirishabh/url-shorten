package com.cybrilla.app.service;

import java.util.Objects;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybrilla.app.controller.UrlRedirectionController;
import com.cybrilla.app.dao.URLRepository;
import com.cybrilla.app.entity.URL;
import com.cybrilla.app.utils.AppUtils;

@Service
public class URLService implements I_Service{
	
	Logger logger = LoggerFactory.getLogger(URLService.class);
	
	@Autowired
	private URLRepository repository;
	
	@Autowired
	private AppUtils util;

	@Override
	public String shortenUrl(String url) {
		final UrlValidator urlValidator = new UrlValidator();
		String shortUrl = null;
        if (!urlValidator.isValid(url)) {
        	logger.error("Invalid url : {}", url);
            return new String("Invalid URL.");  
        }
        
        try {
        	URL urlEntity = repository.findURLByLongUrl(url);
            if (Objects.nonNull(urlEntity)) {
            	logger.info("Short url already exists in database for : {}", url);
            	shortUrl = urlEntity.getShortUrl();
            } else {
            	logger.info("Generating short url for : {}", url);
            	shortUrl = util.getAlphaNumericString();
            	logger.info("Generated short url for : {}", url);
                urlEntity = new URL(url, shortUrl);
                repository.save(urlEntity);
            }
        } catch(Exception e) {
        	logger.error("Error occurred in generating short url : {}", e.getMessage());
        	return null;
        }
        
		return shortUrl;
	}

	@Override
	public String getOriginalUrl(String shortUrl) {
		String originalUrl = null;
		try {
			URL url = repository.findURLByShortUrl(shortUrl);
			if (Objects.nonNull(url)) {
				logger.info("Original url found in database for : {}", shortUrl);
				originalUrl = url.getLongUrl();
			} else {
				logger.error("Original url not found in database for : {}", shortUrl);
			}
		} catch (Exception e) {
			logger.error("Error occurred in getting original url : {}", e.getMessage());
			return null;
		}
		
		return originalUrl;
	}

}

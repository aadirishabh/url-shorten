package com.cybrilla.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybrilla.app.entity.URL;

public interface URLRepository extends JpaRepository<URL, Integer> {
	
	URL findURLByShortUrl(String shortUrl);

	URL findURLByLongUrl(String url);

}

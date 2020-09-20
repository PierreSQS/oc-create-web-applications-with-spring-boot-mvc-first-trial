package com.openclassrooms.watchlist.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MovieRatingService {

	private final Logger log = LoggerFactory.getLogger(MovieRatingService.class);
	
	String apiUrl = "http://www.omdbapi.com/?apikey=104c4ee4&t=";

	public Optional<String> getMovieRating(String title) {
		try {
			
			RestTemplate restTmp = new RestTemplate();

			ResponseEntity<String> response = restTmp.getForEntity(apiUrl+title, String.class);
			log.info("OMDBAPI called for URL {}",apiUrl+title);
			JsonNode jsonNode = new ObjectMapper().readTree(response.getBody()).path("imdbRating");
//			log.debug("OMDBAPI Response: {}", response);
			log.debug("jsonNode \"imdbRating\": {}", jsonNode);
			if (jsonNode.isMissingNode()) {
				log.warn("imdbRating node is missing, returning empty String.");
			}
			return Optional.ofNullable(jsonNode.asText());
		} catch (Exception e) {
			log.warn("Error while calling OMDb API" + e.getMessage());
			return Optional.of("");

		}
	}
}

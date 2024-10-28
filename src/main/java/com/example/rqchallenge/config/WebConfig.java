package com.example.rqchallenge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class WebConfig {

	// Inject property from application.properties
	@Value("${api.base.url}")
	private String apiBaseUrl;

	private final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Bean
	WebClient webClient() {

		// Create HttpHeaders object and set multiple headers
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.COOKIE, "humans_21909=1");

		WebClient webClient = WebClient.builder().baseUrl(apiBaseUrl).defaultHeaders(h -> h.addAll(headers))
				.filters(f -> {
					f.add(logRequest());
					f.add(logResponse());
				}).build();

		return webClient;
	}

	// This method returns filter function which will log request data
	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			logger.trace("Request: {} {}", clientRequest.method(), clientRequest.url());
			clientRequest.headers()
					.forEach((name, values) -> values.forEach(value -> logger.trace("{}={}", name, value)));
			return Mono.just(clientRequest);
		});
	}

	// This method returns filter function which will log response data
	private ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			logger.trace("Response status: {}", clientResponse.statusCode());
			clientResponse.headers().asHttpHeaders()
					.forEach((name, values) -> values.forEach(value -> logger.trace("{}={}", name, value)));
			return Mono.just(clientResponse);
		});
	}

}

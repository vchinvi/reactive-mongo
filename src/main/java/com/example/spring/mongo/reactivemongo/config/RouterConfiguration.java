package com.example.spring.mongo.reactivemongo.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.spring.mongo.reactivemongo.handler.ControllerHandler;

@Configuration
public class RouterConfiguration {
	 @Bean
	 public RouterFunction<ServerResponse> route(ControllerHandler personHandler) {
	        return RouterFunctions.route(POST("/sample/book"), personHandler::saveCourceBookCatelogue2)
	        		.andRoute(POST("/sample/book2"), personHandler::saveCourceBookCatelogue3)
	        		.andRoute(GET("/sample/book"), personHandler::getCourceBookCatelogue)
	        		.andRoute(GET("/sample/book2"), personHandler::getCourceBookCatelogue2);
	        
	        		//.andRoute(POST("/sample/bookdetail"), personHandler::saveBookDetail);
	        
	 }
}

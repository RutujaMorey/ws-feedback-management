//package com.feedback.management.configuration;
//
//
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//@Configuration
//public class JacksonDataFormatConfiguration {
//
//	@Bean
//	public JacksonDataFormat jacksonDataFormat(ObjectMapper objectMapper) {
//		objectMapper.registerModule(new JavaTimeModule());
//		JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
//		jacksonDataFormat.setObjectMapper(objectMapper);
//		return jacksonDataFormat;
//	}
//}

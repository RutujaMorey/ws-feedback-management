package com.feedback.management;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;


	
	public class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

	    @Override 
	    public LocalDateTime convert(Date source) { 
	        return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault()); 
	    }

		@Override
		public JavaType getInputType(TypeFactory arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public JavaType getOutputType(TypeFactory arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	}



package com.atomikos.jaxrs;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

@Provider
public class ISO8601DateFormatOMContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ISO8601DateFormatOMContextResolver() {
        mapper = new ObjectMapper();
    	mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
		mapper.setDateFormat(new ISO8601DateFormat());
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}
package com.atomikos.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(new ISO8601DateFormatOMContextResolver());
		register(FlightController.class);
	}

}
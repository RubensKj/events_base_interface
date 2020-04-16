package com.rubenskj.core.servicesconfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
//@ConfigurationProperties(prefix = "hbsis.services")
public class ServicesConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesConfiguration.class);

    private Map<String, String> events = new HashMap<>();

    public Map<String, String> getEvents() {
        return events;
    }

    public void setEvents(Map<String, String> events) {
        this.events = events;
    }
}

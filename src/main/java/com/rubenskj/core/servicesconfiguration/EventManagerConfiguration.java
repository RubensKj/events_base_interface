package com.rubenskj.core.servicesconfiguration;

import com.rubenskj.core.interfaces.IEventManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EventManagerConfiguration {

    private final ServicesConfiguration servicesConfiguration;

    public EventManagerConfiguration(ServicesConfiguration servicesConfiguration) {
        this.servicesConfiguration = servicesConfiguration;
    }

    @Bean
    public IEventManager getEventManager() {
        return EventManagerInitializer.initilize(servicesConfiguration);
    }
}

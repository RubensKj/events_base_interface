package com.rubenskj.core.services;

import com.rubenskj.core.interfaces.IEvent;
import com.rubenskj.core.interfaces.IEventManager;
import org.springframework.stereotype.Service;

@Service
public class HandlerAllEventsService {

    private final IEventManager eventManager;

    public HandlerAllEventsService(IEventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void handleEvent(String eventName, String handlerName, IEvent event) throws Exception {
        this.eventManager.handle(eventName, handlerName, event);
    }
}

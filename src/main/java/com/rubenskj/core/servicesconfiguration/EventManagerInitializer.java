package com.rubenskj.core.servicesconfiguration;

import com.rubenskj.core.interfaces.ICallbackEvent;
import com.rubenskj.core.interfaces.IEvent;
import com.rubenskj.core.interfaces.IEventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventManagerInitializer implements IEventManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManagerInitializer.class);

    private static EventManagerInitializer instance;

    private final ServicesConfiguration servicesConfiguration;

    private Map<String, HandlerCallback> eventHandlerCallback = new HashMap<>();
    private Map<String, ExecutorService> queues = new ConcurrentHashMap<>();

    public EventManagerInitializer(ServicesConfiguration servicesConfiguration) {
        this.servicesConfiguration = servicesConfiguration;
    }

    public static EventManagerInitializer initilize(ServicesConfiguration servicesConfiguration) {
        if (instance == null) {
            instance = new EventManagerInitializer(servicesConfiguration);
        }

        return instance;
    }

    @Override
    public void handle(String name, String handlerName, IEvent event) throws Exception {
        HandlerCallback handlerCallback = eventHandlerCallback.get(name);

        if (handlerName != null && !handlerCallback.handler.equals(handlerName)) {
            return;
        }

        final String key = getKey(name, handlerCallback.handler);
        final Runnable task = () -> {
            try {
                handlerCallback.callback.handle(event);
            } catch (Exception e) {
                LOGGER.error("Error processing event -> ", e);
            }
        };

        final ExecutorService executorService = queues.get(key);
        executorService.submit(task);
    }

    @Override
    public void register(String eventName, String handlerName, ICallbackEvent callback) {
        String key = getKey(eventName, handlerName);
        queues.put(key, Executors.newFixedThreadPool(2));
        eventHandlerCallback.put(eventName, new HandlerCallback(handlerName, callback));
    }

    private static class HandlerCallback {
        private String handler;
        private ICallbackEvent callback;

        public HandlerCallback(String handler, ICallbackEvent callback) {
            this.handler = handler;
            this.callback = callback;
        }
    }

    private String getKey(String eventName, String handlerName) {
        return eventName.concat("-").concat(handlerName);
    }
}

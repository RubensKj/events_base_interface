package com.rubenskj.core.interfaces;

public interface IEventManager {

    void handle(String name, String handlerName, IEvent event) throws Exception;

    void register(String eventName, String handlerName, ICallbackEvent callback);

}

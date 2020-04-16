package com.rubenskj.core.interfaces;

@FunctionalInterface
public interface ICallbackEvent {

    void handle(IEvent event) throws Exception;
}

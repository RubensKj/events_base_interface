package com.rubenskj.core.services;

import com.rubenskj.core.entities.XmlEntity;
import com.rubenskj.core.enuns.EnumEventType;
import com.rubenskj.core.events.XmlReceivedEvent;
import com.rubenskj.core.interfaces.IEvent;
import com.rubenskj.core.interfaces.IEventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class XmlReceivedEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlReceivedEventService.class);

    private static final String SERVICE_NAME = XmlReceivedEventService.class.getName();

    private final IEventManager eventManager;
    private final XmlService xmlService;

    @Autowired
    public XmlReceivedEventService(IEventManager eventManager, XmlService xmlService) {
        this.eventManager = eventManager;
        this.xmlService = xmlService;
        this.registerEvents();
    }

    private void registerEvents() {
        this.eventManager.register(EnumEventType.XML_RECEIVED.name(), SERVICE_NAME, this::receiveEventXmlReceived);
    }

    public void receiveEventXmlReceived(IEvent event) {
        LOGGER.info("Recebendo Evento -> {}", EnumEventType.XML_RECEIVED.name());

        XmlReceivedEvent xmlReceivedEvent = (XmlReceivedEvent) event;

        this.xmlService.save(new XmlEntity(UUID.randomUUID().toString(), xmlReceivedEvent.getXmlConteudo()));
    }
}

package com.rubenskj.core.controllers;

import com.rubenskj.core.enuns.EnumEventType;
import com.rubenskj.core.events.XmlReceivedEvent;
import com.rubenskj.core.services.HandlerAllEventsService;
import com.rubenskj.core.services.XmlReceivedEventService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class BrokerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrokerController.class);

    private final HandlerAllEventsService handlerAllEventsService;

    public BrokerController(HandlerAllEventsService handlerAllEventsService) {
        this.handlerAllEventsService = handlerAllEventsService;
    }

    @PostMapping("/notificar-broker")
    public String validateBroker(@RequestBody String xml) throws Exception {
        LOGGER.info("Recebendo XML da Mensageria");

        long begin = System.currentTimeMillis();

        byte[] xmlDecoded = Base64.getDecoder().decode(xml);

        String xmlDecodedInString = new String(xmlDecoded, StandardCharsets.UTF_8);

        XmlReceivedEvent xmlReceivedEvent = new XmlReceivedEvent(UUID.randomUUID().toString(), xmlDecodedInString);
        this.handlerAllEventsService.handleEvent(EnumEventType.XML_RECEIVED.name(), XmlReceivedEventService.class.getName(), xmlReceivedEvent);

        LOGGER.info("Finalizando envio do handler do evento. {} ms.", (System.currentTimeMillis() - begin));

        return "The communication with PROMAX works correctly";
    }
}

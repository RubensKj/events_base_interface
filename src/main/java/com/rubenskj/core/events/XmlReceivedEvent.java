package com.rubenskj.core.events;

import com.rubenskj.core.interfaces.IEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class XmlReceivedEvent implements IEvent {

    private String id;
    private String xmlConteudo;

    public XmlReceivedEvent(String id, String xmlConteudo) {
        this.id = id;
        this.xmlConteudo = xmlConteudo;
    }
}

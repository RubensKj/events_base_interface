package com.rubenskj.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "xml_entidade_doc")
public class XmlEntity {

    @Id
    private String id;
    private String uuid;
    private String xmlConteudo;

    public XmlEntity(String uuid, String xmlConteudo) {
        this.uuid = uuid;
        this.xmlConteudo = xmlConteudo;
    }

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getXmlConteudo() {
        return xmlConteudo;
    }

    public void setXmlConteudo(String xmlConteudo) {
        this.xmlConteudo = xmlConteudo;
    }
}

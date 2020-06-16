package com.rubenskj.core.services;

import com.rubenskj.core.repositories.IXmlRepository;
import com.rubenskj.core.entities.XmlEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class XmlService {

    private final IXmlRepository xmlRepository;

    public XmlService(IXmlRepository xmlRepository) {
        this.xmlRepository = xmlRepository;
    }

    public XmlEntity save(XmlEntity xmlEntity) {
        this.validate(xmlEntity);

        return this.xmlRepository.save(xmlEntity);
    }

    private void validate(XmlEntity xmlEntity) {
        if (xmlEntity == null) {
            throw new IllegalStateException("XmlEntity cannot be null");
        }

        if (StringUtils.isEmpty(xmlEntity.getUuid())) {
            throw new IllegalArgumentException("Uuid cannot be null");
        }

        if (StringUtils.isEmpty(xmlEntity.getXmlConteudo())) {
            throw new IllegalArgumentException("XmlConteudo cannot be null");
        }
    }
}

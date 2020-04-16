package com.rubenskj.core.repositories;

import com.rubenskj.core.entities.XmlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IXmlRepository extends MongoRepository<XmlEntity, String> {
}

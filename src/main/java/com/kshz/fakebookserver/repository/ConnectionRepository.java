package com.kshz.fakebookserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kshz.fakebookserver.model.Connection;

@Repository
public interface ConnectionRepository extends MongoRepository<Connection, String> {

}

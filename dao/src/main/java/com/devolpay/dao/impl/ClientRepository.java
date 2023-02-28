package com.devolpay.dao.impl;

import com.devolpay.config.MongoConfig;
import com.devolpay.dao.inter.BaseRepository;
import com.devolpay.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public class  ClientRepository implements BaseRepository<Client> {

    @Autowired
    @Qualifier(MongoConfig.MONGODB)
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Client cliente) {
        mongoTemplate.insert(cliente,"client");
    }

    @Override
    public void update(Client cliente) {
        mongoTemplate.save(cliente,"client");
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Client.class);
    }

    @Override
    public List<Client> getAll() {
       return mongoTemplate.findAll(Client.class);
    }

}

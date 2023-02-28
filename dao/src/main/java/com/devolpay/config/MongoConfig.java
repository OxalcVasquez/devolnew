package com.devolpay.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Configuration
@EnableMongoRepositories(basePackages = "com.devolpay.dao")
@ComponentScan(basePackages = {"com.devolpay.dao"})
public class MongoConfig extends AbstractMongoClientConfiguration {

    public static final String MONGODB="devolpay";

    @Value("${database.mongodb.string-connection}")
    private String connectionString;

    @Value("${database.mongodb.database-name}")
    private String databaseName;

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(this.connectionString);
//      ConnectionString connectionString = new ConnectionString("mongodb://Oxalc:UvfOpsifkkDlIYj9@ac-oi477vz-shard-00-00.hseqwfn.mongodb.net:27017,ac-oi477vz-shard-00-01.hseqwfn.mongodb.net:27017,ac-oi477vz-shard-00-02.hseqwfn.mongodb.net:27017/?ssl=true&replicaSet=atlas-s317ur-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getDatabaseName() {
        return this.databaseName;
//        return "devolpay";
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.devolpay");
    }

    @Bean(name=MONGODB)
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(),MONGODB);
    }

}

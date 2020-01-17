package com.feedback.management;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.feedback.management.repositories.EventSummaryRepository;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;



@Configuration
@EnableMongoRepositories(basePackageClasses=EventSummaryRepository.class)
@ComponentScan(basePackageClasses=EventSummaryRepository.class)
public class MongoConfig extends AbstractMongoConfiguration {
    
    // ---------------------------------------------------- mongodb config

    @Override
    protected String getDatabaseName() {
        return "world";
    }

    @Bean
    public MongoClient mongo() throws Exception {
        MongoClient client = new MongoClient("mongo");
        client.setWriteConcern(WriteConcern.SAFE);
        return client;
    }

    @Override
    protected String getMappingBasePackage() {
        return "io.lishman.springdata.domain";
    }
    
    // ---------------------------------------------------- MongoTemplate
    
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
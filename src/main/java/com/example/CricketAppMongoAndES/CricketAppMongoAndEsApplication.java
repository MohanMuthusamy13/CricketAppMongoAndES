package com.example.CricketAppMongoAndES;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration(exclude = MongoRepositoriesAutoConfiguration.class)
@EnableElasticsearchRepositories(basePackages = "com.example.CricketAppMongoAndES.repository.elasticrepositories")
@EnableMongoRepositories(basePackages = "com.example.CricketAppMongoAndES.repository.mongorepositories")
@ComponentScan
@SpringBootApplication
public class CricketAppMongoAndEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricketAppMongoAndEsApplication.class, args);
	}

}
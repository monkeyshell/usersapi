package com.users.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@Configuration
@PropertySource({"classpath:application.properties" })
@ComponentScan(basePackages={"com.users"})
@EnableMongoRepositories(basePackages = {"com.users.mongo.repository"})
public class ApplicationConfig {


}

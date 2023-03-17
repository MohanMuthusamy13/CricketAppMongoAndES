package com.example.CricketAppMongoAndES.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.CricketAppMongoAndES")
public class Config {
    @Value("${database.use.mongodb}")
    public static boolean useMongoDatabase = true;
}
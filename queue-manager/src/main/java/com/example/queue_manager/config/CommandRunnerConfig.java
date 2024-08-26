package com.example.queue_manager.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@Log
public class CommandRunnerConfig implements CommandLineRunner {
    private final DataSource dataSource;
    @Value("${spring.datasource.url}")
    private String connectionString;

    @Override
    public void run(String... args) throws Exception {
        log.info("Connected to: " + connectionString + ", DB: " + dataSource.getConnection().getMetaData().getDatabaseProductName());
    }
}

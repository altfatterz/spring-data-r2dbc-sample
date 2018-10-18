package com.example;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

@Configuration
@Slf4j
public class DatabaseConfiguration {

    @Bean
    PostgresqlConnectionFactory connectionFactory() {
        PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder() //
                .host("localhost")
                .port(5432)
                .database("postgres")
                .username("postgres")
                .password("secret")
                .build();

        return new PostgresqlConnectionFactory(config);
    }

    @Bean
    DatabaseClient databaseClient(ConnectionFactory factory) {
        return DatabaseClient.builder() //
                .connectionFactory(factory) //
                .build();
    }

    @Bean
    CustomerRepository repositoryFactory(DatabaseClient client) {
        return new R2dbcRepositoryFactory(client, new RelationalMappingContext())
                .getRepository(CustomerRepository.class);
    }

    @Bean
    CommandLineRunner runner(DatabaseClient client) {
        return args -> {
            Stream.of("schema.sql", "data.sql")
                    .peek(it -> log.info(String.format("Executing SQL from %s…", it)))
                    .map(ClassPathResource::new)
                    .flatMap(DatabaseConfiguration::lines)
                    .peek(it -> log.info(String.format("Executing %s.", it)))
                    .forEach(line -> client.execute().sql(line).fetch().rowsUpdated().block());
        };
    }

    private static Stream<String> lines(Resource resource) {

        try {
            return Files.lines(resource.getFile().toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

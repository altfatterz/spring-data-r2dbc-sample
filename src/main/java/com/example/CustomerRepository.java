package com.example;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

    @Query("SELECT * FROM customers WHERE first_name ilike $1 or last_name ilike $1")
    Flux<Customer> findByName(String name);
}

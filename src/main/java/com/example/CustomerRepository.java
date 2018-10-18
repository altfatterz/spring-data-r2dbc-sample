package com.example;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

//    @Query("SELECT * FROM customers WHERE lastname = $1")
//    Mono<Customer> findByLastname(String lastName);
//
//    @Query("SELECT * FROM customers WHERE firstname like $1")
//    Flux<Customer> findByFirstNameConstains(String name);
}

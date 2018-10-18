package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CustomerRestController {

    private final CustomerRepository repository;

    public CustomerRestController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customers")
    public Flux<Customer> findAll() {
        return repository.findAll();
    }
}

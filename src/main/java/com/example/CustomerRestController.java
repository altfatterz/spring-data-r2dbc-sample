package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping("/customers/search")
    public Flux<Customer> findByName(@RequestParam("name") String name) {
        return repository.findByName(name);
    }

    @GetMapping("/customers/{id}")
    public Mono<Customer> findOne(@PathVariable Long id) {
        return repository.findById(id);
    }
}

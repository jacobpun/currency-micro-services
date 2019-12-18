package com.pk.currencyexchange.repo;

import com.pk.currencyexchange.model.ExchangeRate;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface ExchangeRepo extends ReactiveMongoRepository<ExchangeRate, Long> {
    Mono<ExchangeRate> findByFromAndTo(String from, String to);
}
package com.pk.currencyexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

import com.pk.currencyexchange.model.ExchangeRate;
import com.pk.currencyexchange.repo.ExchangeRepo;

@Configuration
@AllArgsConstructor
public class RouteConfig {

    private final ExchangeRepo repo;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route()
            .GET("/rate/from/{from}/to/{to}", this::fetchExchangeRate)
            .build();
    }

    private Mono<ServerResponse> fetchExchangeRate(ServerRequest req) {
        String from = req.pathVariable("from");
        String to = req.pathVariable("to");
        return repo.findByFromAndTo(from, to)
                .flatMap(rate -> ok().body(Mono.just(rate), ExchangeRate.class))
                .switchIfEmpty(notFound().build());
    }
}
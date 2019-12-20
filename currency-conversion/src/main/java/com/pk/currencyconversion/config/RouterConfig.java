package com.pk.currencyconversion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.pk.currencyconversion.client.CurrencyExchangeResourceProxy;
import com.pk.currencyconversion.model.CurrencyRate;
import com.pk.currencyconversion.model.ExchangeRate;

@Configuration
@AllArgsConstructor
@Slf4j
public class RouterConfig {
    private final CurrencyExchangeResourceProxy proxy;
    @Bean
    public RouterFunction<ServerResponse> config() {
        return RouterFunctions
                    .route()
                    .GET("/exchange/from/{from}/to/{to}/count/{count}", this::fetchExchangeRate).build();
    }

    private ServerResponse fetchExchangeRate(ServerRequest req) {
        String from = req.pathVariable("from");
        String to = req.pathVariable("to");
        int count = Integer.parseInt(req.pathVariable("count"));

        log.info("received request From {}, To {}, count {}", from, to, count);

        ExchangeRate exchangeRate = proxy.fetchExchangeRate(from, to);
        if (exchangeRate == null) {
            return ServerResponse.notFound().build();
        }
        CurrencyRate currencyRate = new CurrencyRate(from, to, exchangeRate.getRate(), count, exchangeRate.getRate() *count);
        return ServerResponse.ok().body(currencyRate);
    }
}
package com.pk.currencyconversion.client;

import com.pk.currencyconversion.model.ExchangeRate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service", url = "${CURRENCY_EXCHANGE_URI:http://localhost:8080}")
public interface CurrencyExchangeResourceProxy {
    @GetMapping("/rate/from/{from}/to/{to}")
    ExchangeRate fetchExchangeRate(@PathVariable("from") String from, @PathVariable("to") String to);
}
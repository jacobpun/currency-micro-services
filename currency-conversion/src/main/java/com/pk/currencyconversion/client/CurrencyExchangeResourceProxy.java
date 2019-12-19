package com.pk.currencyconversion.client;

import com.pk.currencyconversion.model.ExchangeRate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zuul-gateway")
public interface CurrencyExchangeResourceProxy {
    @GetMapping("currency-exchange-service/rate/from/{from}/to/{to}")
    ExchangeRate fetchExchangeRate(@PathVariable("from") String from, @PathVariable("to") String to);
}
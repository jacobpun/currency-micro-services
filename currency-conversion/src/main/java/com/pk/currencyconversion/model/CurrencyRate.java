package com.pk.currencyconversion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRate {
    private String from;
    private String to;
    private double rate;
    private int count;
    private double totaValue;
}
package com.kadir.controller;

import com.kadir.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}

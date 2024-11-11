package com.kadir.service;

import com.kadir.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}

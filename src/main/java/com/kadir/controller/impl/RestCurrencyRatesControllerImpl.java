package com.kadir.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kadir.controller.IRestCurrencyRatesController;
import com.kadir.controller.RootEntity;
import com.kadir.dto.CurrencyRatesResponse;
import com.kadir.service.ICurrencyRatesService;

@RestController
@RequestMapping("rest/api/")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @GetMapping("/currency-rates")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
    }

}

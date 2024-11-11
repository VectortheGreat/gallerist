package com.kadir.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kadir.dto.CurrencyRatesResponse;
import com.kadir.exception.BaseException;
import com.kadir.exception.ErrorMessage;
import com.kadir.exception.MessageType;
import com.kadir.service.ICurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

    @Value("${apiKey}")
    private String apiKey;

    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";

        String endPoint = rootUrl + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type="
                + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", apiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()) {

                return response.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
        }
        return null;
    }

}

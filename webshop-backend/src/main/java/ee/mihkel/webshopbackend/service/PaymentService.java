package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.model.input.EverypayResponse;
import ee.mihkel.webshopbackend.model.output.EverypayData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class PaymentService {
    private final String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";

    @Autowired
    RestTemplate restTemplate;

    public String makePayment(BigDecimal totalSum) {
        EverypayData data = new EverypayData();
        data.setApi_username("92ddcfab96e34a5f");
        data.setAccount_name("EUR3D1");
        data.setAmount(totalSum);
        data.setOrder_reference("91221");
        data.setToken_agreement("unscheduled");
        data.setNonce(Math.random()*99999 + "92ddcfab96e34a5f");
        data.setTimestamp(ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        data.setCustomer_url("https://shop.example.com/cart");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic OTJkZGNmYWI5NmUzNGE1Zjo4Y2QxOWU5OWU5YzJjMjA4ZWU1NjNhYmY3ZDBlNGRhZA==");
        HttpEntity<EverypayData> requestData = new HttpEntity<>(data, httpHeaders);
        ResponseEntity<EverypayResponse> response =  restTemplate.exchange(url, HttpMethod.POST, requestData, EverypayResponse.class);

        return response.getBody().getPayment_link();
    }
}

package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.model.input.EverypayResponse;
import ee.mihkel.webshopbackend.model.output.EverypayData;
import ee.mihkel.webshopbackend.model.output.EverypayLink;
import ee.mihkel.webshopbackend.util.OrderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class PaymentService {
    @Value("${everypay.url}")
    private String url;

    @Value("${everypay.username}")
    private String userName;

    @Value("${everypay.accountname}")
    private String accountName;

    @Value("${everypay.tokenAgreement}")
    private String tokenAgreement;

    @Value("${everypay.customerUrl}")
    private String customerUrl;

    @Value("${everypay.authKey}")
    private String authKey;

    @Value("${everypay.nonceKey}")
    private String nonceKey;

    @Autowired
    RestTemplate restTemplate;

    public EverypayLink makePayment(List<Item> orderItems, Long orderId) {
        log.info("Started everypay payment");

        BigDecimal totalSum = OrderUtil.getSumOfOrder(orderItems);


        ZonedDateTime timestamp = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());

        EverypayData data = new EverypayData();
        data.setApi_username(userName);
        data.setAccount_name(accountName);
        data.setAmount(totalSum);
        data.setOrder_reference(orderId.toString());
        data.setToken_agreement(tokenAgreement);
        try {
            data.setNonce(encode(userName+orderId+timestamp));
        } catch (Exception e) {
            log.error("Error while generating nonce {}",e.getMessage());
        }
        data.setTimestamp(timestamp);
        data.setCustomer_url(customerUrl);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", authKey);
        HttpEntity<EverypayData> requestData = new HttpEntity<>(data, httpHeaders);
        ResponseEntity<EverypayResponse> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, requestData, EverypayResponse.class);
            return new EverypayLink(response.getBody().getPayment_link());
        } catch (RestClientException e) {
            log.error("Error while connecting everypay - {}",e.getMessage());
            return null;
        }
    }

    public static String encode(String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        sha256_HMAC.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
        byte[] result = sha256_HMAC.doFinal("example".getBytes());
        return DatatypeConverter.printHexBinary(result);
    }
}

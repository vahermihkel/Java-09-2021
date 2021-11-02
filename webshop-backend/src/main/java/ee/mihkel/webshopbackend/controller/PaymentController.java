package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.model.input.OrderSum;
import ee.mihkel.webshopbackend.model.output.EverypayData;
import ee.mihkel.webshopbackend.model.output.EverypayLink;
import ee.mihkel.webshopbackend.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("payment")
    public ResponseEntity<EverypayLink> makePayment(@RequestBody List<Item> orderItems) {

        EverypayLink everypayLink = paymentService.makePayment(orderItems);
        if (everypayLink == null) {
            log.info("Sending empty result to frontend");
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(everypayLink);
    }
}

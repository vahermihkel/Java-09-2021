package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.input.OrderSum;
import ee.mihkel.webshopbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@CrossOrigin( origins = "http://localhost:4200" ) // kust saadakse controlleri päringutele ligi
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("payment")
    public String makePayment(@RequestBody OrderSum orderSum) {
        return paymentService.makePayment(orderSum.getTotalSum());
    }
}

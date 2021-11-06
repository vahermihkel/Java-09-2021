package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.exception.ItemNotFoundException;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.model.input.OrderInput;
import ee.mihkel.webshopbackend.model.output.EverypayLink;
import ee.mihkel.webshopbackend.service.OrderService;
import ee.mihkel.webshopbackend.service.PaymentService;
import ee.mihkel.webshopbackend.util.OrderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderUtil orderUtil;

    @PostMapping("payment")
    public ResponseEntity<EverypayLink> makePayment(@RequestBody OrderInput order) throws ItemNotFoundException {

        List<Item> itemsFromDatabase = orderUtil.getDatabaseItems(order.getItems());
        Long orderId = orderService.saveOrderToDatabase(itemsFromDatabase,order.getPersonCode());

        EverypayLink everypayLink = paymentService.makePayment(itemsFromDatabase, orderId);
        if (everypayLink == null) {
            log.info("Sending empty result to frontend");
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(everypayLink);
    }
}

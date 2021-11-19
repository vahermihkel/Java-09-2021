package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.exception.ItemNotFoundException;
import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.model.Order;
import ee.mihkel.webshopbackend.model.Person;
import ee.mihkel.webshopbackend.model.input.OrderInput;
import ee.mihkel.webshopbackend.repository.ItemRepository;
import ee.mihkel.webshopbackend.repository.OrderRepository;
import ee.mihkel.webshopbackend.repository.PersonRepository;
import ee.mihkel.webshopbackend.util.OrderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ItemRepository itemRepository;

    public Long saveOrderToDatabase(List<Item> orderItems, String personCode) throws ItemNotFoundException {
        Order order = new Order();

        order.setItems(orderItems);
        BigDecimal totalSum = OrderUtil.getSumOfOrder(orderItems);
        order.setTotalSum(totalSum);
        Person person = personRepository.getById(personCode);
        order.setPerson(person);
        log.info("Order created: " + order);
        order.setIsPaid(false);
        Order orderFromDb = orderRepository.save(order);
        return orderFromDb.getId();
    }
}

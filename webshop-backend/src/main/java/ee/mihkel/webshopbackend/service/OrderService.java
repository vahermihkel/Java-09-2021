package ee.mihkel.webshopbackend.service;

import ee.mihkel.webshopbackend.model.Item;
import ee.mihkel.webshopbackend.model.Order;
import ee.mihkel.webshopbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Long saveOrderToDatabase(List<Item> items) {
        Order order = new Order();
        orderRepository.save(order);
        return 1L;
    }
}

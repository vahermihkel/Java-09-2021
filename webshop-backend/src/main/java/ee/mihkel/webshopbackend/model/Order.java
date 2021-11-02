package ee.mihkel.webshopbackend.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private int totalSum;

    @OneToMany()
    private List<Item> items;
}

package ee.mihkel.webshopbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ee.mihkel.webshopbackend.annotation.ValueFalseAnnotation;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private BigDecimal totalSum;
    private Boolean isPaid;

    @OneToOne
    @NotNull
    private Person person;

    @ManyToMany()
    private List<Item> items;
}

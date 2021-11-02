package ee.mihkel.webshopbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

// asendab generate - constructor/getter/setter/toString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private BigDecimal price;
    private String imgSrc;
    private String category;
    @JsonProperty
    private boolean isActive;
}

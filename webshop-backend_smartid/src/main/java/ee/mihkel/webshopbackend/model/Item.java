package ee.mihkel.webshopbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

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

    @OneToOne
    private Category category;

    @JsonProperty
    private boolean isActive;
}

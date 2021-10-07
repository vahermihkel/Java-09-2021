package ee.mihkel.webshopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private double price;
    private String imgSrc;
    private String category;
    private boolean isActive;
}

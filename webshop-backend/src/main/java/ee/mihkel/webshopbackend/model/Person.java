package ee.mihkel.webshopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    @Id
    private String personCode;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String eMail;
    private String phoneNumber;
}

// Controller Personile
// Repository Personile
// Cache?????

// frontendis
// admin vaate kõrval veel 2 nuppu:
// "Logi sisse"
// "Registreeru"
// * routerLInk (mine admin vaatesse)

// Registeerimisse vorm:
//  kõik ülemised 6 asja + parooli kordamine
// * form uue toote lisamine

// Logi sisse
// otsib üles isikukoodi ja kontrollib backendist kas on sama parool
// if () parooli osas
// * form uue toote lisamine

// administraatorile uus leht: vaata/muuda kasutajaid
// isikukood, eesnimi, perenimi, email, telefoninumber
// igaühel on "X" nupp et teda saaks eemaldada
// * Kategooriate osas

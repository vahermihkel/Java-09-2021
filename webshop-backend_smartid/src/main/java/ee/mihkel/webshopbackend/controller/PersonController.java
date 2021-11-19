package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.exception.UserAlreadyExistsException;
import ee.mihkel.webshopbackend.model.Person;
import ee.mihkel.webshopbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("all-people")
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @PostMapping("add-person")
    public void addNewPerson(@RequestBody Person person) throws UserAlreadyExistsException {
        if (personRepository.findById(person.getPersonCode()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        personRepository.save(person);
    }

    @PostMapping ("edit-person")
    public void editPerson(@RequestBody Person person) {
        personRepository.save(person);
    }

    @GetMapping ("person/{personCode}")
    public Person getPerson(@PathVariable String personCode) {
        return personRepository.findById(personCode).get();
    }
    @DeleteMapping ("delete-person/{personCode}")
    public void deletePerson(@PathVariable String personCode) {
        personRepository.deleteById(personCode);
    }

}

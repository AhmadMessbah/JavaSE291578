package mft.controller;

import mft.model.repository.PersonDa;
import mft.model.entity.Person;

import java.util.regex.Pattern;

public class PersonController {
    public static Person save(String name, String family) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", name) &&
                    (Pattern.matches("^[a-zA-Z\\s]{3,30}$", family))) {
                Person person = Person.builder().name(name).family(family).build();

                PersonDa personDa = new PersonDa();
                personDa.save(person);
                return person;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }
}

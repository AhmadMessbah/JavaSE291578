package mft.model.service;

import mft.model.entity.Person;
import mft.model.repository.PersonDa;

import java.util.List;

public class PersonService {
    private static PersonService service = new PersonService();

    private PersonService() {
    }

    public static PersonService getService() {
        return service;
    }

    public Person save(Person person) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            return personDa.save(person);
        }
    }

    public Person edit(Person person) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            return personDa.edit(person);
        }
    }

    public Person remove(int id) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findById(id);
            if (person != null) {
                personDa.remove(id);
                return person;
            }
            else {
//                throw  new Exception("No Person");
                return null;
            }
        }
    }

    public List<Person> findAll() throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            return personDa.findAll();
        }
    }

    public Person findById(int id) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            return personDa.findById(id);
        }
    }

    public List<Person> findByFamily(String family) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            return personDa.findByFamily(family);
        }
    }
}

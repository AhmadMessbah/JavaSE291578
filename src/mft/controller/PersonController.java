package mft.controller;

import mft.model.entity.enums.Gender;
import mft.model.entity.Person;
import mft.model.service.PersonService;

import java.time.LocalDate;
import java.util.List;

public class PersonController {
    private static PersonController controller = new PersonController();

    private PersonController() {
    }

    public static PersonController getController() {
        return controller;
    }

    public Person save(String name, String family, LocalDate birthDate, Gender gender, Boolean active) {
        try {
            Person person =
                    Person
                            .builder()
                            .name(name)
                            .family(family)
                            .birthDate(birthDate)
                            .gender(gender)
                            .active(active)
                            .build();

            PersonService.getService().save(person);
            return person;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Person edit(Integer id, String name, String family, LocalDate birthDate, Gender gender, Boolean active) {
        try {
            Person person =
                    Person
                            .builder()
                            .id(id)
                            .name(name)
                            .family(family)
                            .birthDate(birthDate)
                            .gender(gender)
                            .active(active)
                            .build();

            PersonService.getService().edit(person);
            return person;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Person remove(Integer id) {
        try {
            Person person = PersonService.getService().findById(id);
            PersonService.getService().remove(id);
            return person;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Person> findAll() {
        try {
            return PersonService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Person findById(Integer id) {
        try {
            return PersonService.getService().remove(id);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Person> findByFamily(String family) {
        try {
            return PersonService.getService().findByFamily(family);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
}

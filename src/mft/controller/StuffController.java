package mft.controller;

import mft.model.entity.Person;
import mft.model.repository.StuffDa;
import mft.model.entity.Stuff;
import mft.model.service.PersonService;
import mft.model.service.StuffService;

import java.util.List;
import java.util.regex.Pattern;

public class StuffController {
    public static Stuff save(String name, String brand, String groupTitle) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", name) &&
                    Pattern.matches("^[a-zA-Z\\s]{3,30}$", brand) &&
                    Pattern.matches("^[a-zA-Z\\s]{3,30}$", groupTitle)) {
                Stuff stuff = Stuff.builder().name(name).brand(brand).groupTitle(groupTitle).build();

                return StuffService.getService().save(stuff);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public static Stuff edit(String name, String brand, String groupTitle) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", name) &&
                    Pattern.matches("^[a-zA-Z\\s]{3,30}$", brand) &&
                    Pattern.matches("^[a-zA-Z\\s]{3,30}$", groupTitle)) {
                Stuff stuff = Stuff
                        .builder()
                        .name(name)
                        .brand(brand)
                        .groupTitle(groupTitle)
                        .build();

                return StuffService.getService().edit(stuff);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public Stuff remove(Integer id) {
        try {
            return StuffService.getService().remove(id);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Stuff> findAll() {
        try {
            return StuffService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
    public Stuff findById(Integer id) {
        try {
            return StuffService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Stuff> findByFamily(String name) {
        try {
            return StuffService.getService().findByName(name);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

}

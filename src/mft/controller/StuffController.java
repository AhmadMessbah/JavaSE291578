package mft.controller;

import mft.model.repository.StuffDa;
import mft.model.entity.Stuff;

import java.util.regex.Pattern;

public class StuffController {
    public static Stuff save(String name, String brand, String groupTitle) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", name) &&
                    Pattern.matches("^[a-zA-Z\\s]{3,30}$", brand) &&
                    Pattern.matches("^[a-zA-Z\\s]{3,30}$", groupTitle)) {
                Stuff stuff = Stuff.builder().name(name).brand(brand).groupTitle(groupTitle).build();

                StuffDa stuffDa = new StuffDa();
                stuffDa.save(stuff);
                return stuff;
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
                Stuff stuff = Stuff.builder().name(name).brand(brand).groupTitle(groupTitle).build();

                StuffDa stuffDa = new StuffDa();
                stuffDa.save(stuff);
                return stuff;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

}

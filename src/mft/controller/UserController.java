package mft.controller;

import mft.model.da.UserDa;
import mft.model.entity.User;

import java.util.regex.Pattern;

public class UserController {
    public static User save(String username, String password , boolean active) {
        try {
            if (Pattern.matches("[a-z\\d\\S\\._]{5,30}" ,username) &&
                    (Pattern.matches("[a-zA-Z\\d\\S]{5,30}" , password))) {
                User user = User.builder().username(username).password(password).active(active).build();

                UserDa userDa = new UserDa();
                userDa.save(user);
                return user;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }
}

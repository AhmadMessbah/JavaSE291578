package mft.controller;

import mft.model.entity.User;
import mft.model.service.UserService;

import java.util.regex.Pattern;

public class UserController {
    public static void save(String username, String password , boolean active) {
        System.out.println("UserController - Save");
        try {
            if (Pattern.matches("^[a-z\\d\\S\\._]{5,30}$" ,username) &&
                    (Pattern.matches("^[\\w\\S]{5,30}$" , password))) {
                User user =
                        User
                                .builder()
                                .username(username)
                                .password(password)
                                .active(active)
                                .build();

                UserService.save(user);
                System.out.println("User Saved : " + user.toString());
            }else {
                System.out.println("Invalid Username/Password");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}

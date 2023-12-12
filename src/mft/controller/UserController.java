package mft.controller;

import mft.model.entity.User;
import mft.model.service.UserService;

import java.util.List;
import java.util.regex.Pattern;

public class UserController {
    private static UserController controller=new UserController();

    private UserController() {
    }

    public static UserController getController() {
        return controller;
    }

    public User save(String username, String password , Boolean active) {
        try {
            if (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$" ,username) &&
                    (Pattern.matches("^[\\w\\S]{5,30}$" , password))) {
                User user =
                        User
                                .builder()
                                .username(username)
                                .password(password)
                                .active(active)
                                .build();
                UserService.getService().save(user);
                return user;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public User edit(Integer id, String username, String password, Boolean active){
        try {
            if (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$" ,username) &&
                    (Pattern.matches("^[\\w\\S]{5,30}$" , password))) {
                User user =
                        User
                                .builder()
                                .id(id)
                                .username(username)
                                .password(password)
                                .active(active)
                                .build();
                UserService.getService().edit(user);
                return user;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public User remove(Integer id){
        try {
            User user=UserService.getService().findById(id);
            UserService.getService().remove(id);
            return user;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public List<User> findAll(){
        try {
            return UserService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public User findById(Integer id){
        try {
            return UserService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public User findByUsername(String username){
        try {
            return UserService.getService().findByUsername(username);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }
}

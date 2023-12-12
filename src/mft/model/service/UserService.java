package mft.model.service;

import mft.model.entity.User;
import mft.model.repository.UserDa;

import java.util.List;

public class UserService {
    private static UserService service = new UserService();

    private UserService() {
    }

    public static UserService getService() {
        return service;
    }

    public User save(User user) throws Exception {
        try (UserDa userDa = new UserDa()) {
            if (userDa.findByUsername(user.getUsername()) == null) {
                return userDa.save(user);
            } else {
                throw new Exception();
            }
        }
    }

    public User edit(User user) throws Exception {
        try (UserDa userDa = new UserDa()) {
                return userDa.edit(user);
        }
    }

    public User remove(int id) throws Exception {
        try (UserDa userDa = new UserDa()) {
            User user= userDa.findById(id);
            if (user != null){
                userDa.remove(id);
                return user;
            }
            else {
                return null;
            }
        }
    }

    public List<User> findAll() throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.findAll();
        }
    }

    public User findById(int id) throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.findById(id);
        }
    }

    public User findByUsername(String username) throws Exception {
        try (UserDa userDa = new UserDa()) {
            return userDa.findByUsername(username);
        }
    }
}

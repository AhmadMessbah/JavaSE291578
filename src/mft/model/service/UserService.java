package mft.model.service;

import mft.model.entity.User;
import mft.model.repository.UserDa;

public class UserService {
    public static void save(User user) throws Exception {
        System.out.println("UserService - Save");
        UserDa userDa= new UserDa();
        if (userDa.findByUsername(user.getUsername())==null){
            userDa.save(user);
        }else {
            throw new Exception();
        }

    }
}

package mft;

import mft.model.entity.User;
import mft.model.service.UserService;

public class Main {
    public static void main(String[] args) throws Exception {
//        UserService.getService().save(User.builder().username("aaa").build());
//        UserService.getService().save(User.builder().username("bbb").build());
//        UserService.getService().save(User.builder().username("ccc").build());

//        System.out.println(UserService.getService().findAll());

        System.out.println(UserService.getService().findByAll("bbb"));
    }
}

package com.inside.springsecurityjwt.controller;

import com.inside.springsecurityjwt.entity.UserEntity;
import com.inside.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    public static int COUNT_MESSAGE = 0;
    @Autowired
    private UserService userService;

    @PostMapping("/user/saveMessage")
    public String saveMessage(@RequestBody UserEntity userEntity) {
        userService.saveMessage(userEntity);
        return "Save message";
    }

    @PostMapping("/user/showHistoryMessage")
    public List<String> showHistoryMessage(@RequestBody UserEntity userEntity) {

        if (userEntity.getMessage().startsWith("history")) {
            String message = userEntity.getMessage();
            String[] arr = message.split("\n");
            for (String s : arr) {
                COUNT_MESSAGE = Integer.parseInt(s.trim().split(" ")[1]);
            }
        }
        return userService.showHistoryMessage(COUNT_MESSAGE);
    }
}

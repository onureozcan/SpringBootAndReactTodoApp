package org.zero.todoapp.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.todoapp.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/{uname}/{pwd}")
    public void register(@PathVariable("uname") String userName,
                         @PathVariable("pwd") String password) {
        userService.register(userName,password);
    }

    @PostMapping("/login/{uname}/{passwd}")
    public String login(@PathVariable("uname") String userName,
                        @PathVariable("pwd") String password) {
        return null;
    }
}

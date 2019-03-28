package org.zero.todoapp.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.todoapp.services.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody Map<String, Object> payload) throws NoSuchAlgorithmException {
        String userName = payload.get("user") + "";
        String password = payload.get("pwd") + "";
        userService.register(userName, password);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> payload) {
        String userName = payload.get("user") + "";
        String password = payload.get("pwd") + "";
        return null;
    }
}

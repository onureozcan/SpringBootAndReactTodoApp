package org.zero.todoapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zero.todoapp.data.UserRepository;
import org.zero.todoapp.models.UserModel;
import org.zero.todoapp.utils.PasswordHashingUtils;

import java.security.NoSuchAlgorithmException;

public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public void register(String userName, String password) throws NoSuchAlgorithmException {
        logger.debug("register:" + userName + "| pwd:" + password);
        userRepository.save(new UserModel(userName, PasswordHashingUtils.hashUserPassword(password)));
    }
}

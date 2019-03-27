package org.zero.todoapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public void register(String userName, String password) {
        logger.debug("register:" + userName + "| pwd:" + password);
    }
}

package org.zero.todoapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zero.todoapp.Constants;
import org.zero.todoapp.data.UserRepository;
import org.zero.todoapp.models.UserModel;
import org.zero.todoapp.utils.PasswordHashingUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public void register(String userName, String password) throws NoSuchAlgorithmException {
        logger.debug("register:" + userName + "| pwd:" + password);
        userRepository.save(new UserModel(userName, PasswordHashingUtils.hashUserPassword(password)));
    }

    public boolean validate(String userName, String password) throws NoSuchAlgorithmException {
        return userRepository.findUserByUserNameAndPassword(
                userName,PasswordHashingUtils.hashUserPassword(password)
        ) != null;
    }

    public String getCurrentUserName(HttpServletRequest request) {
        return String.valueOf((Object) request.getAttribute(Constants.STR_USER_NAME));
    }

}

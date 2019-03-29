package org.zero.todoapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.zero.todoapp.models.UserModel;


@Transactional
public interface UserRepository extends JpaRepository<UserModel, String> {
    UserModel findUserByUserNameAndPassword(String userName, String password);

    UserModel findUserByUserName(String name);
}

package org.zero.todoapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zero.todoapp.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String> {
}

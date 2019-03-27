package org.zero.todoapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_model", schema = "todoapp")
public class UserModel {

    @Id
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password; // hashed

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

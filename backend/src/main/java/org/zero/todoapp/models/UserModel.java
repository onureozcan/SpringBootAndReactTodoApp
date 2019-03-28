package org.zero.todoapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user_model", schema = "todolist")
public class UserModel {

    @Id
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password; // hashed

    @OneToMany(mappedBy = "owner")
    private List<TaskListModel> taskLists;

    public UserModel() {
    }

    public UserModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public List<TaskListModel> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(List<TaskListModel> taskLists) {
        this.taskLists = taskLists;
    }

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

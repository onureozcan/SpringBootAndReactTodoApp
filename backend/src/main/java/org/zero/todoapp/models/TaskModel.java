package org.zero.todoapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task_model", schema = "todolist")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(targetEntity = TaskListModel.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tasklist_id")
    private TaskListModel taskList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depends_on")
    private TaskModel dependsOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskModel getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(TaskModel dependsOn) {
        this.dependsOn = dependsOn;
    }

    public TaskListModel getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskListModel taskList) {
        this.taskList = taskList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

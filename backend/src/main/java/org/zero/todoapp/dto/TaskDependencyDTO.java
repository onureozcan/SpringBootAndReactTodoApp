package org.zero.todoapp.dto;

public class TaskDependencyDTO {

    private int taskId;
    private int dependency;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getDependency() {
        return dependency;
    }

    public void setDependency(int dependency) {
        this.dependency = dependency;
    }
}

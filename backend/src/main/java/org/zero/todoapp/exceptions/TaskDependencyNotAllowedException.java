package org.zero.todoapp.exceptions;

public class TaskDependencyNotAllowedException extends RuntimeException {
    public TaskDependencyNotAllowedException(String message) {
        super(message);
    }
}

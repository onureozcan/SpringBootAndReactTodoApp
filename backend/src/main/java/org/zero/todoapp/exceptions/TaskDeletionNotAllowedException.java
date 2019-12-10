package org.zero.todoapp.exceptions;

public class TaskDeletionNotAllowedException extends RuntimeException {
    public TaskDeletionNotAllowedException(String otherTask) {
        super("this task is dependent to " + otherTask);
    }
}

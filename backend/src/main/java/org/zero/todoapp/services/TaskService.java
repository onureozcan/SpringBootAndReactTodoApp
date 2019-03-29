package org.zero.todoapp.services;

import org.zero.todoapp.Constants;
import org.zero.todoapp.models.TaskModel;

public class TaskService {

    public enum taskCompleteMessages {
        CAN_COMPLETE,
        HAS_INCOMPLETE_DEPENDENCY,
        HAS_EXPIRED
    }

    /**
     * checks this tasks if it has any incomplete dependencies.
     *
     * @param task the task to be checked.
     * @return whether is is safe to delete.
     */
    public boolean canDeleteTask(TaskModel task) {
        return (task.getDependsOn() != null
                && task.getDependsOn().getStatus() != Constants.TASK_STATUS_COMPLETED);

    }

    /**
     * decides whether there is a cyclic dependency.
     *
     * @param task       task to be checked.
     * @param dependency dependency task.
     * @return
     */
    public boolean canHaveDependency(TaskModel task, TaskModel dependency) {
        // simple case
        if (task.getId() == dependency.getId()) {
            return false;
        }
        // TODO: cyclic dependencies;
        return true;
    }

    /**
     * decides whether the given task is allowed to be complete.
     *
     * @return one of the taskCompleteMessages values.
     */
    public taskCompleteMessages canComplete(TaskModel task) {
        // simple case
        if (task.getDueDate() < System.currentTimeMillis()) {
            return taskCompleteMessages.HAS_EXPIRED;
        }
        if (task.getDependsOn() != null &&
                task.getDependsOn().getStatus() != Constants.TASK_STATUS_COMPLETED) {
            return taskCompleteMessages.HAS_INCOMPLETE_DEPENDENCY;
        }
        return taskCompleteMessages.CAN_COMPLETE;
    }

}

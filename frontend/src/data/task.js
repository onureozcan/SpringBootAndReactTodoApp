import cookie from "react-cookies"
import AuthDataSource from './auth'

const base = "http://localhost:8080";

class TaskDataSource {

    static listTasks(listId, callback) {
        $.ajax({
            url: base + "/task/list/" + listId,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", cookie.load("authorization"));
            },
            type: "get",
            success: (data) => {
                callback({
                    success: true,
                    data: data
                });
            },
            error: (data) => {
                // TODO: logout when 401
                if (data.status = "401") {
                    AuthDataSource.logout();
                }
                callback({
                    success: false,
                    data: data
                });
            }
        });
    }

    static addNewTask(taskListId, taskName, newTaskDescription, newTaskDueDate, callback) {
        $.ajax({
            url: base + "/task/add",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", cookie.load("authorization"));
            },
            type: "Post",
            contentType: 'application/json',
            data: JSON.stringify({
                taskName: taskName,
                taskListId:taskListId,
                description:newTaskDescription,
                due:newTaskDueDate
            }),
            success: (data) => {
                callback({
                    success: true,
                    data: data
                });
            },
            error: (data) => {
                if (data.status = "401") {
                    AuthDataSource.logout();
                }
                callback({
                    success: false,
                    data: data
                });
            }
        });
    }
}

export default TaskDataSource;
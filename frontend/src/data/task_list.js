import cookie from "react-cookies"
import AuthDataSource from './auth'

const base = "http://localhost:8080";

class TaskListDataSource {

    static listTaskLists(callback) {
        $.ajax({
            url: base + "/task_lists/list",
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
                if (data.status == "401") {
                    AuthDataSource.logout();
                }
                callback({
                    success: false,
                    data: data
                });
            }
        });

    }

    static addList(taskName, callback) {
        $.ajax({
            url: base + "/task_lists/add",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", cookie.load("authorization"));
            },
            type: "Post",
            contentType: 'application/json',
            data: JSON.stringify({taskName: taskName}),
            success: (data) => {
                callback({
                    success: true,
                    data: data
                });
            },
            error: (data) => {
                if (data.status == "401") {
                    AuthDataSource.logout();
                }
                callback({
                    success: false,
                    data: data
                });
            }
        });
    }

    static deleteList(taskListId, callback) {
        $.ajax({
            url: base + "/task_lists/delete",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("authorization", cookie.load("authorization"));
            },
            type: "Post",
            contentType: 'application/json',
            data: JSON.stringify({taskListId: taskListId}),
            success: (data) => {
                callback({
                    success: true,
                    data: data
                });
            },
            error: (data) => {
                if (data.status == "401") {
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

export default TaskListDataSource;
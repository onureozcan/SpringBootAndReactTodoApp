import cookie from "react-cookies";

const base = "http://localhost:8080";

class TaskListDataSource {

    static listTasks(callback) {
        $.ajax({
            url: base + "/tasks/list",
            beforeSend: function(xhr) {
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
                callback({
                    success: false,
                    data: data
                });
            }
        });

    }

}

export default TaskListDataSource;
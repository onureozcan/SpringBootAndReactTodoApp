package org.zero.todoapp.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.todoapp.models.TaskListModel;
import org.zero.todoapp.models.TaskModel;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskListApiController {

    private Logger logger = LoggerFactory.getLogger(TaskListApiController.class);

    @RequestMapping("/get/{id}")
    public TaskListModel get(@PathVariable("id") int id) {
        logger.debug("GET_TASK_LIST|" + id + "");
        return null;
    }

    @RequestMapping("/list")
    public List<TaskListModel> listTasks() {
        return null;
    }



}

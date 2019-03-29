package org.zero.todoapp.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.todoapp.Constants;
import org.zero.todoapp.data.TaskListRepository;
import org.zero.todoapp.models.TaskListModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskListApiController {

    private Logger logger = LoggerFactory.getLogger(TaskListApiController.class);

    @Autowired
    private TaskListRepository taskListRepository;


    @RequestMapping("/get/{id}")
    public TaskListModel get(@PathVariable("id") int id) {
        logger.debug("GET_TASK_LIST|" + id + "");
        return null;
    }

    @RequestMapping("/list")
    public List<TaskListModel> listTasks(HttpServletRequest request,
                                         HttpServletResponse response) {
        return taskListRepository.findTaskListsByOwner(request.getHeader(Constants.STR_USER_NAME));
    }


}

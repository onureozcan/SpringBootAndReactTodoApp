package org.zero.todoapp.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.todoapp.models.TaskModel;


@RestController
@RequestMapping("/task")
public class TaskApiController {

    private Logger logger = LoggerFactory.getLogger(TaskApiController.class);

    @RequestMapping("/get/{id}")
    public TaskModel get(@PathVariable("id") int id) {
        logger.debug("GET_TASK|" + id + "");
        return null;
    }

}

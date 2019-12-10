package org.zero.todoapp.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.todoapp.dto.AddNewTaskListDTO;
import org.zero.todoapp.dto.TaskIdDTO;
import org.zero.todoapp.models.TaskListModel;
import org.zero.todoapp.services.TaskListService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/task_lists")
public class TaskListApiController {

    private Logger logger = LoggerFactory.getLogger(TaskListApiController.class);

    @Autowired
    private TaskListService taskListService;

    @RequestMapping("/list")
    public List<TaskListModel> listTasks(HttpServletRequest request) {
        return taskListService.listTasks(request);
    }

    @PostMapping("add")
    public void addNewList(@RequestBody AddNewTaskListDTO dto, HttpServletRequest request) {
        taskListService.addNewList(dto, request);
    }

    @PostMapping("delete")
    public void deleteList(@RequestBody TaskIdDTO dto) {
        taskListService.deleteById(dto);
    }

}

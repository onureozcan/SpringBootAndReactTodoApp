package org.zero.todoapp.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.todoapp.Constants;
import org.zero.todoapp.dto.AddNewTaskDTO;
import org.zero.todoapp.dto.TaskIdDTO;
import org.zero.todoapp.dto.TaskDependencyDTO;
import org.zero.todoapp.models.TaskModel;
import org.zero.todoapp.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/task")
public class TaskApiController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/list/{list_id}")
    public List<TaskModel> listTasks(@PathVariable("list_id") String id) {
        return taskService.findTasksByListId(id);
    }

    @PostMapping("add")
    public void addNewTask(@RequestBody AddNewTaskDTO dto) {
        taskService.save(dto);
    }

    @PostMapping("complete")
    public void complete(@RequestBody TaskIdDTO dto) {
        taskService.complete(dto);
    }

    @PostMapping("dependency")
    public void dependency(@RequestBody TaskDependencyDTO taskDependencyDTO) {
        taskService.addDependency(taskDependencyDTO);
    }

    @PostMapping("delete")
    public void delete(@RequestBody TaskIdDTO taskIdDTO) {
        taskService.delete(taskIdDTO);
    }
}

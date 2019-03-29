package org.zero.todoapp.controllers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zero.todoapp.Constants;
import org.zero.todoapp.data.TaskListRepository;
import org.zero.todoapp.data.TaskRepository;
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

    private Logger logger = LoggerFactory.getLogger(TaskApiController.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/list/{list_id}")
    public List<TaskModel> listTasks(@PathVariable("list_id") String id) {
        return taskRepository.findTasksByListId(id);
    }

    @PostMapping("add")
    public void addNewTask(@RequestBody Map<String, Object> payload,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        String listId = String.valueOf((Object) payload.get(Constants.STR_TASK_LIST_ID));
        String taskName = String.valueOf((Object) payload.get(Constants.STR_TASK_NAME));
        String description = String.valueOf((Object) payload.get(Constants.STR_DESCRIPTION));
        Long dueDate = Long.parseLong(String.valueOf((Object) payload.get(Constants.STR_DUE)));

        TaskModel task = new TaskModel();
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setName(taskName);
        task.setDependsOn(null);
        // TODO: ensure that list is there
        task.setTaskList(taskListRepository.findById(Integer.parseInt(listId)).get());
        taskRepository.save(task);

    }

    @PostMapping("complete")
    public void complete(@RequestBody Map<String, Object> payload) {
        String taskId = String.valueOf((Object) payload.get(Constants.STR_TASK_ID));
        TaskModel task = taskRepository.findById(Integer.parseInt(taskId)).get();
        task.setStatus(Constants.TASK_STATUS_COMPLETED);
        taskRepository.save(task);
    }

    @PostMapping("dependency")
    public void dependency(@RequestBody Map<String, Object> payload,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String taskId = String.valueOf((Object) payload.get(Constants.STR_TASK_ID));
        String dependencyId = String.valueOf((Object) payload.get(Constants.STR_DEPENDENCY));
        TaskModel task = taskRepository.findById(Integer.parseInt(taskId)).get();
        TaskModel dependency = taskRepository.findById(Integer.parseInt(dependencyId)).get();
        if (taskService.canHaveDependency(task, dependency)) {
            task.setDependsOn(dependency);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
                    "this task is not allowed to have a dependency on " + dependency.getName());
        }
        taskRepository.save(task);
    }

    @PostMapping("delete")
    public void delete(@RequestBody Map<String, Object> payload,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String taskId = String.valueOf((Object) payload.get(Constants.STR_TASK_ID));
        TaskModel task = taskRepository.findById(Integer.parseInt(taskId)).get();
        task.setStatus(Constants.TASK_STATUS_COMPLETED);
        if (taskService.canDeleteTask(task)) {
            String ret = "this task is dependent to " + task.getDependsOn().getName();
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, ret);
            return;
        }
        taskRepository.delete(task);
    }
}

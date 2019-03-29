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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}

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
import org.zero.todoapp.data.UserRepository;
import org.zero.todoapp.models.TaskListModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/task_lists")
public class TaskListApiController {

    private Logger logger = LoggerFactory.getLogger(TaskListApiController.class);

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/list")
    public List<TaskListModel> listTasks(HttpServletRequest request,
                                         HttpServletResponse response) {
        return taskListRepository
                .findTaskListsByOwnerName(getCurrentUserName(request));
    }

    private String getCurrentUserName(HttpServletRequest request) {
        return String.valueOf((Object) request.getAttribute(Constants.STR_USER_NAME));
    }

    @PostMapping("add")
    public void addNewList(@RequestBody Map<String, Object> payload,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        String name = String.valueOf((Object) payload.get(Constants.STR_TASK_NAME));
        TaskListModel taskListModel = new TaskListModel();
        taskListModel.setName(name);
        taskListModel.setOwner(userRepository.findUserByUserName(getCurrentUserName(request)));
        taskListRepository.save(taskListModel);
    }

    @PostMapping("delete")
    public void deleteList(@RequestBody Map<String, Object> payload) {
        int taskListId = Integer
                .parseInt(String.valueOf((Object) payload.get(Constants.STR_TASK_LIST_ID)));
        taskListRepository.deleteById(taskListId);
    }

}

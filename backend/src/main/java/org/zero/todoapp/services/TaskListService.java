package org.zero.todoapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zero.todoapp.data.TaskListRepository;
import org.zero.todoapp.data.UserRepository;
import org.zero.todoapp.dto.AddNewTaskListDTO;
import org.zero.todoapp.dto.TaskIdDTO;
import org.zero.todoapp.models.TaskListModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<TaskListModel> listTasks(HttpServletRequest request) {
        return taskListRepository
                .findTaskListsByOwnerName(userService.getCurrentUserName(request));
    }

    public void addNewList(AddNewTaskListDTO dto, HttpServletRequest request) {
        TaskListModel taskListModel = new TaskListModel();
        taskListModel.setName(dto.getTaskName());
        taskListModel.setOwner(userRepository.findUserByUserName(userService.getCurrentUserName(request)));
        taskListRepository.save(taskListModel);
    }

    public void deleteById(TaskIdDTO dto) {
        taskListRepository.deleteById(dto.getTaskId());
    }
}

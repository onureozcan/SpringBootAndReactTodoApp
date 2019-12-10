package org.zero.todoapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zero.todoapp.Constants;
import org.zero.todoapp.controllers.api.TaskApiController;
import org.zero.todoapp.data.TaskListRepository;
import org.zero.todoapp.data.TaskRepository;
import org.zero.todoapp.dto.AddNewTaskDTO;
import org.zero.todoapp.dto.TaskIdDTO;
import org.zero.todoapp.dto.TaskDependencyDTO;
import org.zero.todoapp.exceptions.TaskDeletionNotAllowedException;
import org.zero.todoapp.exceptions.TaskDependencyNotAllowedException;
import org.zero.todoapp.models.TaskModel;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class TaskService {

    private Logger logger = LoggerFactory.getLogger(TaskApiController.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskServiceRules taskServiceRules;

    public List<TaskModel> findTasksByListId(String id) {
        return taskRepository.findTasksByListId(id);
    }

    public void save(AddNewTaskDTO dto) {
        TaskModel task = new TaskModel();
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDue());
        task.setName(dto.getTaskName());
        task.setDependsOn(null);
        task.setTaskList(taskListRepository.findById(Integer.parseInt(dto.getTaskListId())).get());
        taskRepository.save(task);
    }

    public void complete(TaskIdDTO dto) {
        TaskModel task = taskRepository.findById(dto.getTaskId()).get();
        task.setStatus(Constants.TASK_STATUS_COMPLETED);
        taskRepository.save(task);
    }

    public void addDependency(TaskDependencyDTO taskDependencyDTO) {
        TaskModel task = taskRepository.findById(taskDependencyDTO.getTaskId()).get();
        TaskModel dependency = taskRepository.findById(taskDependencyDTO.getDependency()).get();
        if (taskServiceRules.canHaveDependency(task, dependency)) {
            task.setDependsOn(dependency);
        } else {
            throw new TaskDependencyNotAllowedException("this task is not allowed to have a dependency on " + dependency.getName());
        }
        taskRepository.save(task);
    }

    public void delete(TaskIdDTO taskIdDTO) {
        TaskModel task = taskRepository.findById(taskIdDTO.getTaskId()).get();
        task.setStatus(Constants.TASK_STATUS_COMPLETED);
        if (taskServiceRules.canDeleteTask(task)) {
            throw new TaskDeletionNotAllowedException(task.getDependsOn().getName());
        }
        taskRepository.delete(task);
    }
}

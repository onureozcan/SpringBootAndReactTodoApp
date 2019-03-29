package org.zero.todoapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zero.todoapp.models.TaskListModel;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskListModel, Integer> {

    List<TaskListModel> findTaskListsByOwner(String owner);

}

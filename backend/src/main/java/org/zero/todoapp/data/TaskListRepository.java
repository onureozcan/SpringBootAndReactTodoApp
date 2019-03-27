package org.zero.todoapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zero.todoapp.models.TaskListModel;

public interface TaskListRepository extends JpaRepository<TaskListModel, Integer> {
}

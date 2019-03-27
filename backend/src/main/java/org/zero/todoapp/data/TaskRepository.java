package org.zero.todoapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zero.todoapp.models.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
}

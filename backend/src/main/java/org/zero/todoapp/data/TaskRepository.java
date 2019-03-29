package org.zero.todoapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zero.todoapp.models.TaskModel;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {

    @Query("from TaskModel where tasklist_id = :id")
    List<TaskModel> findTasksByListId(@Param("id") String id);

}

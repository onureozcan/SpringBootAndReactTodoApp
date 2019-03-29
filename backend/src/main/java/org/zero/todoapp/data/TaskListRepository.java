package org.zero.todoapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zero.todoapp.models.TaskListModel;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TaskListRepository extends JpaRepository<TaskListModel, Integer> {

    @Query("from TaskListModel where owner_name = :name")
    List<TaskListModel> findTaskListsByOwnerName(@Param("name") String ownerName);
}

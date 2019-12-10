import org.junit.Test;
import org.zero.todoapp.models.TaskModel;
import org.zero.todoapp.services.TaskServiceRules;

import static org.junit.Assert.assertTrue;

public class TaskServiceRulesTester {


    @Test
    public void testDeletionWhenThereIsADependentTask() {
        TaskModel t1 = new TaskModel();
        t1.setStatus(1);
        TaskModel t2 = new TaskModel();
        t2.setDependsOn(t1);
        assertTrue(!new TaskServiceRules().canDeleteTask(t2));
    }

    @Test
    public void testDeletionWhenThereIsAClosedDependentTask() {
        TaskModel t1 = new TaskModel();
        t1.setStatus(0);
        TaskModel t2 = new TaskModel();
        t2.setDependsOn(t1);
        assertTrue(new TaskServiceRules().canDeleteTask(t2));
    }

    @Test
    public void testDependencyBetweenTheSameTask() {
        TaskModel t1 = new TaskModel();
        assertTrue(!new TaskServiceRules().canHaveDependency(t1, t1));
    }

    @Test
    public void testTaskThatExpiredCanComplete() {
        TaskModel t1 = new TaskModel();
        t1.setDueDate(System.currentTimeMillis() - 100);
        assertTrue(new TaskServiceRules().canComplete(t1) == TaskServiceRules.taskCompleteMessages.HAS_EXPIRED);
    }

    @Test
    public void testTaskThatHasIncompleteDependencyCanComplete() {
        TaskModel t1 = new TaskModel();
        TaskModel t2 = new TaskModel();
        t1.setStatus(0);
        t2.setDependsOn(t1);
        t2.setDueDate(System.currentTimeMillis() + 100);
        assertTrue(new TaskServiceRules().canComplete(t2) == TaskServiceRules.taskCompleteMessages.HAS_INCOMPLETE_DEPENDENCY);
    }
}

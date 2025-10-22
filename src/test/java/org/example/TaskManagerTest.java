package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TaskManagerTest {
    @Test
    public void testAddTask(){
        TaskManager testTaskManager = new TaskManager();
        Task testTask = new Task("Test title task", "Test description task", Task.Status.TO_DO, LocalDateTime.now());
        testTaskManager.addTask(testTask);
        Assertions.assertEquals(1, testTaskManager.getTasks().size());
    }

    @Test
    public void removeTestTask(){
        TaskManager testTaskManager = new TaskManager();
        Task testTask = new Task("test", "test", Task.Status.TO_DO, LocalDateTime.now());
        testTaskManager.addTask(testTask);
        testTaskManager.removeTaskByTitle(testTask.getTitle());
        Assertions.assertEquals(0, testTaskManager.getTasks().size());
    }

    @Test
    public void removeTestTaskWrongTitle(){
        TaskManager tm = new TaskManager();
        Task testTask = new Task("test", "test", Task.Status.TO_DO, LocalDateTime.now());
        tm.addTask(testTask);
        boolean ok = tm.removeTaskByTitle("giberishtext");
        Assertions.assertFalse(ok);
        Assertions.assertEquals(1, tm.getTasks().size());
    }

    @Test
    public void updateTestTask(){
        TaskManager testTaskManager = new TaskManager();
        Task testTask = new Task("t", "d", Task.Status.TO_DO, LocalDateTime.now());
        testTaskManager.addTask(testTask);
        boolean ok = testTaskManager.updateTask("t", "test updated title", "test updated description", Task.Status.IN_PROGRESS);
        Task updated = testTaskManager.getTasks().get(0);
        Assertions.assertAll(
                () -> Assertions.assertTrue(ok),
                () -> Assertions.assertEquals("test updated title", updated.getTitle()),
                () -> Assertions.assertEquals("test updated description", updated.getDescription()),
                () -> Assertions.assertEquals(Task.Status.IN_PROGRESS, updated.getStatus())
        );
    }

    @Test
    public void updateTestTaskWrongTitle(){
        TaskManager testTaskManager = new TaskManager();
        Task testTask = new Task("t", "d", Task.Status.TO_DO, LocalDateTime.now());
        testTaskManager.addTask(testTask);
        boolean ok = testTaskManager.updateTask("giberishtext", "test updated title", "test updated description", Task.Status.IN_PROGRESS);
        Assertions.assertFalse(ok);
        Task unchanged = testTaskManager.getTasks().get(0);
        Assertions.assertAll(
                () -> Assertions.assertEquals("t", unchanged.getTitle()),
                () -> Assertions.assertEquals("d", unchanged.getDescription()),
                () -> Assertions.assertEquals(Task.Status.TO_DO, unchanged.getStatus())
        );
    }
}

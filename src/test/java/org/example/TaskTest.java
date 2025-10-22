package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskTest {

    @Test
    public void testGettersSetters(){
        Task t = new Task("title", "description", Task.Status.TO_DO, LocalDateTime.now());
        LocalDateTime now = LocalDateTime.now();
        t.setTitle("newTitle");
        t.setDescription("newDescription");
        t.setStatus(Task.Status.IN_PROGRESS);
        t.setCurrentDateTime(now);

        t.getTitle();
        t.getDescription();
        t.getStatus();
        t.getCurrentDateTime();

        Assertions.assertAll(
                () -> Assertions.assertEquals("newTitle", t.getTitle()),
                () -> Assertions.assertEquals("newDescription", t.getDescription()),
                () -> Assertions.assertEquals(Task.Status.IN_PROGRESS, t.getStatus()),
                () -> Assertions.assertEquals(now, t.getCurrentDateTime())
        );
    }
}

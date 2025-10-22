package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public boolean removeTaskByTitle(String title){
        for(Task t : taskList){
            if(t.getTitle().equals(title)){
                taskList.remove(t);
                return true;
            }
        }
        return false;
    }

    public void getAllTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(taskList.get(i) + " ");
        }
    }

    public boolean updateTask(String currentTitle, String newTitle, String newDescription, Task.Status newStatus) {
        for (Task t : taskList) {
            if (t.getTitle().equals(currentTitle)) {
                if (newTitle != null && !newTitle.isBlank()) t.setTitle(newTitle);
                if (newDescription != null && !newDescription.isBlank()) t.setDescription(newDescription);
                if (newStatus != null) t.setStatus(newStatus);
                t.setCurrentDateTime(java.time.LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public void loadFromFile(java.nio.file.Path file) {
        this.taskList.clear();
        this.taskList.addAll(FileHandler.load(file));
    }

    public void saveToFile(java.nio.file.Path file) {
        FileHandler.save(file, this.taskList);
    }

    public java.util.List<Task> getTasks() {
        return java.util.Collections.unmodifiableList(taskList);
    }

}

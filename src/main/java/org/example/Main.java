package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.nio.file.Path;

public class Main{
    public static void main(String[] args) {
        TaskManager t = new TaskManager();
        Scanner sc = new Scanner(System.in);

        Path dataFile = Path.of("tasks.txt");
        System.out.println("Working dir: " + System.getProperty("user.dir"));

        t.loadFromFile(dataFile);

        while(true) {
                System.out.println("\n1. Add task");
                System.out.println("2. View all tasks");
                System.out.println("3. Update task(by title)");
                System.out.println("4. Remove task(by title)");
                System.out.println("5. Export tasks to Markdown");
                System.out.println("6. Exit");
                System.out.println("Please choose an action: ");

            Integer choice = null;
            while (choice == null) {
                System.out.print("Please choose an action (1-6): ");
                String line = sc.nextLine().trim();
                try {
                    choice = Integer.parseInt(line);
                    if (choice < 1 || choice > 6) {
                        System.out.println("Invalid option. Enter a number between 1 and 6.");
                        choice = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option. Enter a number between 1 and 6.");
                }
            }

                switch (choice) {
                    case 1: {
                        System.out.println("Choose a title for the task: ");
                        String title = sc.nextLine();

                        System.out.println("Choose a description for the task: ");
                        String description = sc.nextLine();

                        Task.Status status = null;
                        while(status == null){
                            System.out.println("Choose a status for the task (TO_DO / IN_PROGRESS / DONE): ");
                            String statusStr = sc.nextLine().trim().toUpperCase();
                            try{
                                 status = Task.Status.valueOf(statusStr);
                            } catch(IllegalArgumentException e){
                                System.out.println("Invalid status. Please enter one of: TO_DO / IN_PROGRESS / DONE");
                            }
                        }

                        LocalDateTime now = LocalDateTime.now();

                        Task task = new Task(title, description, status, now);
                        t.addTask(task);
                        System.out.println("Task added successfully");
                        t.saveToFile(dataFile);
                        break;
                    }
                    case 2: {
                        System.out.println("Here are all the tasks created by you: ");
                        t.getAllTasks();
                        break;
                    }
                    case 3: {
                        System.out.println("Please select a task you want to update: ");
                        String currentTitle = sc.nextLine();

                        System.out.println("Select a new title or LEAVE EMPTY IF you want to keep: ");
                        String newTitle = sc.nextLine();
                        if(newTitle.isBlank()) { newTitle = null; }

                        System.out.println("Select a new description or LEAVE EMPTY IF you want to keep: ");
                        String newDescription = sc.nextLine();
                        if(newDescription.isBlank()) { newDescription = null; }

                        Task.Status newStatus = null;
                        while(true){
                            System.out.println("Select a new status (TO_DO / IN_PROGRESS / DONE) or LEAVE EMPTY IF you want to keep: ");
                            String newStatusStr = sc.nextLine().trim();
                            if(newStatusStr.isBlank()) break;
                            try{
                                newStatus = Task.Status.valueOf(newStatusStr.toUpperCase());
                                break;
                            } catch(IllegalArgumentException e){
                                System.out.println("Invalid status. Allowed only: TO_DO / IN_PROGRESS / DONE");
                            }
                        }

                        boolean ok = t.updateTask(currentTitle, newTitle, newDescription, newStatus);
                        System.out.println(ok ? "Task updated." : "Task not found.");
                        t.saveToFile(dataFile);
                        break;
                    }
                    case 4: {
                        System.out.println("In order to remove a task, please select the corespondent title: ");
                        String titleToRemove = sc.nextLine();
                        boolean removed = t.removeTaskByTitle(titleToRemove);
                        System.out.println(removed ? "Removed." : "Task not found.");
                        t.saveToFile(dataFile);
                        break;
                    }
                    case 5:{
                        try {
                            java.nio.file.Path md = java.nio.file.Path.of("tasks.md"); // sau "exports/tasks.md"
                            MarkdownExporter.export(t.getTasks(), md);
                            System.out.println("✅ Exported to: " + md.toAbsolutePath());
                        } catch (Exception e) {
                            System.out.println("❌ Export failed: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 6: {
                        System.out.println("Good bye!");
                        sc.close();
                        return;
                    }
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
    }
}
package org.example;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<Task> load(Path file) {
        List<Task> result = new ArrayList<>();
        if (!Files.exists(file)) return result;

        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] parts = line.split("\\|", -1);
                if (parts.length < 4) continue;

                String title       = parts[0];
                String description = parts[1];
                Task.Status status = Task.Status.valueOf(parts[2]);
                LocalDateTime dt   = LocalDateTime.parse(parts[3]);

                result.add(new Task(title, description, status, dt));
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not load tasks: " + e.getMessage());
        }
        return result;
    }

    public static void save(Path file, List<Task> tasks) {
        try {
            Files.createDirectories(file.getParent() == null ? Path.of(".") : file.getParent());
        } catch (IOException ignored) {}

        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            for (Task t : tasks) {
                String safeTitle = t.getTitle().replace("|", "\\|");
                String safeDesc  = t.getDescription().replace("|", "\\|");

                bw.write(safeTitle + "|" + safeDesc + "|" + t.getStatus() + "|" + t.getCurrentDateTime());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not save tasks: " + e.getMessage());
        }
    }
}

package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownExporter {

    public static void export(List<Task> tasks, Path file) throws IOException {
        if (file.getParent() != null) {
            Files.createDirectories(file.getParent());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("| Title | Description | Status | Created At |\n");
        sb.append("|-------|-------------|--------|------------|\n");

        for (Task t : tasks) {
            sb.append("| ")
                    .append(escape(t.getTitle())).append(" | ")
                    .append(escape(t.getDescription())).append(" | ")
                    .append(t.getStatus()).append(" | ")
                    .append(t.getCurrentDateTime()).append(" |\n");
        }

        Files.writeString(file, sb.toString());
    }

    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("|", "\\|").replace("\n", "<br>");
    }
}

package org.example;

import java.time.LocalDateTime;

public class Task {
    //objects
    private String title;
    private String description;
    private Status status;
    private LocalDateTime currentDateTime;

    public enum Status{
        TO_DO,
        IN_PROGRESS,
        DONE
    };

    //constructor with parameters
    public Task(String title, String description, Status status, LocalDateTime currentDateTime){
        this.title = title;
        this.description = description;
        this.status = status;
        this.currentDateTime = currentDateTime;
    }

    //getter
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public Status getStatus(){ return status; }
    public LocalDateTime getCurrentDateTime(){
        return currentDateTime;
    }

    //setter
    public void setTitle(String t){
        this.title = t;
    }
    public void setDescription(String d){ this.description = d; }
    public void setStatus(Status s){ this.status = s; }
    public void setCurrentDateTime(LocalDateTime cDT){ this.currentDateTime = cDT; }

    //toString
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Status: " + status + "\n" +
                "Created: " + currentDateTime + "\n";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Task)) return false;
        Task other = (Task) obj;

        return java.util.Objects.equals(title, other.title)
                && java.util.Objects.equals(description, other.description)
                && status == other.status
                && java.util.Objects.equals(currentDateTime, other.currentDateTime);
    }

    @Override
    public int hashCode(){
        return java.util.Objects.hash(title, description, status, currentDateTime);
    }
}

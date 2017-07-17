package com.example.bhavyesharma.kaamkarle;

import java.sql.Time;
import java.util.Date;

/**
 * Created by bhavyesharma on 14/07/17.
 */

public class Reminder {
    private String title;
    private String details;
    private String taskDate;
    private String taskTime;

    public Reminder(String title, String taskDate, String taskTime) {
        this.title = title;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.details="";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public Reminder(String title, String details, String taskDate, String taskTime) {
        this.title = title;
        this.details = details;
        this.taskDate = taskDate;
        this.taskTime = taskTime;

    }
}

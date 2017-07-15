package com.example.bhavyesharma.kaamkarle;

import java.sql.Time;
import java.util.Date;

/**
 * Created by bhavyesharma on 14/07/17.
 */

public class Reminder {
    private String title;
    private String details;
    private Date taskDate;
    private Time taskTime;

    public Reminder(String title, Date taskDate, Time taskTime) {
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

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Time getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Time taskTime) {
        this.taskTime = taskTime;
    }

    public Reminder(String title, String details, Date taskDate, Time taskTime) {
        this.title = title;
        this.details = details;
        this.taskDate = taskDate;
        this.taskTime = taskTime;

    }
}

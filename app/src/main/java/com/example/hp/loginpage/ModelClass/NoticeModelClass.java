package com.example.hp.loginpage.ModelClass;

public class NoticeModelClass {

    private String title;
    private String date;
    private String description;
    private int priority;

    public NoticeModelClass() {
    }

    public NoticeModelClass(String title, String date, String description, int priority) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

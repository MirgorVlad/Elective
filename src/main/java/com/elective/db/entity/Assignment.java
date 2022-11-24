package com.elective.db.entity;

import java.sql.Date;

/**
 * Assignment entity
 */
public class Assignment {
    private String name;
    private String description;
    private Date deadline;
    private int course;
    private User user;
    private String path;

    @Override
    public String toString() {
        return "Assignment{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", course=" + course +
                ", user=" + user +
                ", path='" + path + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}

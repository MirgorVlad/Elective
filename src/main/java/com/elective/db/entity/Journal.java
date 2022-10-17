package com.elective.db.entity;

import java.sql.Date;

public class Journal {
    private Course course;
    private User student;
    private Date date;
    private int grade;

    @Override
    public String toString() {
        return "Journal{" +
                "course=" + course +
                ", student=" + student +
                ", date=" + date +
                ", grade=" + grade +
                '}';
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

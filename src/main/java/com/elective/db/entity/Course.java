package com.elective.db.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;
/**
 * Course entity
 */
public class Course implements Serializable {

    private int id;
    private String name;
    private String topic;
    private String description;
    private User teacher;
    private Date startDate;
    private Date finishDate;
    private String image;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Objects.equals(name, course.name) && Objects.equals(topic, course.topic) && Objects.equals(description, course.description) && Objects.equals(teacher, course.teacher) && Objects.equals(startDate, course.startDate) && Objects.equals(finishDate, course.finishDate);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, topic, description, teacher, startDate, finishDate);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", teacher=" + teacher +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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



    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public long countDays(){
        LocalDate start = startDate.toLocalDate();
        LocalDate finish = finishDate.toLocalDate();
        return Duration.between(start.atStartOfDay(), finish.atStartOfDay()).toDays();
    }

    public long duration(){
        return Duration.between(startDate.toLocalDate().atStartOfDay(), finishDate.toLocalDate().atStartOfDay()).toDays();
    }

    public boolean isFinished(){
        return finishDate.toLocalDate().isBefore(LocalDate.now());
    }
}

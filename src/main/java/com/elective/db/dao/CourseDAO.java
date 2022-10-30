package com.elective.db.dao;

import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CourseDAO {

    int MIN_DURATION = 14;
    List<String> topicList = Arrays.asList("programming", "languages", "science", "soft skills");


    void create(Course course, String lang) throws SQLException, DBException;

    List<Course> getAll() throws SQLException, DBException;
    User getTeacher(User user) throws DBException, SQLException;

    void deleteById(int courseId) throws SQLException, DBException;

    Course findById(int id) throws SQLException, DBException;

    void update(Course course) throws SQLException, DBException;

    void jointStudentToCourse(int studentId, int courseId) throws SQLException, DBException;
    boolean isStudentJoined(int userId, int courseId) throws SQLException;

    void unfollowCourse(int studentId, int courseId) throws SQLException, DBException;

    List<Course> availableCourses(int userId) throws SQLException, DBException;

    List<Course> findCoursesByTeacher(int id) throws SQLException, DBException;

    List<Integer> findStudentsInCourse(int courseId) throws SQLException;

    int countStudentsInCourse(int courseId) throws SQLException, DBException;

    List<Course> getCoursesByTopic(String topic) throws SQLException, DBException;

}

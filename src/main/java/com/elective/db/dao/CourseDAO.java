package com.elective.db.dao;

import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {

    void create(Course course, User teacher) throws SQLException, DBException;

    List<Course> getAll() throws SQLException, DBException;
    User getTeacher(User user) throws DBException, SQLException;

    void deleteById(int courseId) throws SQLException, DBException;

    Course findById(int id) throws SQLException, DBException;

    void update(Course course, User teacher) throws SQLException, DBException;

    void jointStudentToCourse(int studentId, int courseId) throws SQLException, DBException;
    boolean isStudentJoined(int userId, int courseId) throws SQLException;

    void unfollowCourse(int studentId, int courseId) throws SQLException, DBException;

    List<Course> availableCourses(int userId) throws SQLException, DBException;
}

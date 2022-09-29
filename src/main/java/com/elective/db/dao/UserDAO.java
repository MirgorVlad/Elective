package com.elective.db.dao;

import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {

    String TEACHER_ROLE = "teacher";
    String STUDENT_ROLE = "student";
    String MANAGER_ROLE = "manager";
    void insert(User user) throws SQLException, DBException;
    User find(String email) throws SQLException, DBException;
    void getRole(User user) throws SQLException, DBException;
    public boolean getStudent(Connection con, User user) throws SQLException;
    public boolean getTeacher(Connection con, User user) throws SQLException;

}

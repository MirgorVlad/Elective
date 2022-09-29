package com.elective.db.dao;

import com.elective.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {

    String TEACHER_ROLE = "teacher";
    String STUDENT_ROLE = "student";
    String MANAGER_ROLE = "manager";
    void insert(User user) throws SQLException, DBException;
    User findByEmail(String email) throws SQLException, DBException;
    User findById(int id) throws SQLException, DBException;
    void getRole(User user) throws SQLException, DBException;
    public boolean isStudent(Connection con, User user) throws SQLException;
    public boolean isTeacher(Connection con, User user) throws SQLException;

}

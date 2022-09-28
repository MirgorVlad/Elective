package com.elective.db.dao;

import com.elective.db.entity.User;

import java.sql.SQLException;

public interface UserDAO {

    String TEACHER_ROLE = "teacher";
    String STUDENT_ROLE = "student";
    String MANAGER_ROLE = "manager";
    void insert(User user) throws SQLException, DBException;
    User find(String email) throws SQLException, DBException;

    void getRole(User user) throws SQLException, DBException;
}

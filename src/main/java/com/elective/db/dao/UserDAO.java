package com.elective.db.dao;

import com.elective.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    String TEACHER_ROLE = "teacher";
    String STUDENT_ROLE = "student";
    String MANAGER_ROLE = "manager";

    void insert(User user) throws SQLException, DBException;

    List<User> getAll() throws SQLException, DBException;

    User findByEmail(String email) throws SQLException, DBException;

    User findById(int id) throws SQLException, DBException;

    String getRole(int id) throws SQLException, DBException;

    List<User> getAllTeachers() throws SQLException, DBException;

    void changeUserState(int userId, boolean state) throws SQLException, DBException;

    boolean isStudent(Connection con, int id) throws SQLException;
    boolean isTeacher(Connection con, int id) throws SQLException;
    boolean isManager(Connection con, int id) throws SQLException;
}

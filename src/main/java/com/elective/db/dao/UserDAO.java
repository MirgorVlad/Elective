package com.elective.db.dao;

import com.elective.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * Interface defines the User DAO objects' API
 */
public interface UserDAO {

    String TEACHER_ROLE = "teacher";
    String STUDENT_ROLE = "student";
    String MANAGER_ROLE = "manager";

    /**
     * Count all users
     */
    int countUsers() throws SQLException;

    /**
     * Insert user into database
     * @param user inserted user
     */
    void insert(User user) throws SQLException, DBException;

    /**
     * Get all users
     */
    List<User> getAll() throws SQLException, DBException;

    /**
     * Find user by email
     * @param email email of user
     */
    User findByEmail(String email) throws SQLException, DBException;

    /**
     * Find user by id
     * @param id user's id
     */
    User findById(int id) throws SQLException, DBException;

    /**
     * Get role of user with id
     * @param id user's id
     */
    String getRole(int id) throws SQLException, DBException;

    /**
     * Get all users with role teacher
     */
    List<User> getAllTeachers() throws SQLException, DBException;

    /**
     * Change user state
     * @param userId user's id
     * @param state block/unblock
     */
    void changeUserState(int userId, boolean state) throws SQLException, DBException;

    /**
     * Check if user is student
     * @param con Connection
     * @param id user's id
     */
    boolean isStudent(Connection con, int id) throws SQLException;

    /**
     * Check if user is teacher
     * @param id user's id
     */
    boolean isTeacher(int id) throws SQLException;

    /**
     * Check if user is manager
     * @param con Connection
     * @param id user's id
     */
    boolean isManager(Connection con, int id) throws SQLException;

    /**
     * Get limit amount of users
     * @param pageid start index
     * @param total count
     */
    List<User> getUsers(int pageid, int total) throws DBException;
}

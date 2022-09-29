package com.elective.db.dao.mysql;

public class SQLQueris {

    public static final String INSERT_USER = "INSERT INTO users values(DEFAULT, ?, ?, ?, ?)";
    public static final String INSERT_TEACHER = "INSERT INTO teachers values(?)";
    public static final String INSERT_STUDENT = "INSERT INTO students values(?)";
    public static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String FIND_TEACHER = "SELECT * FROM teachers WHERE user_id = ?";
    public static final String FIND_STUDENT = "SELECT * FROM students WHERE user_id = ?";
    public static final String FIND_MANAGER = "SELECT * FROM managers WHERE user_id = ?";
    public static final String INSERT_COURSE = "INSERT INTO courses values(DEFAULT, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_COURSES = "SELECT * FROM courses";

    private SQLQueris(){
    }


}

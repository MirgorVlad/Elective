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
    public static final String DELETE_COURSE_BY_ID = "DELETE FROM courses WHERE id = ?";
    public static final String FIND_COURSE_BY_ID = "SELECT * FROM courses WHERE id = ?";
    public static final String UPDATE_COURSE = "UPDATE courses SET name = ?, description = ?, start = ?, finish = ?, teacher = ? WHERE id = ?";
    public static final String JOIN_TO_COURSE = "INSERT INTO sections values (?, ?)";
    public static final String FIND_STUDENT_IN_COURSE_BY_ID = "SELECT * FROM sections where student_id = ? AND course_id = ?";
    public static final String UNFOLLOW_COURSE = "DELETE FROM sections WHERE student_id = ? AND course_id = ?";
    public static final String SELECT_ALL_AVAILABLE_COURSES_FOR_STUDENT = "SELECT * FROM sections where student_id = ?";

    private SQLQueris(){
    }


}

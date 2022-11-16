package com.elective.db.dao.mysql;

public class SQLQueris {

    public static final String INSERT_USER = "INSERT INTO users (id, first_name, last_name, email, password) values(DEFAULT, ?, ?, ?, ?)";
    public static final String INSERT_TEACHER = "INSERT INTO teachers values(?)";
    public static final String INSERT_STUDENT = "INSERT INTO students values(?)";
    public static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String FIND_TEACHER = "SELECT * FROM teachers WHERE user_id = ?";
    public static final String FIND_STUDENT = "SELECT * FROM students WHERE user_id = ?";
    public static final String FIND_MANAGER = "SELECT * FROM managers WHERE user_id = ?";
    public static final String INSERT_COURSE = "INSERT INTO courses values(DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_COURSES = "SELECT * FROM courses";
    public static final String DELETE_COURSE_BY_ID = "DELETE FROM courses WHERE id = ?";
    public static final String FIND_COURSE_BY_ID = "SELECT * FROM courses WHERE id = ?";
    public static final String UPDATE_COURSE = "UPDATE courses SET name = ?, description = ?, start = ?, finish = ?, teacher = ?, topic = ? WHERE id = ?";
    public static final String JOIN_TO_COURSE = "INSERT INTO sections values (?, ?)";
    public static final String FIND_STUDENT_IN_COURSE_BY_ID = "SELECT * FROM sections where student_id = ? AND course_id = ?";
    public static final String UNFOLLOW_COURSE = "DELETE FROM sections WHERE student_id = ? AND course_id = ?";
    public static final String SELECT_ALL_AVAILABLE_COURSES_FOR_STUDENT = "SELECT * FROM sections where student_id = ?";
    public static final String FIND_COURSE_BY_TEACHER_ID = "SELECT * FROM courses WHERE teacher = ?";
    public static final String SELECT_ALL_STUDENTS_IN_COURSE = "SELECT id, first_name, last_name,email,password FROM sections INNER JOIN users u ON sections.student_id = u.id WHERE course_id = ?";
    public static final String INSERT_GRADE = "INSERT INTO journal VALUES (?,?,?,?)";
    public static final String FIND_GRADE_BY_KEY = "SELECT grade from journal WHERE course_id=? AND student_id=? AND date=?";
    public static final String SUM_OF_STUDENT_GRADE = "SELECT SUM(grade) FROM journal WHERE course_id=? AND student_id=?;";
    public static final String UPDATE_GRADE = "UPDATE journal SET grade = ? WHERE course_id=? AND student_id=? AND date=?";
    public static final String COUNT_STUDENTS_IN_COURSE = "SELECT COUNT(*) AS students FROM sections WHERE course_id=?";
    public static final String FIND_COURSE_BY_TOPIC = "SELECT * FROM courses WHERE topic = ?";
    public static final String SELECT_ALL_TEACHERS = "SELECT * FROM teachers INNER JOIN users u ON teachers.user_id = u.id" ;
    public static final String SELECT_ALL_USERS = "SELECT * FROM users";
    public static final String UPDATE_USER_STATE = "UPDATE users SET blocked = ? WHERE id = ?";
    public static final String DELETE_JOURNAL_COURSE_FOR_USER = "DELETE FROM journal WHERE student_id = ? AND course_id = ?";
    public static final String GET_TOPICS = "SELECT * FROM topics WHERE language = ?";
    public static final String FIND_COURSE_BY_LANG = "SELECT * FROM courses WHERE language = ?";
    public static final String COUNT_USERS = "SELECT COUNT(*) AS users FROM users";
    public static final String COUNT_COURSES = "SELECT COUNT(*) AS courses FROM courses";
    public static final String GET_USERS_PORTION = "SELECT * FROM users LIMIT ? , ?";
    public static final String GET_COURSES_PORTION = "SELECT * FROM courses LIMIT ? , ?";
    public static final String FIND_COURSE_BY_NAME = "SELECT * FROM courses WHERE name = ?";
    public static final String GET_COURSES_PORTION_FOR_TEACHER = "SELECT * FROM courses WHERE teacher = ? LIMIT ? , ?";
    public static final String COUNT_COURSES_FOR_TEACHER = "SELECT COUNT(*) AS courses FROM courses WHERE teacher = ?";
    public static final String INSERT_MATERIAL = "INSERT INTO materials VALUES (?, ?,?,?, (SELECT id FROM material_type WHERE name = ?))" ;
    public static final String SELECT_MATERIALS = "SELECT * FROM materials WHERE type = (SELECT id FROM material_type WHERE name = ?)";

    private SQLQueris(){
    }


}

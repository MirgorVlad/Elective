package com.elective.db.dao;

import com.elective.db.entity.Course;
import com.elective.db.entity.Material;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {

    int MIN_DURATION = 14;
    //List<String> topicList = Arrays.asList("programming", "languages", "science", "soft skills");

    void create(Course course, String lang) throws SQLException, DBException;

    List<Course> getAll() throws SQLException, DBException;

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

    List<String> getTopicList(String language) throws SQLException;

    List<Course> findCoursesByLang(String lang) throws SQLException, DBException;

    List<Course> getCourses(int start, int total) throws DBException;

    double countCourses() throws SQLException;

    Course findCoursesByName(String name) throws SQLException, DBException;

    List<Course> getCoursesForTeacher(int teacher, int pageid, int total) throws DBException;
    double countCoursesForTeacher(int teacher) throws SQLException;

    void saveMaterial(int courseId, String title, String text, String path, String type) throws SQLException, DBException;

    List<Material> getAllMaterials(int courseId, String lection) throws SQLException, DBException;
}

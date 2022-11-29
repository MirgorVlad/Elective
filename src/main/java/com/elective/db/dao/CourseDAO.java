package com.elective.db.dao;

import com.elective.db.entity.Assignment;
import com.elective.db.entity.Course;
import com.elective.db.entity.Material;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface defines the Course DAO objects' API
 */
public interface CourseDAO {

    int MIN_DURATION = 14;
    //List<String> topicList = Arrays.asList("programming", "languages", "science", "soft skills");

    /**
     * Inserting course in database
     * @param course inserting course
     * @param lang language of course
     */
    void create(Course course, String lang) throws SQLException, DBException;

    /**
     * Get all courses from database
     */
    List<Course> getAll() throws SQLException, DBException;

    /**
     * Delete course from database by id
     * @param courseId id of deleting course
     */

    void deleteById(int courseId) throws SQLException, DBException;

    /**
     *  Find course in database by Id
     * @param id id of course
     */
    Course findById(int id) throws SQLException, DBException;

    /**
     * Updating course
     * @param course updated course
     */
    void update(Course course) throws SQLException, DBException;

    /**
     * Insert student in course table
     * @param studentId id of inserting student
     * @param courseId id for course where insert
     */
    void jointStudentToCourse(int studentId, int courseId) throws SQLException, DBException;

    /**
     * Check if student is joined
     * @param userId id of checking user
     * @param courseId course where checking
     */
    boolean isStudentJoined(int userId, int courseId) throws SQLException;

    /**
     * Delete student from course table
     * @param studentId user id for deleting
     * @param courseId course where deleting
     */
    void unfollowCourse(int studentId, int courseId) throws SQLException, DBException;

    /**
     * Select available courses for user
     * @param userId user id
     */
    List<Course> availableCourses(int userId) throws SQLException, DBException;

    /**
     * Select courses with teacher
     * @param id teacher id
     */
    List<Course> findCoursesByTeacher(int id) throws SQLException, DBException;

    /**
     * Select all students in course
     * @param courseId id of course
     */
    List<Integer> findStudentsInCourse(int courseId) throws SQLException;

    /**
     * Count students in course with id
     * @param courseId id of course
     */
    int countStudentsInCourse(int courseId) throws SQLException, DBException;

    /**
     * Select all courses with topic
     * @param topic topic name
     */
    List<Course> getCoursesByTopic(String topic) throws SQLException, DBException;

    /**
     * Get all topics
     * @param language language of topics
     */
    List<String> getTopicList(String language) throws SQLException;

    /**
     * Find course by language
     * @param lang language
     */
    List<Course> findCoursesByLang(String lang) throws SQLException, DBException;

    /**
     * Get courses with limit
     * @param start starting id
     * @param total count of courses
     */
    List<Course> getCourses(int start, int total) throws DBException, SQLException;

    /**
     * Count courses
     */
    double countCourses() throws SQLException;

    /**
     * Find course by name
     * @param name name of course
     */
    Course findCoursesByName(String name) throws SQLException, DBException;

    /**
     * Get courses available for teacher for pagination
     * @param teacher teacher id
     * @param pageid page number
     * @param total count of courses
     */
    List<Course> getCoursesForTeacher(int teacher, int pageid, int total) throws DBException;

    /**
     * Count available courses for teacher
     * @param teacher teacher id
     */
    double countCoursesForTeacher(int teacher) throws SQLException;

    /**
     * Saving material for course
     * @param courseId  course id
     * @param title title
     * @param text description
     * @param path path to material
     * @param type type
     */
    void saveMaterial(int courseId, String title, String text, String path, String type) throws SQLException, DBException;

    /**
     * Get all materials from course
     * @param courseId course id
     * @param lection name
     * @return
     * @throws SQLException
     * @throws DBException
     */
    List<Material> getAllMaterials(int courseId, String lection) throws SQLException, DBException;

    /**
     * Find material by name
     * @param courseId course id
     * @param material material name
     * @param type type
     */
    Material findMaterialByName(int courseId, String material, String type) throws SQLException, DBException;

    /**
     * Delete material
     * @param courseId course id
     * @param material material name
     */
    void deleteMaterial(int courseId, String material) throws SQLException, DBException;

    /**
     * Add material with assignment type
     * @param assignment assignment
     */
    void addAssignment(Assignment assignment) throws SQLException, DBException;

    /**
     * Get all assignments for course
     * @param courseId course id
     */
    List<Assignment> getAllAssignments(int courseId) throws SQLException, DBException;

    /**
     * Find assignment by name
     * @param courseId course id
     * @param materialName assignment name
     */
    Assignment findAssignmentByName(int courseId, String materialName) throws SQLException, DBException;

    /**
     * Find material with type solution by name
     * @param courseId course id
     * @param materialName solution name
     * @param studentId student id
     */
    Assignment findSolutionByName(int courseId, String materialName, int studentId) throws SQLException, DBException;

    /**
     * Delete assignment
     * @param courseId course id
     * @param material assignment name
     */
    void deleteAssignment(int courseId, String material) throws SQLException, DBException;

    /**
     * Get all solutions
     * @param courseId course id
     * @param materialName solution name
     */
    List<Assignment> getSolutions(int courseId, String materialName) throws SQLException, DBException;
}

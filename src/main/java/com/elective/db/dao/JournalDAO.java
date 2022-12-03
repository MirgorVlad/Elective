package com.elective.db.dao;

import com.elective.db.entity.Journal;

import java.sql.Date;
import java.sql.SQLException;
/**
 * Interface defines the Journal DAO objects' API
 */
public interface JournalDAO {
    /**
     * Set grade for user in course
     * @param journal journal entity
     */
    void setGrade(Journal journal) throws SQLException, DBException;

    /**
     * Get grade for user in course
     * @param courseId id of course
     * @param studentId user's id
     * @param date date when grade
     */
    int getGrade(int courseId, int studentId, Date date) throws SQLException, DBException;

    /**
     * Calculate total grade for course
     * @param courseId course id
     * @param studentId user's id
     */
    int sumOfStudentGrades(int courseId, int studentId) throws SQLException;

    /**
     * Delete info about user from course
     * @param userId user's id
     * @param courseId course id
     */
    void deleteData(int userId, int courseId) throws SQLException;
}

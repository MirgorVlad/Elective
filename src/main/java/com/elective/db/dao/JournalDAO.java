package com.elective.db.dao;

import com.elective.db.entity.Journal;

import java.sql.Date;
import java.sql.SQLException;

public interface JournalDAO {
    void setGrade(Journal journal) throws SQLException, DBException;
    int getGrade(int courseId, int studentId, Date date) throws SQLException, DBException;
    int sumOfStudentGrades(int courseId, int studentId) throws SQLException;
}

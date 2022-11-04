package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.JournalDAO;
import com.elective.db.entity.Journal;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MysqlJournalDAO implements JournalDAO {
    static Logger log = LogManager.getLogger(MysqlJournalDAO.class);

    Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    @Override
    public void setGrade(Journal journal) throws SQLException, DBException {
        PreparedStatement pstmt=null;
        try(Connection con = getConnection()){
            if(getGrade(journal.getCourse().getId(), journal.getStudent().getId(), journal.getDate()) == 0){
                pstmt = con.prepareStatement(SQLQueris.INSERT_GRADE);
                int k = 1;
                pstmt.setInt(k++, journal.getCourse().getId());
                pstmt.setInt(k++, journal.getStudent().getId());
                pstmt.setDate(k++, journal.getDate());
                pstmt.setInt(k++, journal.getGrade());

                if (pstmt.executeUpdate() == 0) {
                    log.log(Level.WARN, "Cannot insert grade");
                    throw new DBException("Cannot insert grade");
                }
                log.log(Level.DEBUG, "Set grade: " + journal);
            } else {
                pstmt = con.prepareStatement(SQLQueris.UPDATE_GRADE);
                int k = 1;
                pstmt.setInt(k++, journal.getGrade());
                pstmt.setInt(k++, journal.getCourse().getId());
                pstmt.setInt(k++, journal.getStudent().getId());
                pstmt.setDate(k++, journal.getDate());

                if (pstmt.executeUpdate() == 0) {
                    throw new DBException("Cannot update grade");
                }
            }
        } finally {
            if(pstmt != null){
                pstmt.close();
            }
        }
    }

    @Override
    public int getGrade(int courseId, int studentId, Date date) throws SQLException, DBException {
        ResultSet rs = null;
        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_GRADE_BY_KEY)) {
            int k = 1;
            pstmt.setInt(k++, courseId);
            pstmt.setInt(k++, studentId);
            pstmt.setDate(k++, date);
            rs = pstmt.executeQuery();

            while (rs.next()){
                return rs.getInt("grade");
            }
            log.log(Level.DEBUG, "Cannot get grade from course " + courseId + "; student " + studentId + "; date " + date);
        }
        finally {
            if(rs != null)
                rs.close();
        }
        return 0;
    }

    @Override
    public int sumOfStudentGrades(int courseId, int studentId) throws SQLException {
        ResultSet rs = null;
        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.SUM_OF_STUDENT_GRADE)) {
            int k = 1;
            pstmt.setInt(k++, courseId);
            pstmt.setInt(k++, studentId);
            rs = pstmt.executeQuery();

            if (rs.next()){
                System.out.println(rs.getInt("sum(grade)"));
                return rs.getInt("sum(grade)");
            }
            log.log(Level.DEBUG, "Sum of grades for " + studentId + " from course " + courseId);
        }
        finally {
            if(rs != null)
                rs.close();
        }
        return 0;
    }

    @Override
    public void deleteData(int userId, int courseId) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.DELETE_JOURNAL_COURSE_FOR_USER)) {
                pstmt.setInt(1, userId);
                pstmt.setInt(2, courseId);
                int res = pstmt.executeUpdate();
                log.log(Level.DEBUG, "Deleted " + res + " rows from journal: " + courseId + " for student: " + userId);
        }
    }
}

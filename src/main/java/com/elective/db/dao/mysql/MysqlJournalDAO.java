package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.JournalDAO;
import com.elective.db.entity.Journal;

import java.sql.*;

public class MysqlJournalDAO implements JournalDAO {
    @Override
    public void setGrade(Journal journal) throws SQLException, DBException {
        PreparedStatement pstmt=null;
        try(Connection con = ConnectionFactory.getConnection()){
            if(getGrade(journal.getCourse().getId(), journal.getStudent().getId(), journal.getDate()) == 0){
                pstmt = con.prepareStatement(SQLQueris.INSERT_GRADE);
                int k = 1;
                pstmt.setInt(k++, journal.getCourse().getId());
                pstmt.setInt(k++, journal.getStudent().getId());
                pstmt.setDate(k++, journal.getDate());
                pstmt.setInt(k++, journal.getGrade());

                if (pstmt.executeUpdate() == 0) {
                    throw new DBException("Cannot insert grade");
                }
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
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_GRADE_BY_KEY)) {
            int k = 1;
            pstmt.setInt(k++, courseId);
            pstmt.setInt(k++, studentId);
            pstmt.setDate(k++, date);
            rs = pstmt.executeQuery();

            while (rs.next()){
                return rs.getInt("grade");
            }
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
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.SUM_OF_STUDENT_GRADE)) {
            int k = 1;
            pstmt.setInt(k++, courseId);
            pstmt.setInt(k++, studentId);
            rs = pstmt.executeQuery();

            if (rs.next()){
                System.out.println(rs.getInt("sum(grade)"));
                return rs.getInt("sum(grade)");
            }
        }
        finally {
            if(rs != null)
                rs.close();
        }
        return 0;
    }
}

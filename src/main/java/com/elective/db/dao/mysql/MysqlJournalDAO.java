package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.JournalDAO;
import com.elective.db.entity.Journal;

import java.sql.*;

public class MysqlJournalDAO implements JournalDAO {
    @Override
    public void setGrade(Journal journal) throws SQLException, DBException {
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.INSERT_GRADE)){

            int k = 1;
            pstmt.setInt(k++, journal.getCourse().getId());
            pstmt.setInt(k++, journal.getStudent().getId());
            pstmt.setDate(k++, journal.getDate());
            pstmt.setInt(k++, journal.getGrade());

            if (pstmt.executeUpdate() == 0) {
                throw new DBException("Cannot insert grade");
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

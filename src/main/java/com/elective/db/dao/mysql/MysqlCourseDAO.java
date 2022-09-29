package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import java.sql.*;

public class MysqlCourseDAO implements CourseDAO {
    @Override
    public void create(Course course) throws SQLException, DBException {
        ResultSet rs = null;
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.INSERT_COURSE,
                    Statement.RETURN_GENERATED_KEYS)){

            User teacher = getTeacher(con, course.getTeacherEmail());

            int k = 1;
            pstmt.setString(k++, course.getName());
            pstmt.setString(k++, course.getDescription());
            pstmt.setDate(k++, course.getStartDate());
            pstmt.setDate(k++, course.getFinishDate());
            pstmt.setInt(k++, teacher.getId());

            if (pstmt.executeUpdate() > 0) {
                setCourseID(rs, pstmt, course);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    private User getTeacher(Connection con, String email) throws DBException, SQLException {
        UserDAO userDAO = MysqlDAOFactory.getInstance().getUserDAO();
        User user = userDAO.find(email);
        System.out.println(user);
        if(user != null){
            if(userDAO.getTeacher(con, user))
                return user;
            else
                throw new DBException(email + " is not teacher");
        } else {
            throw new DBException("Cannot find user");
        }
    }

    private void setCourseID(ResultSet rs, PreparedStatement pstmt, Course course) throws SQLException {
        rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int courseID = rs.getInt(1);
            course.setId(courseID);
        }
    }
}

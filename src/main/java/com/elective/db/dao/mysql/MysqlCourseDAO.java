package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCourseDAO implements CourseDAO {

    UserDAO userDAO = MysqlDAOFactory.getInstance().getUserDAO();
    @Override
    public void create(Course course, User teacher) throws SQLException, DBException {
        ResultSet rs = null;
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.INSERT_COURSE,
                    Statement.RETURN_GENERATED_KEYS)){

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

    @Override
    public List<Course> getAll() throws SQLException, DBException {
        List<Course> courses = new ArrayList<>();
        try(Connection con = ConnectionFactory.getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(SQLQueris.SELECT_ALL_COURSES)) {
            while (rs.next()){
                courses.add(getCourse(rs));
            }
        }
        return courses;
    }

    @Override
    public Course findById(int id) throws SQLException, DBException {
        ResultSet rs = null;
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_COURSE_BY_ID)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()){
                return getCourse(rs);
            }
        }
        finally {
            if(rs != null)
                rs.close();
        }
        return null;
    }

    @Override
    public void update(Course course, User teacher) throws SQLException, DBException {
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.UPDATE_COURSE)){

            int k = 1;
            pstmt.setString(k++, course.getName());
            pstmt.setString(k++, course.getDescription());
            pstmt.setDate(k++, course.getStartDate());
            pstmt.setDate(k++, course.getFinishDate());
            pstmt.setInt(k++, teacher.getId());
            pstmt.setInt(k++, course.getId());

            if (pstmt.executeUpdate() == 0) {
                throw new DBException("Can't update course");
            }
        }
    }

    private Course getCourse(ResultSet rs) throws SQLException, DBException {
        Course course = new Course();
        int teacherId = rs.getInt("teacher");
        User teacher = userDAO.findById(teacherId);

        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));
        course.setStartDate(rs.getDate("start"));
        course.setFinishDate(rs.getDate("finish"));
        course.setTeacher(teacher);
        return course;
    }

    public User getTeacher(User user) throws DBException, SQLException {
        System.out.println(user);
        try(Connection con = ConnectionFactory.getConnection()) {
            if (user != null) {
                if (userDAO.isTeacher(con, user))
                    return user;
                else
                    throw new DBException(user.getEmail() + " is not teacher");
            } else {
                throw new DBException("Cannot find user");
            }
        }
    }

    @Override
    public void deleteById(int courseId) throws SQLException, DBException {
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQLQueris.DELETE_COURSE_BY_ID)){
            pstmt.setInt(1, courseId);
            if(pstmt.executeUpdate() == 0){
                throw new DBException("Cannot delete course");
            }
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

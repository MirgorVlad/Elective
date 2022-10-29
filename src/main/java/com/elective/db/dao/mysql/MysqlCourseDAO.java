package com.elective.db.dao.mysql;

import com.elective.db.dao.ConnectionFactory;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCourseDAO implements CourseDAO {
    static Logger log = LogManager.getLogger(MysqlCourseDAO.class);

    UserDAO userDAO = MysqlDAOFactory.getInstance().getUserDAO();

    @Override
    public void create(Course course) throws SQLException, DBException {
        ResultSet rs = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.INSERT_COURSE,
                     Statement.RETURN_GENERATED_KEYS)) {

            int k = 1;
            pstmt.setString(k++, course.getName());
            pstmt.setString(k++, course.getDescription());
            pstmt.setDate(k++, course.getStartDate());
            pstmt.setDate(k++, course.getFinishDate());
            pstmt.setInt(k++, course.getTeacher().getId());
            pstmt.setString(k++, course.getTopic());

            if (pstmt.executeUpdate() > 0) {
                log.log(Level.DEBUG, "Course " + course.getName() + " created");
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
        try (Connection con = ConnectionFactory.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(SQLQueris.SELECT_ALL_COURSES)) {
            while (rs.next()) {
                courses.add(getCourse(rs));
            }
            log.log(Level.DEBUG, "Get all courses");
        }
        return courses;
    }

    @Override
    public Course findById(int id) throws SQLException, DBException {
        ResultSet rs = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_COURSE_BY_ID)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();


            while (rs.next()) {
                log.log(Level.DEBUG, "Find course by id: " + id);
                return getCourse(rs);
            }
        } finally {
            if (rs != null)
                rs.close();
        }
        return null;
    }

    @Override
    public void update(Course course) throws SQLException, DBException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.UPDATE_COURSE)) {

            int k = 1;
            pstmt.setString(k++, course.getName());
            pstmt.setString(k++, course.getDescription());
            pstmt.setDate(k++, course.getStartDate());
            pstmt.setDate(k++, course.getFinishDate());
            pstmt.setInt(k++, course.getTeacher().getId());
            pstmt.setString(k++, course.getTopic());
            pstmt.setInt(k++, course.getId());

            log.log(Level.DEBUG, "Update course " + course.getName());

            if (pstmt.executeUpdate() == 0) {
                log.log(Level.WARN, "Cannot update course " + course.getName());
                throw new DBException("Can't update course");
            }
        }
    }

    @Override
    public void jointStudentToCourse(int studentId, int courseId) throws SQLException, DBException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.JOIN_TO_COURSE)) {

            int k = 1;
            pstmt.setInt(k++, courseId);
            pstmt.setInt(k++, studentId);

            log.log(Level.DEBUG, "Join student " + studentId + " to course " + courseId);

            if (pstmt.executeUpdate() == 0) {
                log.log(Level.WARN, "Cannot join student to course");
                throw new DBException("Cant join student to course");
            }
        }
    }

    @Override
    public boolean isStudentJoined(int userId, int courseId) throws SQLException {
        ResultSet rs = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_STUDENT_IN_COURSE_BY_ID)) {
            int k = 1;
            pstmt.setInt(k++, userId);
            pstmt.setInt(k++, courseId);
            rs = pstmt.executeQuery();

            log.log(Level.DEBUG, "Student " + userId + " is joined to " + courseId);

            return rs.next();
        } finally {
            if (rs != null)
                rs.close();
        }
    }

    @Override
    public void unfollowCourse(int studentId, int courseId) throws SQLException, DBException {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.UNFOLLOW_COURSE)) {

            int k = 1;
            pstmt.setInt(k++, studentId);
            pstmt.setInt(k++, courseId);

            log.log(Level.DEBUG, "Unfollow user " + studentId + " from course " + courseId);

            if (pstmt.executeUpdate() == 0) {
                log.log(Level.WARN, "Cannot unfollow user " + studentId + " from course " + courseId);
                throw new DBException("Cannot unfollow from course");
            }
        }
    }

    @Override
    public List<Course> availableCourses(int userId) throws SQLException, DBException {
        List<Course> courses = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement statement = con.prepareStatement(SQLQueris.SELECT_ALL_AVAILABLE_COURSES_FOR_STUDENT)) {
            statement.setInt(1, userId);
            rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(findById(rs.getInt("course_id")));
            }
            log.log(Level.DEBUG, "Available courses for user " + userId);
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return courses;
    }

    @Override
    public List<Course> findCoursesByTeacher(int id) throws SQLException, DBException {
        List<Course> courses = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_COURSE_BY_TEACHER_ID)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                courses.add(getCourse(rs));
            }

            log.log(Level.DEBUG, "Find course by teacher " + id);

        } finally {
            if (rs != null)
                rs.close();
        }
        return courses;
    }

    @Override
    public List<Integer> findStudentsInCourse(int courseId) throws SQLException {
        List<Integer> usersId = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement statement = con.prepareStatement(SQLQueris.SELECT_ALL_STUDENTS_IN_COURSE)) {
            statement.setInt(1, courseId);
            rs = statement.executeQuery();
            while (rs.next()) {
                usersId.add(rs.getInt("id"));
            }

            log.log(Level.DEBUG, "Find students in course " + courseId);

        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return usersId;
    }

    public int countStudentsInCourse(int courseId) throws SQLException, DBException {
        ResultSet rs = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement statement = con.prepareStatement(SQLQueris.COUNT_STUDENTS_IN_COURSE)) {
            statement.setInt(1, courseId);
            rs = statement.executeQuery();
            log.log(Level.DEBUG, "Count students in course " + courseId);
            if (rs.next()) {
                return rs.getInt("students");
            } else {
                log.log(Level.WARN, "Cannot find students in course " + courseId);
                throw new DBException("Cannot find students");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    @Override
    public List<Course> getCoursesByTopic(String topic) throws SQLException, DBException {
        List<Course> courses = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.FIND_COURSE_BY_TOPIC)) {
            pstmt.setString(1, topic);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                courses.add(getCourse(rs));
            }

            log.log(Level.DEBUG, "Get courses by topic " + topic);

        } finally {
            if (rs != null)
                rs.close();
        }
        return courses;
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
        course.setTopic(rs.getString("topic"));
        return course;
    }

    public User getTeacher(User user) throws DBException, SQLException {
        System.out.println(user);
        try (Connection con = ConnectionFactory.getConnection()) {
            if (user != null) {
                if (MysqlUserDAO.isTeacher(con, user))
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
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLQueris.DELETE_COURSE_BY_ID)) {
            pstmt.setInt(1, courseId);

            log.log(Level.DEBUG, "Delete course " + courseId);

            if (pstmt.executeUpdate() == 0) {
                log.log(Level.WARN, "Cannot delete  course " + courseId);
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

    public static Course createCourse(int id, String name, String topic, String desc, Date startDate, Date finishDate, User teacher) {
        Course course = new Course();

        course.setId(id);
        course.setName(name);
        course.setTopic(topic);
        course.setDescription(desc);
        course.setTeacher(teacher);
        course.setStartDate(startDate);
        course.setFinishDate(finishDate);
        return course;
    }

}

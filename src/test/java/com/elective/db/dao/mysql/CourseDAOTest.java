package com.elective.db.dao.mysql;

import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseDAOTest {

    private int id = 1;
    private String name = "Test Course";
    private String description = "Test description";
    private Date start = new Date(100000);
    private Date finish = new Date(10000000);
    private User teacher = createUser("Teacher", "Test", "teacher@gmail.com",
            "password", "teacher");
    private String topic = "test topic";

    Course expectedCourse = createCourse(id, name, description, start, finish, teacher, topic);

    @Test
    void findCourseByIdTest() throws DBException, SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getInt("id")).thenReturn(id);
        when(rs.getString("name")).thenReturn(name);
        when(rs.getString("description")).thenReturn(description);
        when(rs.getDate("start")).thenReturn(start);
        when(rs.getDate("finish")).thenReturn(finish);
        when(rs.getString("topic")).thenReturn(topic);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_COURSE_BY_ID)).thenReturn(pstmt);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(teacher.getId())).thenReturn(teacher);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        when(courseDAO.getConnection()).thenReturn(con);
        when(courseDAO.findById(id)).thenCallRealMethod();
        when(courseDAO.getUserDAO()).thenReturn(userDAO);

        Course actualCourse = courseDAO.findById(id);

        assertEquals(expectedCourse, actualCourse);
    }

    private Course createCourse(int id, String name, String description, Date start, Date finish, User teacher, String topic){
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setDescription(description);
        course.setStartDate(start);
        course.setFinishDate(finish);
        course.setTeacher(teacher);
        course.setTopic(topic);
        return course;
    }

    private User createUser(String fname, String lname, String email, String password, String role) {
        User user = new User();
        user.setId(2);
        user.setEmail(email);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

}

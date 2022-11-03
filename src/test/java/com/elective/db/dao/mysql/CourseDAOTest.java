package com.elective.db.dao.mysql;

import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
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

    private final Random random = new Random();

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
        when(rs.getInt("teacher")).thenReturn(teacher.getId());

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_COURSE_BY_ID)).thenReturn(pstmt);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(teacher.getId())).thenReturn(teacher);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        when(courseDAO.getConnection()).thenReturn(con);
        when(courseDAO.findById(id)).thenCallRealMethod();
        when(courseDAO.getCourse(rs)).thenCallRealMethod();
        when(courseDAO.getUserDAO()).thenReturn(userDAO);

        Course actualCourse = courseDAO.findById(id);

        assertEquals(expectedCourse, actualCourse);
    }

    @Test
    void findCourseByTeacherTest() throws DBException, SQLException {
        User teacher = createUser("Teacher", "Test", "email@gmail.com", "password", "teacher");
        Course course1 = createCourse(1, "Course1", "description1", new Date(123), new Date(12345),
                teacher, "topic1");
        Course course2 = createCourse(2, "Course2", "description2", new Date(123), new Date(12345),
                teacher, "topic2");
        Course course3 = createCourse(3, "Course3", "description3", new Date(123), new Date(12345),
                teacher, "topic3");

        List<Course> expectedCourseList = List.of(course1, course2, course3);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id"))
                .thenReturn(course1.getId())
                .thenReturn(course2.getId())
                .thenReturn(course3.getId());
        when(rs.getString("name"))
                .thenReturn(course1.getName())
                .thenReturn(course2.getName())
                .thenReturn(course3.getName());
        when(rs.getString("description"))
                .thenReturn(course1.getDescription())
                .thenReturn(course2.getDescription())
                .thenReturn(course3.getDescription());
        when(rs.getDate("start"))
                .thenReturn(course1.getStartDate())
                .thenReturn(course2.getStartDate())
                .thenReturn(course3.getStartDate());
        when(rs.getDate("finish"))
                .thenReturn(course1.getFinishDate())
                .thenReturn(course2.getFinishDate())
                .thenReturn(course3.getFinishDate());
        when(rs.getString("topic"))
                .thenReturn(course1.getTopic())
                .thenReturn(course2.getTopic())
                .thenReturn(course3.getTopic());
        when(rs.getInt("teacher"))
                .thenReturn(course1.getTeacher().getId())
                .thenReturn(course2.getTeacher().getId())
                .thenReturn(course3.getTeacher().getId());

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(teacher.getId()))
                .thenReturn(teacher);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_COURSE_BY_TEACHER_ID)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        when(courseDAO.getConnection()).thenReturn(con);
        when(courseDAO.findCoursesByTeacher(teacher.getId())).thenCallRealMethod();
        when(courseDAO.getCourse(rs)).thenCallRealMethod();
        when(courseDAO.getUserDAO()).thenReturn(userDAO);

        List<Course> actualCourseList = courseDAO.findCoursesByTeacher(teacher.getId());

        assertEquals(expectedCourseList, actualCourseList);
    }

    @Test
    void findAllCoursesTest() throws DBException, SQLException {
        int length = 3;
        List<Course> expectedCourseList = generateCourseList(length);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        when(rs.getInt("id"))
                .thenReturn(expectedCourseList.get(0).getId())
                .thenReturn(expectedCourseList.get(1).getId())
                .thenReturn(expectedCourseList.get(2).getId());
        when(rs.getString("name"))
                .thenReturn(expectedCourseList.get(0).getName())
                .thenReturn(expectedCourseList.get(1).getName())
                .thenReturn(expectedCourseList.get(2).getName());
        when(rs.getString("description"))
                .thenReturn(expectedCourseList.get(0).getDescription())
                .thenReturn(expectedCourseList.get(1).getDescription())
                .thenReturn(expectedCourseList.get(2).getDescription());
        when(rs.getDate("start"))
                .thenReturn(expectedCourseList.get(0).getStartDate())
                .thenReturn(expectedCourseList.get(1).getStartDate())
                .thenReturn(expectedCourseList.get(2).getStartDate());
        when(rs.getDate("finish"))
                .thenReturn(expectedCourseList.get(0).getFinishDate())
                .thenReturn(expectedCourseList.get(1).getFinishDate())
                .thenReturn(expectedCourseList.get(2).getFinishDate());
        when(rs.getString("topic"))
                .thenReturn(expectedCourseList.get(0).getTopic())
                .thenReturn(expectedCourseList.get(1).getTopic())
                .thenReturn(expectedCourseList.get(2).getTopic());
        when(rs.getInt("teacher"))
                .thenReturn(expectedCourseList.get(0).getTeacher().getId())
                .thenReturn(expectedCourseList.get(1).getTeacher().getId())
                .thenReturn(expectedCourseList.get(2).getTeacher().getId());

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(expectedCourseList.get(0).getTeacher().getId()))
                .thenReturn(expectedCourseList.get(0).getTeacher());
        when(userDAO.findById(expectedCourseList.get(1).getTeacher().getId()))
                .thenReturn(expectedCourseList.get(1).getTeacher());
        when(userDAO.findById(expectedCourseList.get(2).getTeacher().getId()))
                .thenReturn(expectedCourseList.get(2).getTeacher());

        Statement statement = mock(Statement.class);
        when(statement.executeQuery(SQLQueris.SELECT_ALL_COURSES)).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.createStatement()).thenReturn(statement);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        when(courseDAO.getConnection()).thenReturn(con);
        when(courseDAO.getAll()).thenCallRealMethod();
        when(courseDAO.getCourse(rs)).thenCallRealMethod();
        when(courseDAO.getUserDAO()).thenReturn(userDAO);

        List<Course> actualCourseList = courseDAO.getAll();

        assertEquals(expectedCourseList, actualCourseList);
    }

    @Test
    void insertCourseThrowExceptionTest() throws SQLException, DBException {
        String lang = "ua";

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.INSERT_COURSE, Statement.RETURN_GENERATED_KEYS)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        Mockito.doCallRealMethod().when(courseDAO).create(expectedCourse, lang);
        when(courseDAO.getConnection()).thenReturn(con);
        when(courseDAO.findById(id)).thenReturn(expectedCourse);

        assertThrows(DBException.class,
                () ->  courseDAO.create(expectedCourse, lang), "Cannot insert course");
    }

    @Test
    void updateCourseThrowExceptionTest() throws SQLException, DBException {
        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.UPDATE_COURSE)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        Mockito.doCallRealMethod().when(courseDAO).update(expectedCourse);
        when(courseDAO.getConnection()).thenReturn(con);


        assertThrows(DBException.class,
                () ->  courseDAO.update(expectedCourse), "Can't update course");
    }

    @Test
    void unfollowCourseThrowExceptionTest() throws SQLException, DBException {
        int userId = random.nextInt(100);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.UNFOLLOW_COURSE)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        Mockito.doCallRealMethod().when(courseDAO).unfollowCourse(userId, id);
        when(courseDAO.getConnection()).thenReturn(con);


        assertThrows(DBException.class,
                () ->  courseDAO.unfollowCourse(userId, id), "Cannot unfollow from course");
    }

    @Test
    void joinToCourseThrowExceptionTest() throws SQLException, DBException {
        int userId = random.nextInt(100);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.JOIN_TO_COURSE)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        Mockito.doCallRealMethod().when(courseDAO).jointStudentToCourse(userId, id);
        when(courseDAO.getConnection()).thenReturn(con);


        assertThrows(DBException.class,
                () ->  courseDAO.jointStudentToCourse(userId, id), "Cant join student to course");
    }


    @Test
    void isStudentJoinedToCourseTest() throws SQLException {
       int userId = random.nextInt(100);
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_STUDENT_IN_COURSE_BY_ID)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        when(courseDAO.getConnection()).thenReturn(con);
        when(courseDAO.isStudentJoined(userId, id)).thenCallRealMethod();

        assertTrue(courseDAO.isStudentJoined(userId, id));
    }

    @Test
    void countStudentsInCourseTest() throws SQLException, DBException {
        int expectedCount = random.nextInt(100);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getInt("students")).thenReturn(expectedCount);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.COUNT_STUDENTS_IN_COURSE)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        when(courseDAO.countStudentsInCourse(id)).thenCallRealMethod();
        when(courseDAO.getConnection()).thenReturn(con);

        int actualCount = courseDAO.countStudentsInCourse(id);

        assertEquals(expectedCount, actualCount);
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

    private Course generateRandomCourse() {
        Random random = new Random();
        int id = random.nextInt(100);
        String name = generateString(10);
        String description = generateString(20);
        String topic = "topic";
        User teacher = createUser("name", "lname", "email@gmail.com", "password", "teacher");
        Date start = new Date(12345);
        Date finish = new Date(1234567);

        return createCourse(id, name, description, start, finish, teacher, topic);
    }

    private List<Course> generateCourseList(int length){
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            courseList.add(generateRandomCourse());
        }
        return courseList;
    }

    private String generateString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}

package com.elective.db.dao.mysql;

import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseDAOTest {

    private final Random rand = new Random();

    @Test
    void findCourseByIdTest() throws DBException, SQLException {
        Course expectedCourse = createRandomCourse(null);
        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getInt("id")).thenReturn(expectedCourse.getId());
        when(rs.getString("name")).thenReturn(expectedCourse.getName());
        when(rs.getString("description")).thenReturn(expectedCourse.getDescription());
        when(rs.getDate("start")).thenReturn(expectedCourse.getStartDate());
        when(rs.getDate("finish")).thenReturn(expectedCourse.getFinishDate());
        when(rs.getString("topic")).thenReturn(expectedCourse.getTopic());
        when(rs.getInt("teacher")).thenReturn(expectedCourse.getTeacher().getId());

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_COURSE_BY_ID)).thenReturn(pstmt);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(expectedCourse.getTeacher().getId())).thenReturn(expectedCourse.getTeacher());

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        when(courseDAO.getConnection()).thenReturn(con);
        when(courseDAO.findById(expectedCourse.getId())).thenCallRealMethod();
        when(courseDAO.getCourse(rs)).thenCallRealMethod();
        when(courseDAO.getUserDAO()).thenReturn(userDAO);

        Course actualCourse = courseDAO.findById(expectedCourse.getId());

        assertEquals(expectedCourse, actualCourse);
    }

    @Test
    void findCourseByTeacherTest() throws DBException, SQLException {
        User teacher = createRandomUser(false, true);
        Course course1 = createRandomCourse(teacher);
        Course course2 = createRandomCourse(teacher);
        Course course3 = createRandomCourse(teacher);

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
        Course expectedCourse = createRandomCourse(null);
        String lang = "ua";

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.INSERT_COURSE, Statement.RETURN_GENERATED_KEYS)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        Mockito.doCallRealMethod().when(courseDAO).create(expectedCourse, lang);
        when(courseDAO.getConnection()).thenReturn(con);
        when(courseDAO.findById(expectedCourse.getId())).thenReturn(expectedCourse);

        assertThrows(DBException.class,
                () ->  courseDAO.create(expectedCourse, lang), "Cannot insert course");
    }

    @Test
    void updateCourseThrowExceptionTest() throws SQLException, DBException {
        Course expectedCourse = createRandomCourse(null);
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
        int userId = rand.nextInt(100);
        int courseId = rand.nextInt(100);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.UNFOLLOW_COURSE)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        Mockito.doCallRealMethod().when(courseDAO).unfollowCourse(userId, courseId);
        when(courseDAO.getConnection()).thenReturn(con);


        assertThrows(DBException.class,
                () ->  courseDAO.unfollowCourse(userId, courseId), "Cannot unfollow from course");
    }

    @Test
    void joinToCourseThrowExceptionTest() throws SQLException, DBException {
        int userId = rand.nextInt(100);
        int courseId = rand.nextInt(100);
        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.JOIN_TO_COURSE)).thenReturn(pstmt);

        MysqlCourseDAO courseDAO = mock(MysqlCourseDAO.class);
        Mockito.doCallRealMethod().when(courseDAO).jointStudentToCourse(userId, courseId);
        when(courseDAO.getConnection()).thenReturn(con);


        assertThrows(DBException.class,
                () ->  courseDAO.jointStudentToCourse(userId, courseId), "Cant join student to course");
    }


    @Test
    void isStudentJoinedToCourseTest() throws SQLException {
       int userId = rand.nextInt(100);
       int courseId = rand.nextInt(100);
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
        when(courseDAO.isStudentJoined(userId, courseId)).thenCallRealMethod();

        assertTrue(courseDAO.isStudentJoined(userId, courseId));
    }

    @Test
    void countStudentsInCourseTest() throws SQLException, DBException {
        int expectedCount = rand.nextInt(100);
        int courseId = rand.nextInt(100);

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
        when(courseDAO.countStudentsInCourse(courseId)).thenCallRealMethod();
        when(courseDAO.getConnection()).thenReturn(con);

        int actualCount = courseDAO.countStudentsInCourse(courseId);

        assertEquals(expectedCount, actualCount);
    }

    private Course createRandomCourse(User teacher) {
        Course course = new Course();
        course.setId(rand.nextInt(100));
        course.setName(generateString(10));
        course.setDescription(generateString(20));
        course.setTopic("topic" + rand.nextInt(10));
        course.setStartDate(new Date(rand.nextLong(12345)));
        course.setFinishDate(new Date(course.getStartDate().getTime() + rand.nextLong(12345)));

        if(teacher != null)
            course.setTeacher(teacher);
        else
            course.setTeacher(createRandomUser(false, true));

        return course;
    }

    private User createRandomUser(boolean student, boolean teacher) {
        User user = new User();
        user.setId(rand.nextInt(100));
        user.setFirstName(generateString(10));
        user.setLastName(generateString(10));
        user.setPassword(generateString(10));
        user.setEmail(user.getFirstName() + "@mail.com");
        user.setRole(student ? "student" : teacher ? "teacher" : rand.nextBoolean() ? "student" : "teacher");

        return user;
    }

    private List<Course> generateCourseList(int length){
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            courseList.add(createRandomCourse(null));
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

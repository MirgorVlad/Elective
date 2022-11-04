package com.elective.db.dao.mysql;

import com.elective.db.dao.DBException;
import com.elective.db.entity.Course;
import com.elective.db.entity.Journal;
import com.elective.db.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JournalDAOTest {

    private final Random rand  = new Random();

    @Test
    void getGradeTest() throws SQLException, DBException {

        int courseId = rand.nextInt(100);
        int userId = rand.nextInt(100);
        int expectedGrade = rand.nextInt(10);
        Date date = new Date(rand.nextLong(100000000));

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getInt("grade")).thenReturn(expectedGrade);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.FIND_GRADE_BY_KEY)).thenReturn(pstmt);

        MysqlJournalDAO journalDAO = mock(MysqlJournalDAO.class);
        when(journalDAO.getConnection()).thenReturn(con);
        when(journalDAO.getGrade(courseId, userId, date)).thenCallRealMethod();

        int actualGrade = journalDAO.getGrade(courseId, userId, date);

        assertEquals(expectedGrade, actualGrade);
    }

    @Test
    void setGradeTest() throws SQLException, DBException {

       Journal journal = createRandomJournal();

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeUpdate()).thenReturn(0);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.INSERT_GRADE)).thenReturn(pstmt);

        MysqlJournalDAO journalDAO = mock(MysqlJournalDAO.class);
        when(journalDAO.getConnection()).thenReturn(con);
        Mockito.doCallRealMethod().when(journalDAO).setGrade(journal);
        when(journalDAO.getGrade(journal.getCourse().getId(), journal.getStudent().getId(), journal.getDate())).thenReturn(0);

        assertThrows(DBException.class,
                () ->  journalDAO.setGrade(journal), "Cannot update grade");
    }

    @Test
    void studentGradesSumForCourseTest() throws SQLException, DBException {

        int courseId = rand.nextInt(100);
        int userId = rand.nextInt(100);
        int expectedSum = rand.nextInt(100);

        ResultSet rs = mock(ResultSet.class);
        when(rs.next())
                .thenReturn(true)
                .thenReturn(false);
        when(rs.getInt("sum(grade)")).thenReturn(expectedSum);

        PreparedStatement pstmt = mock(PreparedStatement.class);
        when(pstmt.executeQuery()).thenReturn(rs);

        Connection con = mock(Connection.class);
        when(con.prepareStatement(SQLQueris.SUM_OF_STUDENT_GRADE)).thenReturn(pstmt);

        MysqlJournalDAO journalDAO = mock(MysqlJournalDAO.class);
        when(journalDAO.getConnection()).thenReturn(con);
        when(journalDAO.sumOfStudentGrades(courseId, userId)).thenCallRealMethod();

        int actualSum = journalDAO.sumOfStudentGrades(courseId, userId);

        assertEquals(expectedSum, actualSum);
    }

    private Journal createRandomJournal(){
        Journal journal = new Journal();

        journal.setCourse(createRandomCourse());
        journal.setStudent(createRandomUser(true, false));
        journal.setGrade(rand.nextInt(10));
        journal.setDate(new Date(rand.nextLong(12345)));

        return journal;
    }

    private Course createRandomCourse() {
        Course course = new Course();
        course.setId(rand.nextInt(100));
        course.setName(generateString(10));
        course.setDescription(generateString(20));
        course.setTopic("topic" + rand.nextInt(10));
        course.setTeacher(createRandomUser(false, true));
        course.setStartDate(new Date(rand.nextLong(12345)));
        course.setFinishDate(new Date(course.getStartDate().getTime() + rand.nextLong(12345)));

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

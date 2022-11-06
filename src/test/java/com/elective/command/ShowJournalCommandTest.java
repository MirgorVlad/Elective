package com.elective.command;

import com.elective.Generator;
import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowJournalCommandTest {

    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void showJournalForStudentTest() throws Exception {
        User user = generator.createRandomUser(UserDAO.STUDENT_ROLE);
        Course course = generator.createRandomCourse(null);
        String expectedPage = ReferencePages.JOURNAL_PAGE;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("courseId")).thenReturn(""+course.getId());
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(user.getId())).thenReturn(user);

        CourseDAO courseDAO = mock(CourseDAO.class);
        when(courseDAO.findById(course.getId())).thenReturn(course);
        when(courseDAO.findStudentsInCourse(course.getId())).thenReturn(new ArrayList<>());

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(ShowJournalCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void showJournalForTeacherTest() throws Exception {
        User user = generator.createRandomUser(UserDAO.TEACHER_ROLE);
        Course course = generator.createRandomCourse(null);
        String expectedPage = ReferencePages.TEACHER_JOURNAL;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("courseId")).thenReturn(""+course.getId());
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(user.getId())).thenReturn(user);

        CourseDAO courseDAO = mock(CourseDAO.class);
        when(courseDAO.findById(course.getId())).thenReturn(course);
        when(courseDAO.findStudentsInCourse(course.getId())).thenReturn(new ArrayList<>());

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(ShowJournalCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

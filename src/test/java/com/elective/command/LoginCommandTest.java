package com.elective.command;

import com.elective.Generator;
import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginCommandTest {
    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void studentLoginTest() throws Exception {
        User student = generator.createRandomUser(UserDAO.STUDENT_ROLE);
        String expectedPage = ReferencePages.STUDENT_PAGE;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("lang")).thenReturn("eng");

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("email")).thenReturn(student.getEmail());
        when(req.getParameter("password")).thenReturn(student.getPassword());
        when(req.getSession()).thenReturn(session);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(student.getEmail())).thenReturn(student);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(LoginCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void teacherLoginTest() throws Exception {
        User student = generator.createRandomUser(UserDAO.TEACHER_ROLE);
        String expectedPage = ReferencePages.TEACHER_PAGE;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("lang")).thenReturn("eng");

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("email")).thenReturn(student.getEmail());
        when(req.getParameter("password")).thenReturn(student.getPassword());
        when(req.getSession()).thenReturn(session);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(student.getEmail())).thenReturn(student);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(LoginCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void managerLoginTest() throws Exception {
        User student = generator.createRandomUser(UserDAO.MANAGER_ROLE);
        String expectedPage = ReferencePages.MANAGER_PAGE;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("lang")).thenReturn("eng");

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("email")).thenReturn(student.getEmail());
        when(req.getParameter("password")).thenReturn(student.getPassword());
        when(req.getSession()).thenReturn(session);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(student.getEmail())).thenReturn(student);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(LoginCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

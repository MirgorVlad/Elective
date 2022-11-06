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
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SortCoursesCommandTest {
    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void sortCoursesByName() throws Exception {
        List<Course> courseList = generator.generateCourseList(5);
        String expectedPage = ReferencePages.VIEW_COURSES_LIST;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("coursesList")).thenReturn(courseList);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("sample")).thenReturn("az");
        when(req.getParameter("method")).thenReturn("asc");
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(SortCoursesCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void sortCoursesByNameReverse() throws Exception {
        List<Course> courseList = generator.generateCourseList(5);
        String expectedPage = ReferencePages.VIEW_COURSES_LIST;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("coursesList")).thenReturn(courseList);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("sample")).thenReturn("az");
        when(req.getParameter("method")).thenReturn("desc");
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(SortCoursesCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void sortCoursesByStudentsCount() throws Exception {
        List<Course> courseList = generator.generateCourseList(5);
        String expectedPage = ReferencePages.VIEW_COURSES_LIST;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("coursesList")).thenReturn(courseList);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("sample")).thenReturn("students");
        when(req.getParameter("method")).thenReturn("asc");
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(SortCoursesCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void sortCoursesByStudentsCountReverse() throws Exception {
        List<Course> courseList = generator.generateCourseList(5);
        String expectedPage = ReferencePages.VIEW_COURSES_LIST;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("coursesList")).thenReturn(courseList);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("sample")).thenReturn("students");
        when(req.getParameter("method")).thenReturn("desc");
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(SortCoursesCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void sortCoursesByDuration() throws Exception {
        List<Course> courseList = generator.generateCourseList(5);
        String expectedPage = ReferencePages.VIEW_COURSES_LIST;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("coursesList")).thenReturn(courseList);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("sample")).thenReturn("duration");
        when(req.getParameter("method")).thenReturn("asc");
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(SortCoursesCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void sortCoursesByDurationReverse() throws Exception {
        List<Course> courseList = generator.generateCourseList(5);
        String expectedPage = ReferencePages.VIEW_COURSES_LIST;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("coursesList")).thenReturn(courseList);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("sample")).thenReturn("duration");
        when(req.getParameter("method")).thenReturn("desc");
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(SortCoursesCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

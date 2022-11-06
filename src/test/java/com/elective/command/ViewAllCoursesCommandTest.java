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
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewAllCoursesCommandTest {
    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void viewCoursesListTest() throws Exception {
        List<User> userList = generator.generateUserList(5, UserDAO.TEACHER_ROLE);
        List<Course> courseList = generator.generateCourseList(5);

        String expectedPage = ReferencePages.VIEW_COURSES_LIST;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(null);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("command")).thenReturn("viewCoursesList");
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.getAllTeachers()).thenReturn(userList);

        CourseDAO courseDAO = mock(CourseDAO.class);
        when(courseDAO.getAll()).thenReturn(courseList);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);

        Command command = mock(ViewAllCoursesCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void manageCoursesTest() throws Exception {
        List<User> userList = generator.generateUserList(5, UserDAO.TEACHER_ROLE);
        List<Course> courseList = generator.generateCourseList(5);
        User manager = generator.createRandomUser(UserDAO.MANAGER_ROLE);

        String expectedPage = ReferencePages.MANAGE_COURSES;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(manager);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("command")).thenReturn("manageCourses");
        when(req.getSession()).thenReturn(session);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.getAllTeachers()).thenReturn(userList);

        CourseDAO courseDAO = mock(CourseDAO.class);
        when(courseDAO.getAll()).thenReturn(courseList);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);

        Command command = mock(ViewAllCoursesCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

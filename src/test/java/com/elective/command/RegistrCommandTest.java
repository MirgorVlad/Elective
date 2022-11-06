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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrCommandTest {

    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void userRegistrTest() throws Exception {
        User user = generator.createRandomUser(null);
        String expectedPage = null;

        if(user.getRole().equals(UserDAO.STUDENT_ROLE))
            expectedPage = ReferencePages.STUDENT_PAGE;
        if(user.getRole().equals(UserDAO.TEACHER_ROLE))
            expectedPage = ReferencePages.TEACHER_PAGE;

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("lang")).thenReturn("eng");

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("email")).thenReturn(user.getEmail());
        when(req.getParameter("password")).thenReturn(user.getPassword());
        when(req.getParameter("fName")).thenReturn(user.getFirstName());
        when(req.getParameter("lName")).thenReturn(user.getLastName());
        when(req.getParameter("userRole")).thenReturn(user.getRole());
        when(req.getSession()).thenReturn(session);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(RegistrCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }


}

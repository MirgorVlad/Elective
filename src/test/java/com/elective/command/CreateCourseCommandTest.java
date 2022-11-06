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
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateCourseCommandTest {

    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void createCourseTest() throws Exception {
        User teacher = generator.createRandomUser(UserDAO.TEACHER_ROLE);

        LocalDate start = LocalDate.now();
        LocalDate finish = LocalDate.now().plusDays(CourseDAO.MIN_DURATION+1);

        Course course = generator.createRandomCourse(teacher, Date.valueOf(start), Date.valueOf(finish));
        String expectedPage = ReferencePages.MANAGER_PAGE;

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("name")).thenReturn(course.getName());
        when(req.getParameter("topics")).thenReturn(course.getTopic());
        when(req.getParameter("description")).thenReturn(course.getDescription());
        when(req.getParameter("teacherEmail")).thenReturn(course.getTeacher().getEmail());
        when(req.getParameter("language")).thenReturn("eng");
        when(req.getParameter("startDate")).thenReturn(course.getStartDate().toString());
        when(req.getParameter("finishDate")).thenReturn(course.getFinishDate().toString());

        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findByEmail(teacher.getEmail())).thenReturn(teacher);
        when(userDAO.isTeacher(teacher.getId())).thenReturn(true);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(CreateCourseCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

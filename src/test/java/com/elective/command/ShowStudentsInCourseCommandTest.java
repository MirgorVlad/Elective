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

public class ShowStudentsInCourseCommandTest {
    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void showStudentsInCourseTest() throws Exception {
        Course course = generator.createRandomCourse(null);
        User user = generator.createRandomUser(UserDAO.STUDENT_ROLE);
        List<User> userList = generator.generateUserList(5);

        List<Integer> userIdList = userList.stream()
                .map(User::getId)
                .toList();

        String expectedPage = ReferencePages.STUDENTS_IN_COURSE;

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("courseId")).thenReturn(""+course.getId());

        HttpServletResponse resp = mock(HttpServletResponse.class);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(user.getId())).thenReturn(user);

        CourseDAO courseDAO = mock(CourseDAO.class);
        when(courseDAO.findStudentsInCourse(course.getId())).thenReturn(userIdList);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);

        Command command = mock(ShowStudentsInCourseCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);


        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

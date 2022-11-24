package com.elective.command;

import com.elective.Generator;
import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FinalTestCommandTest {
    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void FinalTestStartingTest() throws Exception {
        User user = generator.createRandomUser(UserDAO.STUDENT_ROLE);
        Course course = generator.createRandomCourse(null);
        String date = "2022-11-22";
        String sTime = "12:00";
        String fTime = "16:00";
        List<User> userList = generator.generateUserList(5, UserDAO.STUDENT_ROLE);

        List<Integer> userIdList = userList.stream()
                .map(User::getId).toList();

        String expectedPage = ReferencePages.TEACHER_PAGE;

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("testDate")).thenReturn(date);
        when(req.getParameter("courseId")).thenReturn(""+course.getId());
        when(req.getParameter("sTime")).thenReturn(sTime);
        when(req.getParameter("fTime")).thenReturn(fTime);

        HttpServletResponse resp = mock(HttpServletResponse.class);

        CourseDAO courseDAO = mock(CourseDAO.class);
        when(courseDAO.findStudentsInCourse(course.getId())).thenReturn(userIdList);
        when(courseDAO.findById(course.getId())).thenReturn(course);

        UserDAO userDAO = mock(UserDAO.class);
        when(userDAO.findById(user.getId())).thenReturn(user);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);
        when(daoFactory.getUserDAO()).thenReturn(userDAO);

        Command command = mock(FinalTestCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

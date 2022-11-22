package com.elective.command;

import com.elective.Generator;
import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Assignment;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteMaterialCommandTest {
    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void deleteAssignmentTest() throws Exception {
        Assignment assignment = generator.createRandomAssignment(null);

        String expectedPage = "controller?command=showMaterials&courseId=" + assignment.getCourse();

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("material")).thenReturn(assignment.getName());
        when(req.getParameter("courseId")).thenReturn(""+assignment.getCourse());
        when(req.getParameter("type")).thenReturn("assignment");

        HttpServletResponse resp = mock(HttpServletResponse.class);

        CourseDAO courseDAO = mock(CourseDAO.class);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(DeleteMaterialCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

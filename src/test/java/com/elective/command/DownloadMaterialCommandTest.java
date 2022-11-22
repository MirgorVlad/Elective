package com.elective.command;

import com.elective.Generator;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.entity.Assignment;
import com.elective.db.entity.Material;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DownloadMaterialCommandTest {
    private static Random rand;
    private static Generator generator;

    @BeforeAll
    static void setup() {
        rand = new Random();
        generator = new Generator();
    }

    @Test
    void downloadLectionTest() throws Exception {
        String path =  "E:\\EPAM\\test";
        Material material = generator.createRandomMaterial(Material.LECTION, path);
        int courseId = rand.nextInt(100);

        String expectedPage = "controller?command=viewMaterial&material=" + material.getName() + "&courseId="
                + courseId + "&type=" + material.getType();

        ServletOutputStream outputStream = mock(ServletOutputStream.class);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("material")).thenReturn(material.getName());
        when(req.getParameter("courseId")).thenReturn(""+courseId);
        when(req.getParameter("type")).thenReturn(material.getType());

        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(resp.getOutputStream()).thenReturn(outputStream);

        CourseDAO courseDAO = mock(CourseDAO.class);
        when(courseDAO.findMaterialByName(courseId, material.getName(), null)).thenReturn(material);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(DownloadMaterialCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void downloadSolutionTest() throws Exception {
        String path =  "E:\\EPAM\\test";
        String materialType = Material.SOLUTION;
        Assignment assignment = generator.createRandomAssignment(path);
        int courseId = rand.nextInt(100);
        int studentId = rand.nextInt(100);

        String expectedPage = "controller?command=viewMaterial&material=" + assignment.getName() + "&courseId="
                + courseId + "&type=" + materialType;

        ServletOutputStream outputStream = mock(ServletOutputStream.class);

        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getParameter("material")).thenReturn(assignment.getName());
        when(req.getParameter("courseId")).thenReturn(""+courseId);
        when(req.getParameter("type")).thenReturn(materialType);
        when(req.getParameter("student")).thenReturn(studentId+"");

        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(resp.getOutputStream()).thenReturn(outputStream);

        CourseDAO courseDAO = mock(CourseDAO.class);
        when(courseDAO.findSolutionByName(courseId, assignment.getName(), studentId)).thenReturn(assignment);

        DAOFactory daoFactory = mock(DAOFactory.class);
        when(daoFactory.getCourseDAO()).thenReturn(courseDAO);

        Command command = mock(DownloadMaterialCommand.class);
        when(command.execute(req, resp)).thenCallRealMethod();
        when(command.getDaoFactory()).thenReturn(daoFactory);

        String actualPage = command.execute(req, resp);

        assertEquals(expectedPage, actualPage);
    }
}

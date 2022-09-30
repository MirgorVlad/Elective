package com.elective.commad;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteCourseCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        System.out.println("Dlete course  ==> " + courseId);
        courseDAO.deleteById(courseId);

        return "controller?command=viewCourses";
    }
}

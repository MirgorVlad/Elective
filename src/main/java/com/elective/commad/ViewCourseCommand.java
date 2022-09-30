package com.elective.commad;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.entity.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ViewCourseCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        System.out.println("View course  ==> " + courseId);
        Course course = courseDAO.findById(courseId);
        System.out.println(course);
        req.setAttribute("course", course);
        return "course.jsp";
    }
}

package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.mysql.MysqlDAOFactory;
import com.elective.db.entity.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewCoursesCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        List<Course> courseList = courseDAO.getAll();
        System.out.println(courseList);
        req.getSession().setAttribute("coursesList", courseList);
        return ReferencesPages.VIEW_COURSES;
    }
}

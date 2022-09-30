package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ViewAllCoursesCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        List<Course> courseList = courseDAO.getAll();
        String page = null;
        System.out.println(courseList);
        req.getSession().setAttribute("coursesList", courseList);

        if(req.getParameter("command").equals("viewCoursesList")){
            page =  ReferencesPages.VIEW_COURSES_LIST;
        }

        else if(req.getParameter("command").equals("manageCourses") &&
                ((User)req.getSession().getAttribute("user")).getRole().equals(UserDAO.MANAGER_ROLE)) {
            page =  ReferencesPages.MANAGE_COURSES;
        } else
            throw new IllegalAccessException("You are not manager");

        return page;
    }
}

package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ViewStudentAvailableCoursesCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        User student = (User)req.getSession().getAttribute("user");
        List<Course> availableCourses =  courseDAO.availableCourses(student.getId());
        availableCourses.forEach(System.out::println);
        return ReferencesPages.VIEW_COURSES_LIST;
    }
}

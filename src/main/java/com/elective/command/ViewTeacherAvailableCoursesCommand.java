package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ViewTeacherAvailableCoursesCommand implements Command{
    static Logger log = LogManager.getLogger(ViewTeacherAvailableCoursesCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        User teacher = (User)req.getSession().getAttribute("user");
        List<Course> availableCourses =  courseDAO.findCoursesByTeacher(teacher.getId());

        log.log(Level.INFO, "Available courses for teacher " + teacher.getEmail());
        log.log(Level.DEBUG, availableCourses);

        req.setAttribute("coursesList", availableCourses);
        return ReferencePages.AVAILABLE_COURSES_FOR_TEACHER;
    }
}

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
/**
 * Implementation of Command interface that perform displaying available Courses for appropriate User student
 */
public class ViewStudentAvailableCoursesCommand implements Command{
    static Logger log = LogManager.getLogger(ViewStudentAvailableCoursesCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        User student = (User)req.getSession().getAttribute("user");
        List<Course> availableCourses =  courseDAO.availableCourses(student.getId());

        log.log(Level.INFO, "Available courses for student " + student.getEmail());
        log.log(Level.DEBUG, availableCourses);

        req.setAttribute("coursesList", availableCourses);
        return ReferencePages.AVAILABLE_COURSES_FOR_STUDENT;
    }
}

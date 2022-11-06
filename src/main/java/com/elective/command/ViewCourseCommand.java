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

public class ViewCourseCommand implements Command{
    static Logger log = LogManager.getLogger(ViewCourseCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        int userId = ((User)req.getSession().getAttribute("user")).getId();
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        Course course = courseDAO.findById(courseId);

        log.log(Level.INFO, "View course: " + course.getName());
        log.log(Level.DEBUG, course);

        req.setAttribute("course", course);
        req.setAttribute("isJoined", courseDAO.isStudentJoined(userId, courseId));

        return ReferencePages.COURSE;
    }
}

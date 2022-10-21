package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class JoinToCourseCommand implements Command{
    static Logger log = LogManager.getLogger(JoinToCourseCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        int studentId = Integer.parseInt(req.getParameter("userId"));
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        Course course = courseDAO.findById(courseId);
        User user = (User) req.getSession().getAttribute("user");

        log.log(Level.INFO, "Join user " + user.getEmail() + " to course " + course.getName());

        req.setAttribute("course", course);
        req.setAttribute("isJoined", courseDAO.isStudentJoined(user.getId(), courseId));
        courseDAO.jointStudentToCourse(studentId, courseId);
        return "controller?command=viewCourse&courseId=" + courseId;
    }
}

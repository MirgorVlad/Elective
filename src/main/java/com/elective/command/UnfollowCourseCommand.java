package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.JournalDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UnfollowCourseCommand implements Command{
    static Logger log = LogManager.getLogger(UnfollowCourseCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        JournalDAO journalDAO = daoFactory.getJournalDAO();
        int studentId = Integer.parseInt(req.getParameter("userId"));
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        Course course = courseDAO.findById(courseId);
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("isJoined", courseDAO.isStudentJoined(user.getId(), courseId));
        req.setAttribute("course", course);

        log.log(Level.INFO, "Unfollow user " + user.getEmail() + " from course " + course.getName());

        courseDAO.unfollowCourse(studentId, courseId);
        journalDAO.deleteData(user.getId(), course.getId());
        return "controller?command=viewCourse&courseId=" + courseId;
    }
}

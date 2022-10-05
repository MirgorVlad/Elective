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

public class JoinToCourseCommand implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        int studentId = Integer.parseInt(req.getParameter("userId"));
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        Course course = courseDAO.findById(courseId);
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("course", course);
        req.setAttribute("isJoined", courseDAO.isStudentJoined(user.getId(), courseId));
        courseDAO.jointStudentToCourse(studentId, courseId);
        return ReferencesPages.ACCESS_JOINED;
    }
}

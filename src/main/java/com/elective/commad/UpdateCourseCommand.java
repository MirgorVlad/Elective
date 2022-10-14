package com.elective.commad;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.dao.mysql.MysqlCourseDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateCourseCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        int id = Integer.parseInt(req.getParameter("courseId"));
        String name = req.getParameter("name");
        String topic = req.getParameter("topics");
        String desc = req.getParameter("description");
        String tEmail = req.getParameter("teacherEmail");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date finishDate = Date.valueOf(req.getParameter("finishDate"));

        if(startDate.toLocalDate().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Course must start today or in the future");

        User teacher = courseDAO.getTeacher(userDAO.findByEmail(tEmail));
        Course course = MysqlCourseDAO.createCourse(id, name, topic, desc, startDate, finishDate, teacher);

        if(course.countDays() < CourseDAO.MIN_DURATION)
            throw new IllegalArgumentException("Course must last at least " + CourseDAO.MIN_DURATION + " days");

        req.setAttribute("course", course);
        courseDAO.update(course);

        return "controller?command=viewCourse&courseId=" + id;
    }

}

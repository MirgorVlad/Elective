package com.elective.command;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.dao.mysql.MysqlCourseDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateCourseCommand implements Command{
    static Logger log = LogManager.getLogger(UpdateCourseCommand.class);
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

        if(startDate.toLocalDate().isBefore(LocalDate.now())) {
            log.log(Level.WARN, "Course start date in the past  - " + startDate.toLocalDate());
            throw new IllegalArgumentException("Course must start today or in the future");
        }

        User teacher = courseDAO.getTeacher(userDAO.findByEmail(tEmail));
        Course course = MysqlCourseDAO.createCourse(id, name, topic, desc, startDate, finishDate, teacher);

        if(course.countDays() < CourseDAO.MIN_DURATION) {
            log.log(Level.WARN, "Course last  "  + course.countDays() + " days, min duration - " + CourseDAO.MIN_DURATION);
            throw new IllegalArgumentException("Course must last at least " + CourseDAO.MIN_DURATION + " days");
        }

        req.setAttribute("course", course);
        courseDAO.update(course);

        log.log(Level.INFO, "Update course: " + course.getName());
        log.log(Level.DEBUG, "name: " + name + "; topic: " + topic + "; desc: " + desc
                + "; teacher: " + tEmail + "; start: " + startDate + "; finish: " + finishDate);

        return "controller?command=viewCourse&courseId=" + id;
    }

}

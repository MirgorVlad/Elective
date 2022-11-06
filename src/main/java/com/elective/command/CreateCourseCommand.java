package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.dao.mysql.MysqlCourseDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreateCourseCommand implements Command{
    static Logger log = LogManager.getLogger(CreateCourseCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        UserDAO userDAO = getDaoFactory().getUserDAO();
        String name = req.getParameter("name");
        System.out.println(name);
        System.out.println(req.getCharacterEncoding());
        String topic = req.getParameter("topics");
        String desc = req.getParameter("description");
        String tEmail = req.getParameter("teacherEmail");
        String lang = req.getParameter("language");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date finishDate = Date.valueOf(req.getParameter("finishDate"));

        if(startDate.toLocalDate().isBefore(LocalDate.now())) {
            log.log(Level.WARN, "Course start date in the past  - " + startDate.toLocalDate());
            throw new IllegalArgumentException("Course must start today or in the future");
        }

        User teacher = userDAO.findByEmail(tEmail);
        if(!userDAO.isTeacher(teacher.getId()))
            throw new Exception("User is not teacher");

        Course course = MysqlCourseDAO.createCourse(0, name, topic, desc, startDate, finishDate, teacher);

        if(course.countDays() < CourseDAO.MIN_DURATION) {
            log.log(Level.WARN, "Course last  "  + course.countDays() + " days, min duration - " + CourseDAO.MIN_DURATION);
            throw new IllegalArgumentException("Course must last at least " + CourseDAO.MIN_DURATION + " days, but get only " + course.countDays());
        }

        log.log(Level.INFO, "Course created: " + course.getName());
        log.log(Level.DEBUG, "name: " + name + "; topic: " + topic + "; " + "teacher:  " + tEmail + "; start: " + startDate +
                "; finish:  " + finishDate);
        courseDAO.create(course, lang);


        return ReferencePages.MANAGER_PAGE;
    }


}

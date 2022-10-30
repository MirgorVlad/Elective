package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowStudentsInCourseCommand implements Command{
    static Logger log = LogManager.getLogger(ShowStudentsInCourseCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        int courseId = Integer.parseInt(req.getParameter("courseId"));

        List<Integer> studentsIdList = courseDAO.findStudentsInCourse(courseId);
        List<User> studentsList = new ArrayList<>();

        for(int id : studentsIdList){
            studentsList.add(userDAO.findById(id));
        }

        req.setAttribute("studentsList", studentsList);

        log.log(Level.INFO, "Show students in course " + courseId);
        log.log(Level.DEBUG, studentsList);

        return ReferencePages.STUDENTS_IN_COURSE;
    }
}

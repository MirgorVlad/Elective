package com.elective.command;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DeleteCourseCommand implements Command{
    static Logger log = LogManager.getLogger(DeleteCourseCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        int courseId = Integer.parseInt(req.getParameter("courseId"));

        log.log(Level.INFO, "DELETE course id: "+ courseId);

        courseDAO.deleteById(courseId);

        return "controller?command=manageCourses";
    }
}

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
import java.util.ArrayList;
import java.util.List;

public class ViewTeacherAvailableCoursesCommand implements Command{
    static Logger log = LogManager.getLogger(ViewTeacherAvailableCoursesCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        User teacher = (User)req.getSession().getAttribute("user");
        List<Course> availableCourses =  courseDAO.findCoursesByTeacher(teacher.getId());

        int total = 10;
        String pageNum = req.getParameter("page");
        String name = req.getParameter("name");
        if(name != null) {
            Course course = courseDAO.findCoursesByName(name);
            availableCourses = new ArrayList<>();
            if(course != null && course.getTeacher().equals(teacher))
                availableCourses.add(course);
            log.log(Level.INFO, "Find course"+course+" for teacher: " + teacher.getEmail());
        }
        if(pageNum != null) {
            int pageid = Integer.parseInt(pageNum);
            if (pageid != 1) {
                pageid = pageid - 1;
                pageid = pageid * total + 1;
            }
            availableCourses = courseDAO.getCoursesForTeacher(teacher.getId(),pageid, total);
        }
        double courseCount = courseDAO.countCoursesForTeacher(teacher.getId());

        req.setAttribute("pageCount", Math.ceil(courseCount/total));
        req.setAttribute("coursesList", availableCourses);

        log.log(Level.INFO, "Available courses for teacher " + teacher.getEmail());
        log.log(Level.DEBUG, availableCourses);

        req.setAttribute("coursesList", availableCourses);
        return ReferencePages.AVAILABLE_COURSES_FOR_TEACHER;
    }
}

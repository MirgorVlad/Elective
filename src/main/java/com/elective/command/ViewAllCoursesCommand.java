package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
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
/**
 * Implementation of Command interface that perform displaying all available Courses
 */
public class ViewAllCoursesCommand implements Command{
    static Logger log = LogManager.getLogger(ViewAllCoursesCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        UserDAO userDAO = getDaoFactory().getUserDAO();
        User user = (User)req.getSession().getAttribute("user");
        List<Course> courseList = courseDAO.getAll();
        List<User> teacherList = userDAO.getAllTeachers();

        req.getSession().setAttribute("teacherList", teacherList);

        String page;

        req.getSession().setAttribute("coursesList", courseList);
        log.log(Level.DEBUG,  courseList);

        if(req.getParameter("command").equals("viewCoursesList")){
            page =  ReferencePages.VIEW_COURSES_LIST;
            log.log(Level.INFO, "View all courses");
        }

        else if(req.getParameter("command").equals("manageCourses") &&
                user.getRole().equals(UserDAO.MANAGER_ROLE)) {
            int total = 10;
            String pageNum = req.getParameter("page");
            String name = req.getParameter("name");
            if(name != null) {
                Course course = courseDAO.findCoursesByName(name);
                courseList = new ArrayList<>();
                if(course != null)
                    courseList.add(course);
                log.log(Level.INFO, "Find course: " +course);
            }
            if(pageNum != null) {
                int pageid = Integer.parseInt(pageNum);
                if (pageid != 1) {
                    pageid = pageid - 1;
                    pageid = pageid * total + 1;
                }
                courseList = courseDAO.getCourses(pageid, total);
            }
            double courseCount = courseDAO.countCourses();

            req.setAttribute("pageCount", Math.ceil(courseCount/total));
            req.setAttribute("coursesList", courseList);

            log.log(Level.INFO, "Manage courses: " + courseList);
            page =  ReferencePages.MANAGE_COURSES;
        } else{
            log.log(Level.WARN, "User " + user.getEmail() + " cannot manage courses");
            throw new IllegalAccessException("You are not manager");
        }

        return page;
    }
}

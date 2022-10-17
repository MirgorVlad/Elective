package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.entity.Course;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class SelectCoursesCommand implements Command{
    static Logger log = LogManager.getLogger(SelectCoursesCommand.class);
    private final CourseDAO courseDAO = daoFactory.getCourseDAO();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {

        String topic = req.getParameter("topics");
        String teacher = req.getParameter("teachers");
        List<Course> courseList;

        courseList = generateList(topic, teacher);

        log.log(Level.INFO, "Selected courses by topic - " + topic + "; teacher - " + teacher);
        log.log(Level.DEBUG, courseList);

        //req.getSession().removeAttribute("coursesList");
        req.getSession().setAttribute("coursesList", courseList);
        return ReferencePages.VIEW_COURSES_LIST;
    }

    private List<Course> generateList(String topic, String teacher) throws DBException, SQLException {
        List<Course> courseListByTopic;
        List<Course> courseListByTeacher;
        if(topic.equals("all"))
            courseListByTopic = courseDAO.getAll();
        else
            courseListByTopic = courseDAO.getCoursesByTopic(topic);
        if(teacher.equals("all"))
            courseListByTeacher = courseListByTopic;
        else
            courseListByTeacher = courseDAO.findCoursesByTeacher(Integer.parseInt(teacher));


        return courseListByTopic.stream()
                .distinct()
                .filter(courseListByTeacher::contains).toList();
    }
}

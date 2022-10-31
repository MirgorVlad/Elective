package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.entity.Course;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class SelectCoursesCommand implements Command{
    static Logger log = LogManager.getLogger(SelectCoursesCommand.class);
    private final CourseDAO courseDAO = daoFactory.getCourseDAO();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {

        String topic = req.getParameter("topics");
        String teacher = req.getParameter("teachers");
        String lang = req.getParameter("languages");
        List<Course> courseList;

        courseList = generateList(topic, teacher, lang);

        log.log(Level.INFO, "Selected courses by topic - " + topic + "; teacher - " + teacher);
        log.log(Level.DEBUG, courseList);

        //req.getSession().removeAttribute("coursesList");
        req.getSession().setAttribute("coursesList", courseList);
        return ReferencePages.VIEW_COURSES_LIST;
    }

    private List<Course> generateList(String topic, String teacher, String lang) throws DBException, SQLException {
        List<Course> courseListByTopic;
        List<Course> courseListByTeacher;
        List<Course> courseListByLang;
        if(topic.equals("all"))
            courseListByTopic = courseDAO.getAll();
        else
            courseListByTopic = courseDAO.getCoursesByTopic(topic);
        if(teacher.equals("all"))
            courseListByTeacher = courseListByTopic;
        else
            courseListByTeacher = courseDAO.findCoursesByTeacher(Integer.parseInt(teacher));
        if(lang.equals("all"))
            courseListByLang = courseListByTopic;
        else
            courseListByLang = courseDAO.findCoursesByLang(lang);

        return courseListByTopic.stream()
                .distinct()
                .filter(courseListByTeacher::contains)
                .filter(courseListByLang::contains).toList();
    }
}

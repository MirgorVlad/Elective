package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.entity.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SelectCoursesCommand implements Command{
    private final CourseDAO courseDAO = daoFactory.getCourseDAO();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {

        String topic = req.getParameter("topics");
        String teacher = req.getParameter("teachers");
        List<Course> courseList;

        courseList = generateList(topic, teacher);

        req.setAttribute("coursesList", courseList);
        return "controller?command=viewCoursesList";
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

        System.out.println("Topic: " + courseListByTopic);
        System.out.println("Teacher: " + courseListByTeacher);

        return courseListByTopic.stream()
                .distinct()
                .filter(courseListByTeacher::contains).toList();
    }
}

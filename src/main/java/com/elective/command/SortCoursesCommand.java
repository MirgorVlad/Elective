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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortCoursesCommand implements Command{
    static Logger log = LogManager.getLogger(SortCoursesCommand.class);

    private final String AZ = "az";
    private final String ZA = "za";
    private final String DURATION = "duration";
    private final String STUDENTS = "students";
    private final String ASC_METHOD = "asc";
    private final String DESC_METHOD = "desc";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {

        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        String sample = req.getParameter("sample");
        String method = req.getParameter("method");
        List<Course> courseList;


        if(req.getSession().getAttribute("coursesList") != null) {
            courseList = new ArrayList<>((List<Course>) req.getSession().getAttribute("coursesList"));

        }
        else {
            throw new IllegalArgumentException("Courses list is empty");
        }

        if(sample.equals(AZ)){
            courseList.sort(Comparator.comparing(Course::getName));
        }
        if(sample.equals(ZA)){
            courseList.sort(Comparator.comparing(Course::getName).reversed());
        }
        if(sample.equals(DURATION)){
            if(method.equals(ASC_METHOD))
                courseList.sort(Comparator.comparing(Course::duration));
            if(method.equals(DESC_METHOD))
                courseList.sort(Comparator.comparing(Course::duration).reversed());

        }
        if(sample.equals(STUDENTS)){
            if(method.equals(ASC_METHOD)) {
                courseList.sort(Comparator.comparingInt((Course o) -> {
                    try {
                        return courseDAO.countStudentsInCourse(o.getId());
                    } catch (SQLException | DBException e) {
                        throw new RuntimeException("Cannot sort", e);
                    }
                }));
            }
            if(method.equals(DESC_METHOD)) {
                courseList.sort(Comparator.comparingInt((Course o) -> {
                    try {
                        return courseDAO.countStudentsInCourse(o.getId());
                    } catch (SQLException | DBException e) {
                        throw new RuntimeException("Cannot sort", e);
                    }
                }).reversed());
            }
        }

        req.getSession().setAttribute("coursesList", courseList);

        log.log(Level.INFO, "Sort courses by " + method + "(" + sample + ")");

        return ReferencePages.VIEW_COURSES_LIST;
    }
}

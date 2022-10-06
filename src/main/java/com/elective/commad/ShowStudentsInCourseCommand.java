package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowStudentsInCourseCommand implements Command{
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
        studentsList.forEach(System.out::println);
        return ReferencesPages.STUDENTS_IN_COURSE;
    }
}

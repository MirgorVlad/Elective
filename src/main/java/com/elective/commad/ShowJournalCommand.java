package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowJournalCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        String page = "";
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        Course course = courseDAO.findById(Integer.parseInt(req.getParameter("courseId")));
        req.setAttribute("course", course);
        User user = (User)req.getSession().getAttribute("user");

        if (user.getRole().equals(UserDAO.TEACHER_ROLE)){
            List<Integer> studentsIdList = courseDAO.findStudentsInCourse(course.getId());
            List<User> studentsList = new ArrayList<>();

            for(int id : studentsIdList){
                studentsList.add(userDAO.findById(id));
            }

            req.setAttribute("studentsList", studentsList);

            page = ReferencesPages.TEACHER_JOURNAL;
        }

        if(user.getRole().equals(UserDAO.STUDENT_ROLE)) {
            page = ReferencesPages.JOURNAL_PAGE;
        }
        return page;
    }
}

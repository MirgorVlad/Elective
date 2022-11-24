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
 * Implementation of Command interface that perform displaying  Course journal
 */
public class ShowJournalCommand implements Command{
    static Logger log = LogManager.getLogger(ShowJournalCommand.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        String page = "";
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        UserDAO userDAO = getDaoFactory().getUserDAO();
        Course course = courseDAO.findById(Integer.parseInt(req.getParameter("courseId")));
        req.setAttribute("course", course);
        User user = (User)req.getSession().getAttribute("user");

        log.log(Level.INFO, "Show journal for course - "+ course.getName());

        if (user.getRole().equals(UserDAO.TEACHER_ROLE)){
            List<Integer> studentsIdList = courseDAO.findStudentsInCourse(course.getId());
            List<User> studentsList = new ArrayList<>();

            for(int id : studentsIdList){
                studentsList.add(userDAO.findById(id));
            }

            req.getSession().setAttribute("studentsList", studentsList);

            page = ReferencePages.TEACHER_JOURNAL;
        }

        if(user.getRole().equals(UserDAO.STUDENT_ROLE)) {
            page = ReferencePages.JOURNAL_PAGE;
        }
        return page;
    }
}

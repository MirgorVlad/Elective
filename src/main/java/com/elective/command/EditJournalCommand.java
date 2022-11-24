package com.elective.command;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.JournalDAO;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.Journal;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
/**
 * Implementation of Command interface that perform editing Journal for User teacher
 */
public class EditJournalCommand implements Command{
    static Logger log = LogManager.getLogger(EditJournalCommand.class);

    private final CourseDAO courseDAO = getDaoFactory().getCourseDAO();
    private final UserDAO userDAO = getDaoFactory().getUserDAO();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException, IllegalArgumentException {
        JournalDAO journalDAO = getDaoFactory().getJournalDAO();
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String student = req.getParameter("students");
        Date date = Date.valueOf(req.getParameter("date"));
        int grade = Integer.parseInt(req.getParameter("grade"));
        Course course = courseDAO.findById(courseId);

        log.log(Level.INFO, "Edit journal for course " + course.getName());

        if(date.toLocalDate().isBefore(course.getStartDate().toLocalDate())
                || date.toLocalDate().isAfter(course.getFinishDate().toLocalDate())) {
            log.log(Level.WARN, "No date " + date.toLocalDate() + " in course");
            throw new IllegalArgumentException("There is no such date in the course");
        }

        log.log(Level.DEBUG, "Change grade " + grade + "to student: " + student + " to " + date);

        journalDAO.setGrade(createJournal(courseId, student, date, grade));
        req.getSession().removeAttribute("studentsList");
        return "controller?command=showJournal&courseId="+courseId+"&page=1";
    }

    private Journal createJournal(int courseId, String studentEmail, Date date, int grade) throws DBException, SQLException {
        Journal journal = new Journal();
        journal.setGrade(grade);
        journal.setDate(date);
        journal.setCourse(courseDAO.findById(courseId));
        journal.setStudent(userDAO.findByEmail(studentEmail));
        return journal;
    }
}

package com.elective.commad;

import com.elective.ReferencesPages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.JournalDAO;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.Journal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class EditJournalCommand implements Command{
    private final CourseDAO courseDAO = daoFactory.getCourseDAO();
    private final UserDAO userDAO = daoFactory.getUserDAO();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException, IllegalAccessException {
        JournalDAO journalDAO = daoFactory.getJournalDAO();
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String student = req.getParameter("students");
        Date date = Date.valueOf(req.getParameter("date"));
        int grade = Integer.parseInt(req.getParameter("grade"));
        Course course = courseDAO.findById(courseId);
        System.out.println(courseId + student + date + grade);

        if(date.toLocalDate().isBefore(course.getStartDate().toLocalDate())
                || date.toLocalDate().isAfter(course.getFinishDate().toLocalDate()))
            throw new IllegalArgumentException("There is no such date in the course");

        journalDAO.setGrade(createJournal(courseId, student, date, grade));
        req.getSession().removeAttribute("studentsList");
        return "controller?command=showJournal&courseId="+courseId;
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

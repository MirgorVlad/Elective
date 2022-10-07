package com.elective.tags;
import com.elective.ReferencesPages;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.JournalDAO;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ShowJournal extends TagSupport {
    private List<User> studentsList;
    private static final int COUNT_OF_TEST = 10;
    private Course course;
    private final JournalDAO journalDAO = DAOFactory.getInstance().getJournalDAO();

    public void setCourse(Course course){
        this.course = course;
    }
    public void setStudentsList(List<User> studentsList){
        this.studentsList = studentsList;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(
                        "<table border=\"1\">" +
                                printTable() +
                            "</table>"
            );
        } catch (IOException | DBException | SQLException | ServletException e) {
            throw new RuntimeException("Problem is here", e);
        }
        return SKIP_BODY;
    }

    private String printTable() throws DBException, SQLException, ServletException, IOException {
        long courseContinueDays = course.countDays();
        long step = courseContinueDays / COUNT_OF_TEST;
        User user = (User) pageContext.getSession().getAttribute("user");

        try {
            if(user.getRole().equals(UserDAO.STUDENT_ROLE)){
                return printStudentJournal(step, courseContinueDays);
            }
            if(user.getRole().equals(UserDAO.TEACHER_ROLE)){
                return printTeacherJournal(step, courseContinueDays);
            }
        } catch (DBException | SQLException ex){
            pageContext.forward(ReferencesPages.ERROR_PAGE);
        }

        return "No data";
    }

    private String printStudentJournal(long step, long days) throws DBException, SQLException {
        User student = (User)pageContext.getSession().getAttribute("user");
        LocalDate startDate = course.getStartDate().toLocalDate().plusDays(step);
        LocalDate finishDate = course.getFinishDate().toLocalDate();
        String out = createDates(startDate, step, days, "DATE");
        String outGrades = "<tr><th>GRADE</th>";
        for(LocalDate s = startDate; s.isBefore(finishDate); s = s.plusDays(step)){
            outGrades += "<th>"+journalDAO.getGrade(course.getId(), student.getId(), Date.valueOf(s))+"</th>";  //instead i -> date.getGrade
            //startDate = startDate.plusDays(step);
        }
        outGrades += "<th>-</th>"; //finalTest;
        outGrades += "<th>"+journalDAO.sumOfStudentGrades(course.getId(), student.getId())+"</th></tr>"; //total;
        return out + outGrades;
    }

    private String printTeacherJournal(long step, long days) throws DBException, SQLException {
        LocalDate startDate = course.getStartDate().toLocalDate().plusDays(step);
        LocalDate finishDate = course.getFinishDate().toLocalDate();
        String out = createDates(startDate, step, days, "Student\\Date");
        String studentRow = "";
        for(User user : studentsList) {
            studentRow += "<tr><th><a href=\"controller?command=viewProfile&userId="+user.getId()+"\">"+user.getFullName()+"</a></th>";
            for(LocalDate s = startDate; s.isBefore(finishDate); s = s.plusDays(step)){
                studentRow += "<th>"+journalDAO.getGrade(course.getId(), user.getId(), Date.valueOf(s))+"</th>";  //instead i -> date.getGrade
                //startDate = startDate.plusDays(step);
            }
            studentRow += "<th>-</th>"; //finalTest;
            studentRow += "<th>"+journalDAO.sumOfStudentGrades(course.getId(), user.getId())+"</th></tr>"; //total;
            startDate = course.getStartDate().toLocalDate().plusDays(step);
        }
        return out + studentRow;
    }

    private String createDates(LocalDate start, long step, long days, String fColumnText){
        String out = "<tr><th>"+fColumnText+"</th>";
        for(int i = 0; i < days - step; i+=step){
            out += "<th>" + start + "</th>";
            start = start.plusDays(step);
        }
        out += "<th>Final Test</th>" +
                "<th>TOTAL</th></tr>";
        return out;
    }
}

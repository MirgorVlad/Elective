package com.elective.tags;
import com.elective.ReferencePages;
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
    private static final int step = 1;
    private static final int limit = 7;
    private Course course;
    private final User student = (User)pageContext.getSession().getAttribute("user");
    private  LocalDate startDate = course.getStartDate().toLocalDate();
    private final LocalDate finishDate = course.getFinishDate().toLocalDate();
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
                            "</table>" +
                                "<a href=\"controller?command=showJournal&courseId="+course.getId()+"\"?page=1>1 </a>" +
                                "<a href=\"controller?command=showJournal&courseId="+course.getId()+"\"?page=2>2 </a>"
            );
        } catch (IOException | DBException | SQLException | ServletException e) {
            throw new RuntimeException("Problem is here", e);
        }
        return SKIP_BODY;
    }

    private String printTable() throws DBException, SQLException, ServletException, IOException {
        long courseContinueDays = course.countDays();
        User user = (User) pageContext.getSession().getAttribute("user");

        try {
            if(user.getRole().equals(UserDAO.STUDENT_ROLE)){
                return printStudentJournal(courseContinueDays);
            }
            if(user.getRole().equals(UserDAO.TEACHER_ROLE)){
                return printTeacherJournal(courseContinueDays);
            }
        } catch (DBException | SQLException ex){
            pageContext.forward(ReferencePages.ERROR_PAGE);
        }

        return "No data";
    }

    private String printStudentJournal(long days) throws DBException, SQLException {
        int page = Integer.parseInt(pageContext.getRequest().getParameter("page"));
        if(page > 1){
            page-=1;
            page = page*limit+1;
        }
        startDate = startDate.plusDays(page);
        String out = createDates(startDate, limit, "DATE");
        String outGrades = "<tr><th>GRADE</th>";
        for(LocalDate s = startDate; !s.isEqual(startDate.plusDays(limit)); s = s.plusDays(step)){
            if(s.isAfter(finishDate)){
                break;
            }
            outGrades += "<th>"+journalDAO.getGrade(course.getId(), student.getId(), Date.valueOf(s))+"</th>";  //instead i -> date.getGrade
            //startDate = startDate.plusDays(step);
        }
        outGrades += "<th>-</th>"; //finalTest;
        outGrades += "<th>"+journalDAO.sumOfStudentGrades(course.getId(), student.getId())+"</th></tr>"; //total;
        return out + outGrades;
    }

    private String printTeacherJournal(long days) throws DBException, SQLException {
        LocalDate startDate = course.getStartDate().toLocalDate();
        LocalDate finishDate = course.getFinishDate().toLocalDate();
        String out = createDates(startDate,  days, "Student\\Date");
        String studentRow = "";
        for(User user : studentsList) {
            studentRow += "<tr><th><a href=\"controller?command=viewProfile&userId="+user.getId()+"\">"+user.getFullName()+"</a></th>";
            for(LocalDate s = startDate; !s.isEqual(finishDate.minusDays(1)); s = s.plusDays(step)){
                studentRow += "<th>"+journalDAO.getGrade(course.getId(), user.getId(), Date.valueOf(s))+"</th>";  //instead i -> date.getGrade
                //startDate = startDate.plusDays(step);
            }
            studentRow += "<th>-</th>"; //finalTest;
            studentRow += "<th>"+journalDAO.sumOfStudentGrades(course.getId(), user.getId())+"</th></tr>"; //total;
            startDate = course.getStartDate().toLocalDate().plusDays(step);
        }
        return out + studentRow;
    }

    private String createDates(LocalDate start, long days, String fColumnText){
        String out = "<tr><th>"+fColumnText+"</th>";
        LocalDate s = start;
        for(int i = 0; i < limit; i+=step){
            if(s.isAfter(finishDate)){
                break;
            }
            out += "<th>" + s + "</th>";
            s = s.plusDays(step);
        }
        out += "<th>Final Test</th>" +
                "<th>TOTAL</th></tr>";
        return out;
    }
}

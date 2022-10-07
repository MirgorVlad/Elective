package com.elective.tags;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ShowJournal extends TagSupport {
    private List<User> studentsList;
    private static final int COUNT_OF_TEST = 10;
    private Course course;

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
        } catch (IOException e) {
            throw new RuntimeException("Problem is here", e);
        }
        return SKIP_BODY;
    }

    private String printTable(){
        long courseContinueDays = course.countDays();
        long step = courseContinueDays / COUNT_OF_TEST;
        User user = (User) pageContext.getSession().getAttribute("user");

        if(user.getRole().equals(UserDAO.STUDENT_ROLE)){
            return printStudentJournal(step, courseContinueDays);
        }
        if(user.getRole().equals(UserDAO.TEACHER_ROLE)){
            return printTeacherJournal(step, courseContinueDays);
        }
        return "No data";
    }

    private String printStudentJournal(long step, long days) {
        LocalDate startDate = course.getStartDate().toLocalDate().plusDays(step);
        String out = createDates(startDate, step, days, "DATE");
        String outGrades = "<tr><th>GRADE</th>";
        for(int i = 0; i < days - step; i+=step){
            System.out.println(startDate);
            outGrades += "<th>"+i+"</th>";  //instead i -> date.getGrade
            startDate = startDate.plusDays(step);
        }
        outGrades += "</tr>";
        return out + outGrades;
    }

    private String printTeacherJournal(long step, long days){
        LocalDate startDate = course.getStartDate().toLocalDate().plusDays(step);
        String out = createDates(startDate, step, days, "Student\\Date");
        String studentRow = "";
        for(User user : studentsList) {
            studentRow += "<tr><th>"+user.getFullName()+"</th>";
            for (int i = 0; i < days - step; i += step) {
                studentRow += "<th>" + i + "</th>";  //instead i -> date.getGrade
                startDate = startDate.plusDays(step);
            }

            studentRow += "</tr>";
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

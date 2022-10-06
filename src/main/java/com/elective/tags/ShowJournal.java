package com.elective.tags;
import com.elective.db.entity.Course;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;

public class ShowJournal extends TagSupport {
    private static final int COUNT_OF_TEST = 10;
    private Course course;

    public void setCourse(Course course){
        this.course = course;
    }
    @Override
    public int doStartTag() throws JspException {
        long courseContinueDays = course.countDays();
        long step = courseContinueDays / COUNT_OF_TEST;
        try {
            pageContext.getOut().write(
                    "<table border=\"1\">" +
                            "<tr>" +
                                printTestDays(step, courseContinueDays) +
                            "</tr>"
            );
        } catch (IOException e) {
            throw new RuntimeException("Problem is here", e);
        }
        return SKIP_BODY;
    }

    private String printTestDays(long step, long days) {
        String out = "<th>DATE</th>";
        String outGrades = "<tr><th>GRADE</th>";
        LocalDate startDate = course.getStartDate().toLocalDate().plusDays(step);
        //LocalDate finishDate = course.getFinishDate().toLocalDate();
        //System.out.println(finishDate.compareTo(startDate));
        for(int i = 0; i < days - step; i+=step){
            System.out.println(startDate);
            out += "<th>" + startDate + "</th>";
            outGrades += "<th>"+i+"</th>";  //instead i -> date.getGrade
            startDate = startDate.plusDays(step);
        }
        out += "<th>Final Test</th>" +
                "<th>TOTAL</th>";
        outGrades += "</tr>";
        return out + outGrades;
    }
}

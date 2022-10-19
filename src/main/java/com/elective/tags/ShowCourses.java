package com.elective.tags;

import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ShowCourses extends TagSupport {

    private List<Course> coursesList;
    private User user = null;
    private final String out = "  <table border=\"1\">\n" +
            "        <caption>All Courses</caption>\n" +
            "        <tr>\n" +
            "            <th>Name</th>\n" +
            "            <th>Start date</th>\n" +
            "            <th>End date</th>\n" +
            "            <th>Teacher</th>\n" +
            "            <th>Email</th>\n";

    public void setCoursesList(List<Course> courseList){
        this.coursesList = courseList;
    }

    @Override
    public int doStartTag() throws JspException {
        user = (User)pageContext.getSession().getAttribute("user");
        try {
            pageContext.getOut().write(
                   getOutTable()
            );
        } catch (IOException e) {
            throw new RuntimeException("Problem is here", e);
        }
        return SKIP_BODY;
    }

    private String getOutTable() {
        String outTable = "Empty set";
        if(user.getRole().equals(UserDAO.MANAGER_ROLE)){
           outTable = out + managerTable();
        }
        if(user.getRole().equals(UserDAO.STUDENT_ROLE)){
            outTable =  studentTable();
        }
        if(user.getRole().equals(UserDAO.TEACHER_ROLE)){
            outTable = out + teacherTable();
        }
        return  outTable;
    }

    private String managerTable(){
        String table = "<th>EDIT</th>\n" +
                        "<th>DELETE</th>\n" +
                        "</tr>";
        for(Course course : coursesList){
            table += " <tr>\n" +
                    "      <th><a href=\"controller?command=viewCourse&courseId="+course.getId()+"\">"+course.getName()+"</a></th>\n" +
                    "      <th>"+course.getStartDate()+"</th>\n" +
                    "      <th>"+course.getFinishDate()+"</th>\n" +
                    "      <th>"+course.getTeacher().getFullName()+"</th>\n" +
                    "      <th><a href=\"controller?command=viewProfile&userId="+course.getTeacher().getId()+"\">"+course.getTeacher().getEmail()+"</a></th>\n" +
                    "      <th><a href=\"edit_course.jsp?courseId="+course.getId() +
                                                        "&name="+course.getName() +
                                                        "&topic="+course.getTopic() +
                                                        "&description=" + course.getDescription() +
                                                        "&teacher="+course.getTeacher().getEmail() +
                                                        "&start="+course.getStartDate() +
                                                        "&finish="+course.getFinishDate()+"\">EDIT</a></th>\n" +
                    "      <th><a href=\"controller?command=deleteCourse&courseId="+course.getId()+"\">DELETE</a></th>\n" +
                    " </tr>";
        }
        return table;
    }

    private String studentTable(){
        return studentTableThatNotBegun() + "<br/>"+ studentTableThatInProgress() + "<br/>"+ studentTableThatFinished();
    }

    String studentTableThatNotBegun(){
        String table = "  <table border=\"1\" style=\"width: 70%;\">\n" +
                "        <caption><b>Not begun yet</b></caption>\n" +
                "        <tr>\n" +
                "            <th style=\"width: 11%;\">Name</th>\n" +
                "            <th style=\"width: 11%;\">Start date</th>\n" +
                "            <th style=\"width: 11%;\">End date</th>\n" +
                "            <th style=\"width: 22%;\">Teacher</th>\n" +
                "            <th style=\"width: 22%;\">Email</th>\n" +
                "            <th style=\"width: 23%;\">UNFOLLOW</th>\n" +
                "</tr>";
        for(Course course : coursesList) {
            if (course.getStartDate().toLocalDate().isAfter(LocalDate.now())) {
                table += " <tr>\n" +
                        "      <th><a href=\"controller?command=viewCourse&courseId=" + course.getId() + "\">" + course.getName() + "</a></th>\n" +
                        "      <th>" + course.getStartDate() + "</th>\n" +
                        "      <th>" + course.getFinishDate() + "</th>\n" +
                        "      <th>" + course.getTeacher().getFullName() + "</th>\n" +
                        "      <th><a href=\"controller?command=viewProfile&userId=" + course.getTeacher().getId() + "\">" + course.getTeacher().getEmail() + "</a></th>\n" +
                        "      <th><a href=\"controller?command=unfollowCourse&userId=" + user.getId() + "&courseId=" + course.getId() + "\">UNFOLLOW</a></th>\n" +
                        " </tr>";
            }
        }
        table += "</table>";
        return table;
    }

    String studentTableThatInProgress(){
        String table = "  <table border=\"1\" style=\"width: 70%;\">\n" +
                "        <caption><b>In Progress</b></caption>\n" +
                "        <tr>\n" +
                "            <th style=\"width: 10%;\">Name</th>\n" +
                "            <th style=\"width: 10%;\">Start date</th>\n" +
                "            <th style=\"width: 10%;\">End date</th>\n" +
                "            <th style=\"width: 20%;\">Teacher</th>\n" +
                "            <th style=\"width: 20%;\">Email</th>\n" +
                "            <th style=\"width: 10%;\">UNFOLLOW</th>\n" +
                "            <th style=\"width: 10%;\">JOURNAL</th>\n" +
                "</tr>";
        for(Course course : coursesList) {
            if ((course.getStartDate().toLocalDate().isBefore(LocalDate.now()) || course.getStartDate().toLocalDate().isEqual(LocalDate.now()))
                    && course.getFinishDate().toLocalDate().isAfter(LocalDate.now())) {
                table += " <tr>\n" +
                        "      <th><a href=\"controller?command=viewCourse&courseId=" + course.getId() + "\">" + course.getName() + "</a></th>\n" +
                        "      <th>" + course.getStartDate() + "</th>\n" +
                        "      <th>" + course.getFinishDate() + "</th>\n" +
                        "      <th>" + course.getTeacher().getFullName() + "</th>\n" +
                        "      <th><a href=\"controller?command=viewProfile&userId=" + course.getTeacher().getId() + "\">" + course.getTeacher().getEmail() + "</a></th>\n" +
                        "      <th><a href=\"controller?command=unfollowCourse&userId=" + user.getId() + "&courseId=" + course.getId() + "\">UNFOLLOW</a></th>\n" +
                        "      <th><a href=\"controller?command=showJournal&courseId=" + course.getId() + "&page=1\">JOURNAL</a></th>\n" +
                        " </tr>";
            }
        }
        table += "</table>";
        return table;
    }

    String studentTableThatFinished(){
        String table = "  <table border=\"1\" style=\"width: 70%;\">\n" +
                "        <caption><b>Finished</b></caption>\n" +
                "        <tr>\n" +
                "            <th style=\"width: 10%;\">Name</th>\n" +
                "            <th style=\"width: 10%;\">Start date</th>\n" +
                "            <th style=\"width: 10%;\">End date</th>\n" +
                "            <th style=\"width: 20%;\">Teacher</th>\n" +
                "            <th style=\"width: 20%;\">Email</th>\n" +
                "            <th style=\"width: 10%;\">UNFOLLOW</th>\n" +
                "            <th style=\"width: 10%;\">RESULTS</th>\n" +
                "</tr>";
        for(Course course : coursesList) {
            if (course.getFinishDate().toLocalDate().isBefore(LocalDate.now())) {
                table += " <tr>\n" +
                        "      <th><a href=\"controller?command=viewCourse&courseId=" + course.getId() + "\">" + course.getName() + "</a></th>\n" +
                        "      <th>" + course.getStartDate() + "</th>\n" +
                        "      <th>" + course.getFinishDate() + "</th>\n" +
                        "      <th>" + course.getTeacher().getFullName() + "</th>\n" +
                        "      <th><a href=\"controller?command=viewProfile&userId=" + course.getTeacher().getId() + "\">" + course.getTeacher().getEmail() + "</a></th>\n" +
                        "      <th><a href=\"controller?command=unfollowCourse&userId=" + user.getId() + "&courseId=" + course.getId() + "\">UNFOLLOW</a></th>\n" +
                        "      <th><a href=\"controller?command=showJournal&courseId="+ course.getId() + "&page=1\">RESULTS</a></th>\n" +
                        " </tr>";
            }
        }
        table += "</table>";
        return table;
    }

    private String teacherTable(){
        String table = "<th>STUDENTS</th>\n" +
                "<th>JOURNAL</th>\n" +
                "</tr>";
        for(Course course : coursesList){
            table += " <tr>\n" +
                    "      <th><a href=\"controller?command=viewCourse&courseId="+course.getId()+"\">"+course.getName()+"</a></th>\n" +
                    "      <th>"+course.getStartDate()+"</th>\n" +
                    "      <th>"+course.getFinishDate()+"</th>\n" +
                    "      <th>"+course.getTeacher().getFullName()+"</th>\n" +
                    "      <th><a href=\"controller?command=viewProfile&userId="+course.getTeacher().getId()+"\">"+course.getTeacher().getEmail()+"</a></th>\n" +
                    "      <th><a href=\"controller?command=showStudentsInCourse&courseId="+course.getId()+"\">STUDENTS</a></th>\n" +
                    "      <th><a href=\"controller?command=showJournal&courseId="+ course.getId() + "&page=1\">JOURNAL</a></th>\n" +
                    " </tr>";
        }
        return table;
    }

}

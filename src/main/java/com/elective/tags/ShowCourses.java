package com.elective.tags;

import com.elective.ReferencePages;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ShowCourses extends TagSupport {
    static Logger log = LogManager.getLogger(ShowCourses.class);

    private ResourceBundle bundle;
    private List<Course> coursesList;
    private User user = null;
    private final String out = "  <table border=\"1\">\n" +
            "        <tr class=\"header\">\n" +
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
        HttpSession session = pageContext.getSession();
        user = (User)session.getAttribute("user");
        String lang = "en";
        if( session.getAttribute("lang") != null)
            lang = (String) session.getAttribute("lang");

        Locale locale = Locale.of(lang);

        bundle = ResourceBundle.getBundle("messages", locale);
            //locale = setLocale((String) session.getAttribute("lang"), pageContext.getRequest(), pageContext.getResponse());

        try {
            pageContext.getOut().write(
                   getOutTable()
            );
        } catch (IOException | ServletException e) {
            throw new RuntimeException("Problem is here", e);
        }
        return SKIP_BODY;
    }

    private String getOutTable() throws IOException, ServletException {
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
                                                        "&finish="+course.getFinishDate()+"\">"+bundle.getString("course.edit")+"</a></th>\n" +
                    "      <th><a href=\"controller?command=deleteCourse&courseId="+course.getId()+"\">"+bundle.getString("course.delete")+"</a></th>\n" +
                    " </tr>";
        }
        return table;
    }

    private String studentTable() throws IOException, ServletException {
        return studentTableThatNotBegun() + "<br/>"+ studentTableThatInProgress() + "<br/>"+ studentTableThatFinished();
    }

    String studentTableThatNotBegun(){
        String table = "  <h3>"+bundle.getString("course.notstart")+"</h3><table border=\"1\">\n" +
                "        <tr class=\"header\">\n" +
                "            <th style=\"width: 11%;\">"+bundle.getString("course.name")+"</th>\n" +
                "            <th style=\"width: 11%;\">"+bundle.getString("course.start")+"</th>\n" +
                "            <th style=\"width: 11%;\">"+bundle.getString("course.end")+"</th>\n" +
                "            <th style=\"width: 22%;\">"+bundle.getString("course.teacher")+"</th>\n" +
                "            <th style=\"width: 22%;\">"+bundle.getString("course.email")+"</th>\n" +
                "            <th style=\"width: 23%;\">"+bundle.getString("course.unfollow")+"</th>\n" +
                "</tr>";
        for(Course course : coursesList) {
            if (course.getStartDate().toLocalDate().isAfter(LocalDate.now())) {
                table += " <tr>\n" +
                        "      <th><a href=\"controller?command=viewCourse&courseId=" + course.getId() + "\">" + course.getName() + "</a></th>\n" +
                        "      <th>" + course.getStartDate() + "</th>\n" +
                        "      <th>" + course.getFinishDate() + "</th>\n" +
                        "      <th>" + course.getTeacher().getFullName() + "</th>\n" +
                        "      <th><a href=\"controller?command=viewProfile&userId=" + course.getTeacher().getId() + "\">" + course.getTeacher().getEmail() + "</a></th>\n" +
                        "      <th><a href=\"controller?command=unfollowCourse&userId=" + user.getId() + "&courseId=" + course.getId() + "\">"+bundle.getString("course.unfollow")+"</a></th>\n" +
                        " </tr>";
            }
        }
        table += "</table>";
        return table;
    }

    String studentTableThatInProgress() throws ServletException, IOException {
        String table = "  <h3>"+bundle.getString("course.inprogress")+"</h3><table border=\"1\" >\n" +
                "        <tr class=\"header\">\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.name")+"</th>\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.start")+"</th>\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.end")+"</th>\n" +
                "            <th style=\"width: 20%;\">"+bundle.getString("course.teacher")+"</th>\n" +
                "            <th style=\"width: 20%;\">"+bundle.getString("course.email")+"</th>\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.unfollow")+"</th>\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.journal")+"</th>\n" +
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
                        "      <th><a href=\"controller?command=unfollowCourse&userId=" + user.getId() + "&courseId=" + course.getId() + "\">"+bundle.getString("course.unfollow")+"</a></th>\n" +
                        "      <th><a href=\"controller?command=showJournal&courseId=" + course.getId() + "&page=1\">"+bundle.getString("course.journal")+"</a></th>\n" +
                        " </tr>";
            }
        }
        table += "</table>";
        return table;
    }

    String studentTableThatFinished(){
        String table = "  <h3>"+bundle.getString("course.finished")+"</h3><table border=\"1\" >\n" +
                "        <tr class=\"header\">\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.name")+"</th>\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.start")+"</th>\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.end")+"</th>\n" +
                "            <th style=\"width: 20%;\">"+bundle.getString("course.teacher")+"</th>\n" +
                "            <th style=\"width: 20%;\">"+bundle.getString("course.email")+"</th>\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.unfollow")+"</th>\n" +
                "            <th style=\"width: 10%;\">"+bundle.getString("course.results")+"</th>\n" +
                "</tr>";
        for(Course course : coursesList) {
            if (course.getFinishDate().toLocalDate().isBefore(LocalDate.now())) {
                table += " <tr>\n" +
                        "      <th><a href=\"controller?command=viewCourse&courseId=" + course.getId() + "\">" + course.getName() + "</a></th>\n" +
                        "      <th>" + course.getStartDate() + "</th>\n" +
                        "      <th>" + course.getFinishDate() + "</th>\n" +
                        "      <th>" + course.getTeacher().getFullName() + "</th>\n" +
                        "      <th><a href=\"controller?command=viewProfile&userId=" + course.getTeacher().getId() + "\">" + course.getTeacher().getEmail() + "</a></th>\n" +
                        "      <th><a href=\"controller?command=unfollowCourse&userId=" + user.getId() + "&courseId=" + course.getId() + "\">"+bundle.getString("course.unfollow")+"</a></th>\n" +
                        "      <th><a href=\"controller?command=showJournal&courseId="+ course.getId() + "&page=1\">"+bundle.getString("course.results")+"</a></th>\n" +
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

    private static Properties setLocale(String lang, ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        Properties locale = new Properties();
        String name = "messages_"+lang+".properties";
        File file = new File(ShowCourses.class.getResource("/").getPath() + name);
        try {
            locale.load(new FileInputStream(ShowCourses.class.getResource("/").getPath() + name));
        } catch (IOException e) {
                locale.load(new FileInputStream(ShowCourses.class.getResource("/").getPath() + "messages.properties"));
        }
        return locale;
    }
}

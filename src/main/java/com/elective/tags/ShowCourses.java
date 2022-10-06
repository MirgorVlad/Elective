package com.elective.tags;

import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class ShowCourses extends TagSupport {

    private List<Course> coursesList;
    private String role;
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
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {

        try {
            pageContext.getOut().write(
                   out +  getOutTable()
            );
        } catch (IOException e) {
            throw new RuntimeException("Problem is here", e);
        }
        return SKIP_BODY;
    }

    private String getOutTable() {
        String outTable = "Empty set";
        if(role.equals(UserDAO.MANAGER_ROLE)){
           outTable = managerTable();
        }
        return outTable;
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
                    "      <th>"+course.getTeacher().getEmail()+"</th>\n" +
                    "      <th><a href=\"edit_course.jsp?courseId="+course.getId() +
                                                        "&name="+course.getName() +
                                                        "&description=" + course.getDescription() +
                                                        "&teacher="+course.getTeacher().getEmail() +
                                                        "&start="+course.getStartDate() +
                                                        "&finish="+course.getFinishDate()+"\">EDIT</a></th>\n" +
                    "      <th><a href=\"controller?command=deleteCourse&courseId="+course.getId()+"\">DELETE</a></th>\n" +
                    " </tr>";
        }
        return table;
    }

}

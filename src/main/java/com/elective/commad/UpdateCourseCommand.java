package com.elective.commad;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

public class UpdateCourseCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        int id = Integer.parseInt(req.getParameter("courseId"));
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        String tEmail = req.getParameter("teacherEmail");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date finishDate = Date.valueOf(req.getParameter("finishDate"));

        User teacher = courseDAO.getTeacher(userDAO.findByEmail(tEmail));
        Course course = createCourse(id, name, desc, startDate, finishDate, teacher);
        req.setAttribute("course", course);
        courseDAO.update(course, teacher);

        return "controller?command=viewCourse&courseId=" + id;
    }

    private Course createCourse(int id, String name, String desc, Date startDate, Date finishDate, User teacher) {
        Course course= new Course();

        course.setId(id);
        course.setName(name);
        course.setDescription(desc);
        course.setTeacher(teacher);
        course.setStartDate(startDate);
        course.setFinishDate(finishDate);
        return course;
    }
}

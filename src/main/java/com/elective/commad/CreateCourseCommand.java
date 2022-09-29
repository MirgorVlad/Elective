package com.elective.commad;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.dao.mysql.MysqlDAOFactory;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

public class CreateCourseCommand implements Command{

    private final DAOFactory daoFactory;

    public CreateCourseCommand(){
        daoFactory = MysqlDAOFactory.getInstance();
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, DBException {
        CourseDAO courseDAO = daoFactory.getCourseDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        String tEmail = req.getParameter("teacherEmail");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date finishDate = Date.valueOf(req.getParameter("finishDate"));

        User teacher = courseDAO.getTeacher(userDAO.findByEmail(tEmail));

        Course course= new Course();

        course.setName(name);
        course.setDescription(desc);
        course.setTeacher(teacher);
        course.setStartDate(startDate);
        course.setFinishDate(finishDate);

        courseDAO.create(course, teacher);

        System.out.println(startDate);

        return "manager.jsp";
    }
}

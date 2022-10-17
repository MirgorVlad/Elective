package com.elective;

import com.elective.command.CreateCourseCommand;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    static Logger log = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("user");

        String query = req.getQueryString();
        String page = req.getServletPath();


        if(user != null) {
            //MANAGER ACCESS
            log.log(Level.WARN, "Illegal access to manager functions");
            if (!user.getRole().equals(UserDAO.MANAGER_ROLE)) {
                if (page.equals("/controller")) {
                    if (query.split("&")[0].equals("command=manageCourses")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                    if (query.split("&")[0].equals("command=viewAllUsers")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                    if (query.split("&")[0].equals("command=deleteCourse")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                } else {
                    if (page.equals("/create_course.jsp")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                    if (page.equals("/manager.jsp")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                    if (page.equals("/edit_course.jsp")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                }
            }

            //Teacher access
            log.log(Level.WARN, "Illegal access to teacher functions");
            if (!user.getRole().equals(UserDAO.TEACHER_ROLE)) {
                if (page.equals("/controller")) {
                    if (query.split("&")[0].equals("command=viewTeacherAvailableCourses")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                    if (query.split("&")[0].equals("command=showStudentsInCourse")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                    if (query.split("&")[0].equals("command=showJournal")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                } else {
                    if(page.equals("/teacher.jsp")){
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                    if(page.equals("/edit_journal.jsp")){
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                }
            }

            //Student access
            log.log(Level.WARN, "Illegal access to student functions");
            if (!user.getRole().equals(UserDAO.STUDENT_ROLE)) {
                if (page.equals("/controller")) {
                    if (query.equals("command=viewStudentAvailableCourses")) {
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                } else {
                    if(page.equals("/student.jsp")){
                        req.getRequestDispatcher(ReferencePages.ACCESS_ERROR).forward(req, resp);
                    }
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

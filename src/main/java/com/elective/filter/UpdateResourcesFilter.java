package com.elective.filter;

import com.elective.ReferencePages;
import com.elective.command.JoinToCourseCommand;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UpdateResourcesFilter implements Filter {
    static Logger log = LogManager.getLogger(UpdateResourcesFilter.class);
    CourseDAO courseDAO;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        courseDAO = DAOFactory.getInstance().getCourseDAO();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String query = req.getQueryString();
        String page = req.getServletPath();
        String command = req.getParameter("command");

        if(command != null) {
            if (command.equals("login") || command.equals("registr")) {
                initDataForUser(req, resp);
            }
        }

        if(query != null){
            if(req.getQueryString().split("&")[0].equals("command=viewCoursesList")) {
                try {
                    List<String> topicList = courseDAO.getTopicList((String) req.getSession().getAttribute("lang"));
                    req.getSession().setAttribute("topicList", topicList);
                } catch (SQLException e) {
                    req.getRequestDispatcher(ReferencePages.ERROR_PAGE);
                }
            }
        }
        if(page.equals("/"+ReferencePages.MANAGER_PAGE) || page.equals("/" + ReferencePages.VIEW_COURSES_LIST)){
            try {
                List<String> topicList = courseDAO.getTopicList((String) req.getSession().getAttribute("lang"));
                req.getSession().setAttribute("topicList", topicList);
            } catch (SQLException e) {
                req.getRequestDispatcher(ReferencePages.ERROR_PAGE);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void initDataForUser(HttpServletRequest req, HttpServletResponse resp) {
        try {
            CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO();
            List<String> topicList = courseDAO.getTopicList((String) req.getSession().getAttribute("lang"));
            req.getSession().setAttribute("topicList", topicList);
        } catch (SQLException ex){
            try {
                req.getRequestDispatcher(ReferencePages.ERROR_PAGE).forward(req, resp);
            } catch (Exception e) {
                log.error("Cannot update data in filter");
                throw new RuntimeException(e);
            }
        }

    }
}

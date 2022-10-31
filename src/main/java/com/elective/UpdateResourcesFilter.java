package com.elective;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UpdateResourcesFilter implements Filter {
    CourseDAO courseDAO;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        courseDAO = DAOFactory.getInstance().getCourseDAO();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String query = req.getQueryString();
        String page = req.getServletPath();
        System.out.println(query);
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
}

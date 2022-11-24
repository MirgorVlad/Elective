package com.elective.filter;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

/**
 * This filter sets locale for page
 */
public class SessionLocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)

            throws IOException, ServletException {

        CourseDAO courseDAO;
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("lang") != null) {

            req.getSession().setAttribute("lang", req.getParameter("lang"));

        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
package com.elective;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})

public class SessionLocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)

            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("lang") != null) {

            req.getSession().setAttribute("lang", req.getParameter("lang"));

        }

        chain.doFilter(request, response);

    }

    public void destroy() {}

    public void init(FilterConfig arg0) throws ServletException {}

}
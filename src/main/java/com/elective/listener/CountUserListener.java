package com.elective.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


public class CountUserListener implements HttpSessionListener {
    ServletContext ctx=null;
    static int total=0,current=0;

    public void sessionCreated(HttpSessionEvent e) {
        total++;
        current++;

        ctx=e.getSession().getServletContext();
        ctx.setAttribute("totalusers", total);
        ctx.setAttribute("currentusers", current);

    }

    public void sessionDestroyed(HttpSessionEvent e) {
        current--;
        ctx.setAttribute("currentusers",current);
    }

}

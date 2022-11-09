package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.DAOFactory;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class FinalTestCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String date = req.getParameter("testDate");
        String start = req.getParameter("sTime");
        String finish = req.getParameter("fTime");

        Date startDate = new Date();
        startDate.setTime(getMillisFromEpoch(date, start));

        final String username = "mirgorodskiy295@gmail.com";
        final String password = "gcmhpcqjpwuxtscd";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mirgorodskiy295@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("mirgorodskiy295@gmail.com"));
            message.setSubject("A testing mail header !!!");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return ReferencePages.TEACHER_PAGE;
    }

    private long getMillisFromEpoch(String date, String time) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        long startTimeHour = LocalTime.parse(time).getHour() * 60 * 60 * 1000;
        long startTimeMinute = LocalTime.parse(time).getMinute() * 60 * 1000;;
        long startTime = startTimeHour + startTimeMinute;
        return startDate.getTime() + startTime;
    }

}

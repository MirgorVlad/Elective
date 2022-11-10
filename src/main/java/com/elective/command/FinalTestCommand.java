package com.elective.command;

import com.elective.Mailer;
import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DBException;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

public class FinalTestCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();

        String date = req.getParameter("testDate");
        String start = req.getParameter("sTime");
        String finish = req.getParameter("fTime");
        int courseId = Integer.parseInt(req.getParameter("courseId"));

        Date sendInTime = new Date();
        sendInTime.setTime(getMillisFromEpoch(date, start) - 3600000);

        List<Integer> userList = courseDAO.findStudentsInCourse(courseId);
        Course course = courseDAO.findById(courseId);

        informStudents(sendInTime, userList, course, date, start);


        return ReferencePages.TEACHER_PAGE;
    }

    private void informStudents(Date sendInTime, List<Integer> userList, Course course, String date, String time)
            throws SQLException, DBException {

        UserDAO userDAO = getDaoFactory().getUserDAO();
        for(int userId : userList){
            User user = userDAO.findById(userId);

            //send now
            Mailer.send(user.getEmail(), "Final test", "Course: "+course.getName()+"\nFinal Test will start at " + time + " on " + date);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Mailer.send(user.getEmail(), "Final test", "Course: "+course.getName()+"\nTest will start in a hour!!");
                }
            };
            //send an hour before
            timer.schedule(timerTask, sendInTime);
        }
    }


    private long getMillisFromEpoch(String date, String time) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        long startTimeHour = LocalTime.parse(time).getHour() * 60 * 60 * 1000;
        long startTimeMinute = LocalTime.parse(time).getMinute() * 60 * 1000;;
        long startTime = startTimeHour + startTimeMinute;
        return startDate.getTime() + startTime;
    }
}

package com.elective.tags;
import com.elective.ReferencePages;
import com.elective.db.dao.DAOFactory;
import com.elective.db.dao.DBException;
import com.elective.db.dao.JournalDAO;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Course;
import com.elective.db.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShowJournal extends TagSupport {
    private ResourceBundle bundle;
    private List<User> studentsList;
    private static final int step = 1;
    private static final int limit = 7;
    private int pageCount;
    private Course course;
    private final JournalDAO journalDAO = DAOFactory.getInstance().getJournalDAO();

    public void setCourse(Course course){
        this.course = course;
    }
    public void setStudentsList(List<User> studentsList){
        this.studentsList = studentsList;
    }
    @Override
    public int doStartTag() {
        getLocale(pageContext.getSession());
        pageCount = (int) Math.ceil(1.0*course.countDays() / limit);
        String page = pageContext.getRequest().getParameter("page");
        try {
            String out = "<h1>"+bundle.getString("journal.week")+" " +page+"</h1>" +
                    "<table border=\"1\">" +
                            printTable() +
                         "</table>";
            out += "<div class=\"pagination\">";
            for (int i = 1; i <= pageCount; i++){
                out += "<a href=\"controller?command=showJournal&courseId="+course.getId()+"&page="+i+"\">"+i+"</a>";
            }
            out+="</div>";
            pageContext.getOut().write(out);
        } catch (IOException | DBException | SQLException | ServletException e) {
            throw new RuntimeException("Cannot show journal", e);
        }
        return SKIP_BODY;
    }

    private String printTable() throws DBException, SQLException, ServletException, IOException {
        User user = (User) pageContext.getSession().getAttribute("user");

        try {
            if(user.getRole().equals(UserDAO.STUDENT_ROLE)){
                return printStudentJournal();
            }
            if(user.getRole().equals(UserDAO.TEACHER_ROLE)){
                return printTeacherJournal();
            }
        } catch (DBException | SQLException ex){
            pageContext.forward(ReferencePages.ERROR_PAGE);
        }

        return "No data";
    }

    private String printStudentJournal() throws DBException, SQLException {
        LocalDate startDate = course.getStartDate().toLocalDate();
        LocalDate finishDate = course.getFinishDate().toLocalDate();
        User student = (User)pageContext.getSession().getAttribute("user");
        int pageInfoCount = Integer.parseInt(pageContext.getRequest().getParameter("page"));
        int page = pageInfoCount;
        if(pageInfoCount > 1){
            pageInfoCount-=1;
            pageInfoCount = pageInfoCount*limit+1;
        }
        startDate = startDate.plusDays(pageInfoCount);
        String out = createDates(startDate, finishDate,  page, bundle.getString("journal.date"));
        String outGrades = "<tr><th>"+bundle.getString("journal.grade")+"</th>";
        for(LocalDate s = startDate; !s.isEqual(startDate.plusDays(limit)); s = s.plusDays(step)){
            if(s.isAfter(finishDate)){
                break;
            }
            outGrades += "<th>"+journalDAO.getGrade(course.getId(), student.getId(), Date.valueOf(s))+"</th>";  //instead i -> date.getGrade
            //startDate = startDate.plusDays(step);
        }
        if(page == pageCount) {
            outGrades += "<th>-</th>"; //finalTest;
            outGrades += "<th>" + journalDAO.sumOfStudentGrades(course.getId(), student.getId()) + "</th></tr>"; //total;
        }
        return out + outGrades;
    }

    private String printTeacherJournal() throws DBException, SQLException {
        LocalDate startDate = course.getStartDate().toLocalDate();
        LocalDate finishDate = course.getFinishDate().toLocalDate();
        int pageInfoCount = Integer.parseInt(pageContext.getRequest().getParameter("page"));
        int page = pageInfoCount;
        if(pageInfoCount > 1){
            pageInfoCount-=1;
            pageInfoCount = pageInfoCount*limit+1;
        }
        startDate = startDate.plusDays(pageInfoCount);
        String out = createDates(startDate, finishDate,  page, bundle.getString("journal.studentdate"));
        String studentRow = "";
        for(User user : studentsList) {

            studentRow += "<tr><th><a href=\"controller?command=viewProfile&userId="+user.getId()+"\">"+user.getFullName()+"</a></th>";
            for(LocalDate s = startDate; !s.isEqual(startDate.plusDays(limit)); s = s.plusDays(step)) {
                if (s.isAfter(finishDate)) {
                    break;
                }
                studentRow += "<th>" + journalDAO.getGrade(course.getId(), user.getId(), Date.valueOf(s)) + "</th>";
            }
            if(page == pageCount) {
                studentRow += "<th>-</th>"; //finalTest;
                studentRow += "<th>" + journalDAO.sumOfStudentGrades(course.getId(), user.getId()) + "</th></tr>"; //total;
            }
            startDate = course.getStartDate().toLocalDate().plusDays(pageInfoCount);
        }
        return out + studentRow;
    }

    private String createDates(LocalDate start, LocalDate finish, int page, String fColumnText){
        String out = "<tr class=\"header\"><th>"+fColumnText+"</th>";
        LocalDate s = start;
        for(int i = 0; i < limit; i+=step){
            if(s.isAfter(finish)){
                break;
            }
            out += "<th>" + s + "</th>";
            s = s.plusDays(step);
        }

        if(page == pageCount) {
            out += "<th>"+bundle.getString("journal.final")+"</th>" +
                    "<th>"+bundle.getString("journal.total")+"</th></tr>";
        }
        return out;
    }

    private void getLocale(HttpSession session) {
        String lang = "en";
        if( session.getAttribute("lang") != null)
            lang = (String) session.getAttribute("lang");

        Locale locale = Locale.of(lang);

        bundle = ResourceBundle.getBundle("messages", locale);
    }
}

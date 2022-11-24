package com.elective.command;

import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.entity.Assignment;
import com.elective.db.entity.Material;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileInputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
/**
 * Implementation of Command interface that perform uploading Material to page
 */
public class UploadMaterialToCourseCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();

        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String title = req.getParameter("title");
        String text = req.getParameter("description");
        String videoPath = req.getParameter("videoPath");
        String deadline = req.getParameter("deadline");
        String assignmentFile = req.getParameter("assignment");
        User user = (User)req.getSession().getAttribute("user");

        if(videoPath != null){
            courseDAO.saveMaterial(courseId, title, text, videoPath, Material.VIDEO);
        }
        else if(deadline != null){
            Date date = Date.valueOf(deadline);
            Assignment assignment = createAssignment(courseId, title, text, date, user, null);
            courseDAO.addAssignment(assignment);
        }
        else if(assignmentFile != null){
            Properties props = new Properties();
            props.load(new FileInputStream(DAOFactory.class.getResource("/").getPath() + "app.properties"));
            String uploads = props.getProperty("upload.assignment");

            Part filePart = req.getPart("assignmentFile");
            String fileName = filePart.getSubmittedFileName();

            String path = uploads + fileName;
            for (Part part : req.getParts()) {
                part.write(path);
            }

            courseDAO.addAssignment(createAssignment(courseId, title, null, Date.valueOf(LocalDate.now()), user, path));
        }
        else {
            Properties props = new Properties();
            props.load(new FileInputStream(DAOFactory.class.getResource("/").getPath() + "app.properties"));
            String uploads = props.getProperty("upload.lection");

            Part filePart = req.getPart("file");
            String fileName = filePart.getSubmittedFileName();

            String path = uploads + fileName;
            for (Part part : req.getParts()) {
                part.write(path);
            }

            courseDAO.saveMaterial(courseId, title, text, path, Material.LECTION);
        }

        return "controller?command=showMaterials&courseId=" + courseId;
    }

    private Assignment createAssignment(int courseId, String title, String text, Date deadline, User user, String path) {
        Assignment assignment = new Assignment();

        assignment.setCourse(courseId);
        assignment.setName(title);
        assignment.setDeadline(deadline);
        assignment.setDescription(text);
        assignment.setPath(path);
        assignment.setUser(user);

        return assignment;
    }
}

package com.elective.command;

import com.elective.Mailer;
import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.entity.Assignment;
import com.elective.db.entity.Material;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.Properties;

public class UploadMaterialToCourseCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();

        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String title = req.getParameter("title");
        String text = req.getParameter("description");
        String videoPath = req.getParameter("videoPath");
        String deadline = req.getParameter("deadline");

        if(videoPath != null){
            courseDAO.saveMaterial(courseId, title, text, videoPath, Material.VIDEO);
        }
        if(deadline != null){
            Date date = Date.valueOf(deadline);
            Assignment assignment = createAssignment(courseId, title, text, date);
            courseDAO.addAssignment(assignment);
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

    private Assignment createAssignment(int courseId, String title, String text, Date deadline) {
        Assignment assignment = new Assignment();

        assignment.setCourse(courseId);
        assignment.setName(title);
        assignment.setDeadline(deadline);
        assignment.setDescription(text);

        return assignment;
    }
}

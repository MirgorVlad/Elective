package com.elective.command;

import com.elective.Mailer;
import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.DAOFactory;
import com.elective.db.entity.Material;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileInputStream;
import java.util.Properties;

public class UploadMaterialToCourseCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();

        Properties props = new Properties();
        props.load(new FileInputStream(DAOFactory.class.getResource("/").getPath() + "app.properties"));
        String uploads = props.getProperty("upload.lection");

        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String title = req.getParameter("title");
        String text = req.getParameter("description");
        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        String path = uploads + fileName;
        for (Part part : req.getParts()) {
            part.write(path);
        }

        courseDAO.saveMaterial(courseId, title, text, path, Material.LECTION);

        return "controller?command=showMaterials&courseId=" + courseId;
    }
}

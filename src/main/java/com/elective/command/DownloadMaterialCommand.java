package com.elective.command;

import com.elective.db.dao.CourseDAO;
import com.elective.db.entity.Material;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;


public class DownloadMaterialCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();

        OutputStream out = resp.getOutputStream();

        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String materialName = req.getParameter("material");
        String type = req.getParameter("type");

        Material material = courseDAO.findMaterialByName(courseId, materialName, null);
        String filepath = material.getPath();
        String [] pathSplit = filepath.split("/");
        String filename = pathSplit[pathSplit.length-1];

        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
        resp.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");

        FileInputStream fileInputStream=new FileInputStream(filepath);

        int i;
        while ((i=fileInputStream.read()) != -1) {
            out.write(i);
        }
        fileInputStream.close();
        out.close();

        return "controller?command=viewMaterial&material="+materialName+"&courseId="+courseId+"&type=" + type;
    }
}

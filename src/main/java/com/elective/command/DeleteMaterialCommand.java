package com.elective.command;

import com.elective.db.dao.CourseDAO;
import com.elective.db.entity.Material;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteMaterialCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();

        String material = req.getParameter("material");
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        String type = req.getParameter("type");

        if(type.equals(Material.ASSIGNMENT))
            courseDAO.deleteAssignment(courseId, material);
        else
            courseDAO.deleteMaterial(courseId, material);

        return "controller?command=showMaterials&courseId=" + courseId;
    }
}

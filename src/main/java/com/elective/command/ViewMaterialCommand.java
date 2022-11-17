package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.entity.Material;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewMaterialCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        String materialName = req.getParameter("material");
        String type = req.getParameter("type");
        int courseId = Integer.parseInt(req.getParameter("courseId"));

        Material material = courseDAO.findMaterialByName(courseId, materialName, type);

        req.setAttribute("material", material);

        return ReferencePages.VIEW_MATERIAL;
    }
}

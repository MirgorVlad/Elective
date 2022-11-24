package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.dao.UserDAO;
import com.elective.db.entity.Assignment;
import com.elective.db.entity.Material;
import com.elective.db.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
/**
 * Implementation of Command interface that perform viewing appropriate Material
 */
public class ViewMaterialCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        String materialName = req.getParameter("material");
        String type = req.getParameter("type");
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        User user = (User) req.getSession().getAttribute("user");

        if(type.equals(Material.ASSIGNMENT)){
            Assignment material = courseDAO.findAssignmentByName(courseId, materialName);
            req.setAttribute("material", material);
            if(user.getRole().equals(UserDAO.TEACHER_ROLE)){
                List<Assignment> solutionList = courseDAO.getSolutions(courseId, materialName);
                req.setAttribute("solutionList", solutionList);
            }
        }
        else {
            Material material = courseDAO.findMaterialByName(courseId, materialName, type);
            req.setAttribute("material", material);
        }

        req.setAttribute("type", type);
        return ReferencePages.VIEW_MATERIAL;
    }
}

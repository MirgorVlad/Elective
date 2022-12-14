package com.elective.command;

import com.elective.ReferencePages;
import com.elective.db.dao.CourseDAO;
import com.elective.db.entity.Assignment;
import com.elective.db.entity.Course;
import com.elective.db.entity.Material;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
/**
 * Implementation of Command interface that perform displaying Materials for appropriate Course
 */
public class ShowMaterialsCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CourseDAO courseDAO = getDaoFactory().getCourseDAO();
        int courseId = Integer.parseInt(req.getParameter("courseId"));


        List<Material> lectionList = courseDAO.getAllMaterials(courseId, Material.LECTION);
        List<Material> videoList = courseDAO.getAllMaterials(courseId, Material.VIDEO);
        List<Assignment> assignmentList = courseDAO.getAllAssignments(courseId);

        for(Material mat : lectionList){
            String desc =  mat.getDescription();
            if(desc.length() > 120)
                mat.setDescription(desc.substring(0,120));
        }

        for(Material mat : videoList){
            String desc =  mat.getDescription();
            if(desc.length() > 120)
                mat.setDescription(desc.substring(0,120));
        }

        for(Assignment assignment : assignmentList){
            String desc =  assignment.getDescription();
            if(desc.length() > 120)
                assignment.setDescription(desc.substring(0,120));
        }

        Course course = courseDAO.findById(courseId);
        req.setAttribute("course", course);
        req.setAttribute("lectionList", lectionList);
        req.setAttribute("videoList", videoList);
        req.setAttribute("assignmentList", assignmentList);

        return ReferencePages.MATERIALS;
    }
}

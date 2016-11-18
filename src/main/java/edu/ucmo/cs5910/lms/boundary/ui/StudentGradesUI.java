/*
 * Class: edu.ucmo.cs5910.lms.boundary.ui.StudentGradesUI
 */
package edu.ucmo.cs5910.lms.boundary.ui;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.ucmo.cs5910.lms.controller.GradesMgr;
import edu.ucmo.cs5910.lms.entity.User;

/**
 * 
 */
@Controller
@SessionAttributes({ "user", "selectedCourseId", "selectedArea" })
public class StudentGradesUI {

    @Autowired
    private GradesMgr gradesMgr;

    @RequestMapping(value = "/userMain/STUDENT/grades/{classId}", method = RequestMethod.GET)
    public String getAjaxContentJSP(@PathVariable("classId") int argClassId, HttpSession session, Model model) {
        // TODO: replace this logic with logic to build the Student Grades page
        User userS = (User) session.getAttribute("user");

        model.addAttribute("area", "grades");
        model.addAttribute("classId", argClassId);
        model.addAttribute("msg", String.format("this is html response from ajax call to /userMain/STUDENT/grades/%d for user %s in session", argClassId, userS.getLoginName()));
        // returns the view name
        return "studentGrades";
    }
}

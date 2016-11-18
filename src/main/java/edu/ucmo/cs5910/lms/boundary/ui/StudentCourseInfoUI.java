/*
 * Class: edu.ucmo.cs5910.lms.boundary.ui.StudentCourseInfoUI
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

import edu.ucmo.cs5910.lms.controller.CourseInfoMgr;
import edu.ucmo.cs5910.lms.entity.CourseInfo;

/**
 * 
 */
@Controller
@SessionAttributes({ "user", "selectedCourseId", "selectedArea" })
public class StudentCourseInfoUI {

    @Autowired
    private CourseInfoMgr courseInfoMgr;

    @RequestMapping(value = "/userMain/STUDENT/courseInfo/{classId}", method = RequestMethod.GET)
    public String getAjaxContentJSP(@PathVariable("classId") int argClassId, HttpSession session, Model model) {
        CourseInfo courseInfo = courseInfoMgr.getCourseInfo(argClassId);

        model.addAttribute("area", "courseInfo");
        model.addAttribute("classId", argClassId);
        model.addAttribute("courseInfo", courseInfo);

        // returns the view name
        return "studentCourseInfo";
    }
}

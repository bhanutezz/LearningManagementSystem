/*
 * Class: edu.ucmo.cs5910.lms.boundary.ui.InstructorCourseInfoUI
 */
package edu.ucmo.cs5910.lms.boundary.ui;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.ucmo.cs5910.lms.controller.CourseInfoMgr;
import edu.ucmo.cs5910.lms.entity.CourseInfo;
import edu.ucmo.cs5910.lms.model.JsonResponse;

/**
 * 
 */
@Controller
@SessionAttributes({ "user", "selectedCourseId", "selectedArea" })
public class InstructorCourseInfoUI {

    @Autowired
    private CourseInfoMgr courseInfoMgr;

    @RequestMapping(value = "/userMain/INSTRUCTOR/courseInfo/{classId}",
    		method = RequestMethod.PUT,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public JsonResponse saveAjaxContentJSP(@PathVariable("classId") int argClassId, @RequestBody CourseInfo argCourseInfo) {
    	System.err.println(argCourseInfo);
    	courseInfoMgr.updateCourseInfo(argClassId, argCourseInfo);
    	
    	return new JsonResponse("OK", "");
    }

    @RequestMapping(value = "/userMain/INSTRUCTOR/courseInfo/{classId}", method = RequestMethod.GET)
    public String getAjaxContentJSP(@PathVariable("classId") int argClassId, HttpSession session, Model model) {
        CourseInfo courseInfo = courseInfoMgr.getCourseInfo(argClassId);

        model.addAttribute("area", "courseInfo");
        model.addAttribute("classId", argClassId);
        model.addAttribute("courseInfo", courseInfo);

        // returns the view name
        return "instructorCourseInfo";
    }
}

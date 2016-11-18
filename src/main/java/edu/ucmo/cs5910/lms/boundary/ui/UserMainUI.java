/*
 * Class: edu.ucmo.cs5910.lms.controller.UserMainUI
 */
package edu.ucmo.cs5910.lms.boundary.ui;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.ucmo.cs5910.lms.controller.CourseMgr;
import edu.ucmo.cs5910.lms.entity.Course;
import edu.ucmo.cs5910.lms.entity.User;

/**
 * 
 */
@Controller
@SessionAttributes({ "user", "selectedCourseId", "selectedArea" })
public class UserMainUI {

    @Autowired
    private CourseMgr classMgr;

    @RequestMapping("/userMain")
    public String showStudentMain(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            System.out.println(String.format("user is %s in session", user.getLoginName()));
        } else {
            System.err.println("user was not found in session");
        }

        if (model.containsAttribute("user")) {
            user = (User) model.asMap().get("user");
            System.err.println(String.format("user is %s in model", user.getLoginName()));
        } else {
            System.err.println("user was not found in model");
        }

        model.addAttribute("user", user);

        if (user.isStudent()) {
            model.addAttribute("portalHeading", "This is the LMS student portal!");
            StringBuilder buff = new StringBuilder("You can use this portal to ");
            buff.append("<ul>\n");
            buff.append("<li>Review course information by examining online content or uploaded documents</li>\n");
            buff.append("<li>Participate in discussions by creating them, reviewing them, and responding to them</li>\n");
            buff.append("<li>Submit assignments by directly entering assignment material online, or by uploading documents</li>\n");
            buff.append("<li>Take quizzes online</li>\n");
            buff.append("<li>Review grades for assignments, quizzes, and any external activities that are graded</li>\n");
            buff.append("</ul>\n");
            model.addAttribute("portalMsg", buff.toString());
        } else {
            model.addAttribute("portalHeading", "This is the LMS Instructor portal!");
            StringBuilder buff = new StringBuilder("You can use this portal to ");
            buff.append("<ul>\n");
            buff.append("<li>Manage courses by making them available or unavailable to individual students,");
            buff.append(" and by adding, updating, or removing information (including announcements)</li>\n");
            buff.append("<li>Manage discussions by monitoring them, participating in them, or removing them if inappropriate</li>\n");
            buff.append("<li>Manage assignments by creating and modifying them, and receiving assignment submissions</li>\n");
            buff.append("<li>Manage quizzes by creating and modifying them, and grading quiz submissions</li>\n");
            buff.append("<li>Post grades for assignments, quizzes, and any external activities that are graded</li>\n");
            buff.append("</ul>\n");
            model.addAttribute("portalMsg", buff.toString());
        }

        List<Course> courseList = classMgr.getCourseList(user);
        model.addAttribute("courseList", courseList);
        model.addAttribute("selectedCourseId", courseList.get(0).getId());
        model.addAttribute("selectedArea", "courseInfoTab");

        // returns the view name
        return "userMain";

    }
}

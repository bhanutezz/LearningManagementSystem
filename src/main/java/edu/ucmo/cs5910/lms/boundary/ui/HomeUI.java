/*
 * Class: edu.ucmo.cs5910.lms.boundary.ui.HomeUI
 */
package edu.ucmo.cs5910.lms.boundary.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import edu.ucmo.cs5910.lms.controller.UserMgr;
import edu.ucmo.cs5910.lms.entity.User;
import edu.ucmo.cs5910.lms.model.LoginForm;

/**
 * 
 */
@Controller
@SessionAttributes("user")
public class HomeUI {

    @Autowired
    private UserMgr userMgr;

    @RequestMapping("/home")
    public String home(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

        model.addAttribute("name", name);
        // returns the view name
        return "home";

    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView loginRequest(@ModelAttribute LoginForm loginForm) {
        ModelAndView modelAndView = new ModelAndView();
        if (userMgr.validateLogin(loginForm)) {
            User user = userMgr.loadUser(loginForm.getLoginName());
            modelAndView.addObject("user", user);

            modelAndView.setViewName("redirect:/userMain");
        } else {
            modelAndView.addObject("loginFailure", "email / password mismatch");
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutRequest(SessionStatus status, Model model) {
        status.setComplete();
        model.asMap().clear();
        return "redirect:/home";
    }

}

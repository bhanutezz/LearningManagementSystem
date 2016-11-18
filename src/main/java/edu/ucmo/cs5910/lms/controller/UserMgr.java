/*
 * Class: edu.ucmo.cs5910.lms.controller.UserMgr
 */
package edu.ucmo.cs5910.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ucmo.cs5910.lms.boundary.dao.UserDAO;
import edu.ucmo.cs5910.lms.entity.User;
import edu.ucmo.cs5910.lms.model.LoginForm;

/**
 * 
 */
@Service
public class UserMgr {
    @Autowired
    private UserDAO userDao;

    /**
     * @param argLoginName
     * @return
     */
    public User loadUser(String argLoginName) {
        User user = userDao.loadUser(argLoginName);

        return user;
    }

    /**
     * @param argLoginForm
     * @return
     */
    public boolean validateLogin(LoginForm argLoginForm) {
        // for demo purposes
        // confirm user login name is recognized
        // AND password equals login name
        return userDao.userExists(argLoginForm.getLoginName())
                        && argLoginForm.getLoginName().equalsIgnoreCase(argLoginForm.getPassword());
    }

}

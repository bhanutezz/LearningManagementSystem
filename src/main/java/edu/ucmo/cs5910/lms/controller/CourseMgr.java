/*
 * Class: edu.ucmo.cs5910.lms.controller.ClassMgr
 */
package edu.ucmo.cs5910.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ucmo.cs5910.lms.boundary.dao.CourseDAO;
import edu.ucmo.cs5910.lms.entity.Course;
import edu.ucmo.cs5910.lms.entity.User;

/**
 * 
 */
@Service
public class CourseMgr {

    @Autowired
    private CourseDAO courseDao;

    /**
     * @param argUser
     * @return
     */
    public List<Course> getCourseList(User argUser) {

        if (argUser.isStudent()) {
            return courseDao.getStudentCourseList(argUser);
        }

        return courseDao.getInstructorCourseList(argUser);
    }

	public Course getCourse(int argCourseId) {
        return courseDao.getCourse(argCourseId);
	}

}

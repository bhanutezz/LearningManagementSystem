package edu.ucmo.cs5910.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ucmo.cs5910.lms.boundary.dao.CourseInfoDAO;
import edu.ucmo.cs5910.lms.boundary.dao.RefMaterialDAO;
import edu.ucmo.cs5910.lms.entity.CourseInfo;

/**
 * 
 */
@Service
public class CourseInfoMgr {
    @Autowired
    private CourseInfoDAO courseInfoDao;

    @Autowired
    private RefMaterialDAO refMaterialDAO;

    public CourseInfo getCourseInfo(int argClassId) {
    	CourseInfo courseInfo = courseInfoDao.getCourseInfo(argClassId);
    	courseInfo.setAttachments(refMaterialDAO.getFileList(courseInfo.getCourse()));
        return courseInfo;
    }

	public void updateCourseInfo(int argClassId, CourseInfo argCourseInfo) {
		courseInfoDao.updateCourseInfo(argClassId, argCourseInfo);
	}

}

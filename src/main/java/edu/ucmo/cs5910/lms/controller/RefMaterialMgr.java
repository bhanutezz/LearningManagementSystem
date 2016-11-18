package edu.ucmo.cs5910.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.ucmo.cs5910.lms.boundary.dao.RefMaterialDAO;
import edu.ucmo.cs5910.lms.entity.Course;

@Service
public class RefMaterialMgr {
	@Autowired
	private RefMaterialDAO refMaterialsDao;
	
	public List<String> getFileList(Course argCourse) {
		return refMaterialsDao.getFileList(argCourse);
	}

	public void addFile(Course argCourse, MultipartFile argFile) throws Exception {
    	
        if (argFile.isEmpty()) {
            throw new Exception(argFile.getOriginalFilename() + "  was empty.");
        }            
        
        refMaterialsDao.addFile(argCourse, argFile);
	}
}

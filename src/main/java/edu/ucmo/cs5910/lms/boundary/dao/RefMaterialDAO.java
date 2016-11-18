package edu.ucmo.cs5910.lms.boundary.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import edu.ucmo.cs5910.lms.entity.Course;

@Repository
public class RefMaterialDAO {
	private static final String rootPath;
	
	static {
		if (System.getenv("OPENSHIFT_DATA_DIR") == null) {
			rootPath = System.getProperty("OPENSHIFT_DATA_DIR");
		} else {
			rootPath = System.getenv("OPENSHIFT_DATA_DIR");
		}
	}

	public static String getRootPath() {
		return rootPath;
	}
	
	public List<String> getFileList(Course argCourse) {
		System.err.println("rootPath is " + rootPath);
		List<String> filenames = new ArrayList<String>();
		File folder = new File(rootPath + argCourse.getId());
        if (!folder.exists())
        	folder.mkdirs();

        File[] listOfFiles = folder.listFiles();
		
	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	  if (listOfFiles[i].getName().startsWith(".")) {
		    	  System.err.println("Ignoring system file '" + listOfFiles[i].getName() + "'");
	    	  } else {
	    		  filenames.add(listOfFiles[i].getName());
	    	  }
	      } else if (listOfFiles[i].isDirectory()) {
	    	  System.err.println("Ignoring Directory " + listOfFiles[i].getName());
	      }
	    }
	    
		return filenames;
	}

	public void addFile(Course argCourse, MultipartFile argFile) throws Exception {
        
    	byte[] bytes = argFile.getBytes();
 
        // Creating the directory to store file
                
        // *** OPENSHIFT *** $OPENSHIFT_DATA_DIR
        File dir = new File(rootPath + argCourse.getId());
        if (!dir.exists())
            dir.mkdirs();
 
        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath() + File.separator + argFile.getOriginalFilename());
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
 
        System.err.println("Server File Location=" + serverFile.getAbsolutePath());
	}

}

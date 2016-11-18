package edu.ucmo.cs5910.lms.boundary.ui;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.ucmo.cs5910.lms.boundary.dao.RefMaterialDAO;
import edu.ucmo.cs5910.lms.controller.CourseMgr;
import edu.ucmo.cs5910.lms.controller.RefMaterialMgr;
import edu.ucmo.cs5910.lms.entity.Course;
import edu.ucmo.cs5910.lms.model.JsonResponse;
 
/**
 * Handles requests for the application file upload requests
 */
@Controller
public class RefMaterialsUI {
 
	@Autowired
	private RefMaterialMgr refMaterialsMgr;
	
	@Autowired
	private CourseMgr courseMgr;
	
    @RequestMapping("/fileList/{course_id}")
    public String showUploadPage(@PathVariable("course_id") Integer argCourseId, Model model) {
//    @RequestMapping("/fileList")
//    public String showUploadPage(Model model) {
//    	Integer argCourseId = 3;
    	Course course = courseMgr.getCourse(argCourseId);
    	
    	model.addAttribute("courseId", argCourseId);
    	model.addAttribute("files", refMaterialsMgr.getFileList(course));
        return "upload";
    }
    
    @RequestMapping(value = "/files/{course_id}/{file_name:.+}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("course_id") Integer argCourseId, @PathVariable("file_name") String fileName) {
    	String rootPath = RefMaterialDAO.getRootPath();
        return new FileSystemResource(new File(rootPath + argCourseId + File.separator + fileName)); 
    }
    
    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/uploadMyFile/{course_id}", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse jsonUploadFileHandler(@PathVariable("course_id") Integer argCourseId, @RequestParam("file") MultipartFile file) {
    	try {

        	Course course = courseMgr.getCourse(argCourseId);

        	refMaterialsMgr.addFile(course, file);
    		return new JsonFileUploadResponse("OK", "", refMaterialsMgr.getFileList(course));
        } catch (Exception e) {
        	System.err.println("failed to upload file: " + e.getMessage());
        	e.printStackTrace(System.err);
            return new JsonResponse("FAILURE", "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
        }
    }
    
    public class JsonFileUploadResponse extends JsonResponse {
    	private List<String> fileNames;

		JsonFileUploadResponse(String argStatus, String argMessage, List<String> argFilenames) {
    		super(argStatus, argMessage);
    		setFileNames(argFilenames);
    	}

		public List<String> getFileNames() {
			return fileNames;
		}

		public void setFileNames(List<String> fileNames) {
			this.fileNames = fileNames;
		}
    	
    }
}

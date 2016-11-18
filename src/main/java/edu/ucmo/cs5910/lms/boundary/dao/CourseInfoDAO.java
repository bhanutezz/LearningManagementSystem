package edu.ucmo.cs5910.lms.boundary.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.ucmo.cs5910.lms.entity.Course;
import edu.ucmo.cs5910.lms.entity.CourseInfo;
import edu.ucmo.cs5910.lms.model.Schedule;

@Repository
public class CourseInfoDAO {
    private static Map<Integer, CourseInfo> COURSE_INFO = new HashMap<Integer, CourseInfo>();

    // create a few CourseInfo that will always be around
    static {
        // note: this needs to line up with the courses defined in CourseDAO
        int i = 1;
        for (Course course : CourseDAO.getMasterCourseList()) {
            String desc = "This is " + course.getTitle() + ", a wonderful course!";

            // do some silly stuff to create some schedules
            Schedule schedule = new Schedule("", "", "");
            if ((++i % 2) == 0) {
                schedule.setDayOfWeek("Monday");
            } else {
                schedule.setDayOfWeek("Tuesday");
            }
            int startHour = 10 + (i % 4);
            schedule.setStartTime(String.format("%d:00", startHour));
            schedule.setEndTime(String.format("%d:50", startHour));

            CourseInfo courseInfo = new CourseInfo(course.getId(), course, desc, schedule);
            courseInfo.addAttachment("Syllabus");
            if ((++i % 2) == 0) {
            	courseInfo.addAttachment("Midterm Review");
            } else {
            	courseInfo.addAttachment("HW 1 Extra Credit");
            }
            
            COURSE_INFO.put(course.getId(), courseInfo);
        }
    }

    public CourseInfo getCourseInfo(int argClassId) {
        for (CourseInfo courseInfo : COURSE_INFO.values()) {
            if (courseInfo.getCourse().getId() == argClassId) {
                return courseInfo;
            }
        }
        return null;
    }

	public void updateCourseInfo(int argClassId, CourseInfo argCourseInfo) {
        for (CourseInfo courseInfo : COURSE_INFO.values()) {
            if (courseInfo.getCourse().getId() == argClassId) {
                courseInfo.updateFields(argCourseInfo);
            }
        }
	}
}

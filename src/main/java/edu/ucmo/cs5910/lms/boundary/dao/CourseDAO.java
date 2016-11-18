/*
 * Class: edu.ucmo.cs5910.lms.boundary.dao.CourseDAO
 */
package edu.ucmo.cs5910.lms.boundary.dao;

import static edu.ucmo.cs5910.lms.entity.User.USER_BHANU;
import static edu.ucmo.cs5910.lms.entity.User.USER_SAM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.ucmo.cs5910.lms.entity.Course;
import edu.ucmo.cs5910.lms.entity.User;

/**
 * For demo usage, there is no database layer. Any users that need to exist across system restarts should be added to the static
 * member COURSES.
 */
@Repository
public class CourseDAO {
    /** the master list of courses. */
    private static final Map<Integer, Course> COURSES = new HashMap<Integer, Course>();

    /** the mapping of student to the course they take. */
    private static final Map<String, List<Course>> USER_COURSE_MAP = new HashMap<String, List<Course>>();

    static {
        List<Course> samCourses = new ArrayList<Course>();
        List<Course> bhanuCourses = new ArrayList<Course>();

        /* populate the master list of all courses. */
        // the instructor needs to match up with the "displayName" field of instructor users in UserDAO

        // user sam will be enrolled in these three courses
        Course course = new Course(1, "CS5500", "Advanced Operating Systems", 13677, "Fred Flintstone");
        COURSES.put(course.getId(), course);
        samCourses.add(course);
        course = new Course(2, "CS5910", "Advanced Software Engineering", 13678, "Roger Reed");
        COURSES.put(course.getId(), course);
        samCourses.add(course);
        course = new Course(3, "CS5900", "Compiler Design and Construction", 13679, "Ken Kaplan");
        COURSES.put(course.getId(), course);
        samCourses.add(course);

        // user bhanu will be enrolled in these three courses
        course = new Course(4, "CS5500", "Advanced Operating Systems", 23677, "Fred Flintstone");
        COURSES.put(course.getId(), course);
        bhanuCourses.add(course);
        course = new Course(5, "CS5910", "Advanced Software Engineering", 23678, "Roger Reed");
        COURSES.put(course.getId(), course);
        bhanuCourses.add(course);
        course = new Course(6, "CS5900", "Compiler Design and Construction", 23679, "Ken Kaplan");
        COURSES.put(course.getId(), course);
        bhanuCourses.add(course);

        // add some further courses for the instructors
        course = new Course(7, "CS5500", "Advanced Operating Systems", 33679, "Ken Kaplan");
        COURSES.put(course.getId(), course);
        course = new Course(8, "CS5500", "Advanced Operating Systems", 33678, "Roger Reed");
        COURSES.put(course.getId(), course);
        course = new Course(9, "CS5900", "Compiler Design and Construction", 33677, "Fred Flintstone");
        COURSES.put(course.getId(), course);
        course = new Course(10, "CS5900", "Compiler Design and Construction", 43609, "Ken Kaplan");
        COURSES.put(course.getId(), course);
        course = new Course(11, "CS5910", "Advanced Software Engineering", 49678, "Roger Reed");
        COURSES.put(course.getId(), course);
        course = new Course(12, "CS5500", "Advanced Operating Systems", 43677, "Fred Flintstone");
        COURSES.put(course.getId(), course);

        /* create a mapping of courses for our two demo students */
        USER_COURSE_MAP.put(USER_SAM.getLoginName(), samCourses);
        USER_COURSE_MAP.put(USER_BHANU.getLoginName(), bhanuCourses);
    }

    static public List<Course> getMasterCourseList() {
        List<Course> list = new ArrayList<Course>();
        list.addAll(COURSES.values());

        return list;
    }

    /**
     * @param argInstructorUser
     * @return
     */
    public List<Course> getInstructorCourseList(User argInstructorUser) {
        List<Course> courseList = new ArrayList<Course>();

        for (Course course : COURSES.values()) {
            if (argInstructorUser.getDisplayName().equals(course.getInstructor())) {
                courseList.add(course);
            }
        }

        return courseList;
    }

    /**
     * @param argStudentUser
     * @return
     */
    public List<Course> getStudentCourseList(User argStudentUser) {
        List<Course> courseList = new ArrayList<Course>();

        courseList.addAll(USER_COURSE_MAP.get(argStudentUser.getLoginName()));

        return courseList;
    }

	public Course getCourse(int argCourseId) {
		return COURSES.get(argCourseId);
	}
}

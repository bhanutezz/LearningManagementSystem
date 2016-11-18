package edu.ucmo.cs5910.lms.entity;

import java.util.ArrayList;
import java.util.List;

import edu.ucmo.cs5910.lms.model.Schedule;

public class CourseInfo {
    private int id;
    private Course course;
    private String description;
    private Schedule schedule;
    private final List<Attachment> attachments = new ArrayList<Attachment>();

    public CourseInfo() {
    	schedule = new Schedule();
    }
    
    public CourseInfo(int id, Course course, String description, Schedule schedule) {
        super();
        this.id = id;
        this.course = course;
        this.description = description;
        this.schedule = schedule;
    }

    public void addAttachment(String argFilename) {
    	attachments.add(new Attachment(argFilename));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CourseInfo other = (CourseInfo) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public Course getCourse() {
        return course;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "CourseInfo [id=" + id + ", course=" + course + ", description=" + description + ", schedule=" + schedule
                        + ", attachments=" + attachments + "]";
    }

	public void updateFields(CourseInfo argCourseInfo) {
		setDescription(argCourseInfo.getDescription());
		setSchedule(argCourseInfo.getSchedule());
	}

	public void setAttachments(List<String> fileList) {
		attachments.clear();
		for (String filename: fileList) {
			attachments.add(new Attachment(filename));
		}
	}
}

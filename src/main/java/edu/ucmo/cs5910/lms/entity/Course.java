/*
 * Class: edu.ucmo.cs5910.lms.entity.Course
 */
package edu.ucmo.cs5910.lms.entity;

/**
 * 
 */
public class Course {
    /** the primary key for a course is id. */
    private int id;
    private int section;
    private String code;
    private String instructor;
    private String title;

    /**
     * @param argI
     * @param argString
     * @param argString2
     */
    public Course(int argId, String argCode, String argTitle, int argSection, String argInstructor) {
        id = argId;
        code = argCode;
        title = argTitle;
        section = argSection;
        instructor = argInstructor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        Course other = (Course) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * @return the section
     */
    public int getSection() {
        return section;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * @param argCode the code to set
     */
    public void setCode(String argCode) {
        code = argCode;
    }

    /**
     * @param argId the id to set
     */
    public void setId(int argId) {
        id = argId;
    }

    /**
     * @param argInstructor the instructor to set
     */
    public void setInstructor(String argInstructor) {
        instructor = argInstructor;
    }

    /**
     * @param argSection the section to set
     */
    public void setSection(int argSection) {
        section = argSection;
    }

    /**
     * @param argTitle the title to set
     */
    public void setTitle(String argTitle) {
        title = argTitle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Course [id=");
        builder.append(id);
        builder.append(", code=");
        builder.append(code);
        builder.append(",title=");
        builder.append(title);
        builder.append(", section=");
        builder.append(section);
        builder.append(",instructor=");
        builder.append(instructor);
        builder.append("]");
        return builder.toString();
    }

}

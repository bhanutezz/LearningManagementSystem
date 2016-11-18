/*
 * Class: edu.ucmo.cs5910.lms.entity.User
 */
package edu.ucmo.cs5910.lms.entity;

import static edu.ucmo.cs5910.lms.model.UserType.INSTRUCTOR;
import static edu.ucmo.cs5910.lms.model.UserType.STUDENT;
import edu.ucmo.cs5910.lms.model.UserType;

/**
 * The User of LMS.
 */
public class User {
    private final String displayName;
    /** loginName is the primary key for the User. */
    private final String loginName;
    private final UserType userType;

    // for demo purposes, make some globally known users to simplify the implementation.
    public static final User USER_SAM = new User("sss", STUDENT, "Sam Student");
    public static final User USER_BHANU = new User("bbb", STUDENT, "Bhanu Kuppili");

    /** the display name needs to match the instructor field in the CourseDAO definition. */
    public static final User USER_ROGER = new User("RRR", INSTRUCTOR, "Roger Reed");
    /** the display name needs to match the instructor field in the CourseDAO definition. */
    public static final User USER_KEN = new User("KKK", INSTRUCTOR, "Ken Kaplan");
    /** the display name needs to match the instructor field in the CourseDAO definition. */
    public static final User USER_FRED = new User("FFF", INSTRUCTOR, "Fred Flintstone");

    public User(String loginName, UserType userType, String displayName) {
        super();
        this.loginName = loginName;
        this.userType = userType;
        this.displayName = displayName;
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
        User other = (User) obj;
        if (loginName == null) {
            if (other.loginName != null) {
                return false;
            }
        } else if (!loginName.equals(other.loginName)) {
            return false;
        }
        return true;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @return the userType
     */
    public UserType getUserType() {
        return userType;
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
        result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
        return result;
    }

    /**
     * @return
     */
    public boolean isInstructor() {
        return userType == UserType.INSTRUCTOR;
    }

    /**
     * @return
     */
    public boolean isStudent() {
        return userType == UserType.STUDENT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [");
        if (loginName != null) {
            builder.append("loginName=");
            builder.append(loginName);
            builder.append(", ");
        }
        if (loginName != null) {
            builder.append("displayName=");
            builder.append(displayName);
            builder.append(", ");
        }
        if (userType != null) {
            builder.append("userType=");
            builder.append(userType);
        }
        builder.append("]");
        return builder.toString();
    }

}

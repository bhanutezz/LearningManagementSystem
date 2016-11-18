package edu.ucmo.cs5910.lms.boundary.dao;

import static edu.ucmo.cs5910.lms.entity.User.USER_BHANU;
import static edu.ucmo.cs5910.lms.entity.User.USER_FRED;
import static edu.ucmo.cs5910.lms.entity.User.USER_KEN;
import static edu.ucmo.cs5910.lms.entity.User.USER_ROGER;
import static edu.ucmo.cs5910.lms.entity.User.USER_SAM;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.ucmo.cs5910.lms.entity.User;

/**
 * The User persistence logic.
 */
@Repository
public class UserDAO {
    /**
     * For demo usage, there is no database layer. Any users that need to exist across system restarts should be added to the
     * static member USERS.
     * 
     * for demo purposes, convention is lowercase login name is student, uppercase login name is instructor
     */
    static private final Map<String, User> USERS = new HashMap<String, User>();

    static {
        /* populate USERS */
        USERS.put(USER_SAM.getLoginName(), USER_SAM);
        USERS.put(USER_BHANU.getLoginName(), USER_BHANU);
        USERS.put(USER_ROGER.getLoginName(), USER_ROGER);
        USERS.put(USER_KEN.getLoginName(), USER_KEN);
        USERS.put(USER_FRED.getLoginName(), USER_FRED);
    }

    public User loadUser(String argLoginName) {
        return USERS.get(argLoginName);
    }

    public boolean userExists(String userLoginName) {
        return USERS.containsKey(userLoginName);
    }

}

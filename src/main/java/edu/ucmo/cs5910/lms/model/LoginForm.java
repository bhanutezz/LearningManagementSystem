/*
 * Class: edu.ucmo.cs5910.lms.model.LoginForm
 */
package edu.ucmo.cs5910.lms.model;

/**
 * 
 */
public class LoginForm {
    private String loginName;
    private String password;

    /**
     * 
     */
    public LoginForm() {
        // TODO Auto-generated constructor stub
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
        LoginForm other = (LoginForm) obj;
        if (loginName == null) {
            if (other.loginName != null) {
                return false;
            }
        } else if (!loginName.equals(other.loginName)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        return true;
    }

    /**
     * @return the email
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
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
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    /**
     * @param argEmail the email to set
     */
    public void setLoginName(String argEmail) {
        loginName = argEmail;
    }

    /**
     * @param argPassword the password to set
     */
    public void setPassword(String argPassword) {
        password = argPassword;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginForm [");
        if (loginName != null) {
            builder.append("email=");
            builder.append(loginName);
            builder.append(", ");
        }
        if (password != null) {
            builder.append("password=");
            builder.append(password);
        }
        builder.append("]");
        return builder.toString();
    }

}

package com.vizaco.onlinecontrol.model;

/**
 * Simple business object representing a user.
 *
 */
public class ChangePassword {

    private String passwordOld;

    private String password;

    private String passwordConfirm;

    public ChangePassword() {
    }

    public ChangePassword(String passwordOld, String password, String passwordConfirm) {
        this.passwordOld = passwordOld;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}

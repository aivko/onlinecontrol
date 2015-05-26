package com.vizaco.onlinecontrol.security;

/**
 * Created by super on 5/14/15.
 */
public interface PasswordHandler {

    void changePassword(String username, String password);

    void encodeAll();

}

package com.vizaco.onlinecontrol.security.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Created by super on 6/4/15.
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final Log logger = LogFactory.getLog(getClass());

    /**
     * @return false always
     */
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        logger.warn("Denying user " + authentication.getName() + " permission '" + permission + "' on object " + target);
        return false;
    }

    /**
     * @return false always
     */
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
                                 Object permission) {
        logger.warn("Denying user " + authentication.getName() + " permission '" + permission + "' on object with Id '"
                + targetId);
        return false;
    }

}
package com.vizaco.onlinecontrol.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by super on 6/5/15.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalSecurityConfig extends GlobalMethodSecurityConfiguration {

    public Map<String, List<ConfigAttribute>> protectPointcutMap() {
        Map<String, List<ConfigAttribute>> map = new HashMap<>();

        // all the necessary rules go here
        map.put("execution(* com.vizaco.onlinecontrol.service.UserService.findUserById(..))", SecurityConfig.createList("ROLE_USER"));
        map.put("execution(* com.vizaco.onlinecontrol.service.UserService.getAllUsers(..))", SecurityConfig.createList("ROLE_USER"));
        map.put("execution(* com.vizaco.onlinecontrol.service.UserService.saveUser(..))", SecurityConfig.createList("ROLE_USER"));
        map.put("execution(* com.vizaco.onlinecontrol.service.UserService.deleteUser(..))", SecurityConfig.createList("ROLE_ADMIN"));

        map.put("execution(* com.vizaco.onlinecontrol.service.StudentService..*(..))", SecurityConfig.createList("ROLE_USER"));
        map.put("execution(* com.vizaco.onlinecontrol.service.ClazzService..*(..))", SecurityConfig.createList("ROLE_USER"));
        map.put("execution(* com.vizaco.onlinecontrol.service.ParentService..*(..))", SecurityConfig.createList("ROLE_USER"));
        map.put("execution(* com.vizaco.onlinecontrol.service.RoleService..*(..))", SecurityConfig.createList("ROLE_USER"));

        return map;
    }

    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return new MapBasedMethodSecurityMetadataSource(protectPointcutMap());
    }

}

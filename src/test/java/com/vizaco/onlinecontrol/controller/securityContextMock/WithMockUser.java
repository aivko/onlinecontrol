package com.vizaco.onlinecontrol.controller.securityContextMock;

import com.vizaco.onlinecontrol.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.*;
import java.util.Set;

/**
 * Created by super on 4/7/15.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = WithMockUserSecurityContextFactory.class)
public @interface WithMockUser {

    long id() default 1L;

    String login() default "login";

    String password() default "password";

    String[] roles();

}

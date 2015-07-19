package com.vizaco.onlinecontrol.controller.securityContextMock;

import com.vizaco.onlinecontrol.model.Role;
import com.vizaco.onlinecontrol.model.User;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by super on 4/7/15.
 */
public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockUser customUser) {

        Set<Role> authorities = new HashSet<>();
        for(String role : customUser.roles()) {

            Role roleNew = new Role();
            roleNew.setName(role);
            roleNew.setDescription(role);
            authorities.add(roleNew);
        }

        User principal = new User();
        principal.setEmail(customUser.email());
        principal.setPassword(customUser.password());
        principal.setRoles(authorities);
        principal.setId(customUser.id());

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;

    }

}
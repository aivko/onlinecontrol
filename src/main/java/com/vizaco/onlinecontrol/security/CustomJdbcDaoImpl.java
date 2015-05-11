//package com.vizaco.onlinecontrol.security;
//
//import com.vizaco.onlinecontrol.model.Role;
//import com.vizaco.onlinecontrol.model.Student;
//import com.vizaco.onlinecontrol.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.dao.SaltSource;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.List;
//
///**
// * Created by super on 5/11/15.
// */
//public class CustomJdbcDaoImpl extends JdbcDaoImpl {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public void changePassword(String username, String password) {
//        UserDetails user = loadUserByUsername(username);
////        String encodedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(user));
//        String encodedPassword = passwordEncoder.encode(password);
//        getJdbcTemplate().update("UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?", encodedPassword, username);
//    }
//
//    @Override
//    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
//        User user = (User) userFromUserQuery;
//        String returnUsername = userFromUserQuery.getUsername();
//
////        return new User(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
////                true, true, true, combinedAuthorities);
//        return new User(returnUsername, userFromUserQuery.getPassword(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.isEnabled(), user.getStudents(), combinedAuthorities);
//    }
//}

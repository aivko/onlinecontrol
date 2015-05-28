package com.vizaco.onlinecontrol.security.impl;

import com.vizaco.onlinecontrol.model.Person;
import com.vizaco.onlinecontrol.model.User;
import com.vizaco.onlinecontrol.security.PasswordHandler;
import com.vizaco.onlinecontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CustomUserDetailsServiceImpl extends JdbcDaoSupport implements UserDetailsService, PasswordHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    public CustomUserDetailsServiceImpl() {
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public UserService getUserService() {
        return userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person userByLogin = userService.findPersonByEmail(email);
        if (userByLogin == null){
            throw new UsernameNotFoundException("Email is not found in the database");
        }
        return userByLogin;

    }

    /**
     * Temp for change password in DB by all users
     * Execute if write init-method to bean
     */
    @Override
    @Transactional
    public void encodeAll() {
        getJdbcTemplate().query("select email, password from users",
                new RowCallbackHandler(){
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        String email = rs.getString(1);
                        String password = rs.getString(2);
                        UserDetails user = loadUserByUsername(email);
                        String encodedPassword = passwordEncoder.encode(password);
                        getJdbcTemplate().update("update users set password = ? where email = ?", encodedPassword, email);
                        logger.debug("Updating password for username: "+email+" to: "+encodedPassword);
                    }
                });
    }

    @Override
    @Transactional
    public void changePassword(String username, String password) {
        UserDetails user = loadUserByUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        getJdbcTemplate().update("UPDATE users SET password = ? WHERE email = ?", encodedPassword, username);
    }

}

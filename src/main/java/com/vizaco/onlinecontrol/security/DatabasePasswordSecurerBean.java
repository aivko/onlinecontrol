package com.vizaco.onlinecontrol.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by super on 5/7/15.
 */
@Component
public class DatabasePasswordSecurerBean extends JdbcDaoSupport {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SaltSource saltSource;
    @Autowired
    private UserDetailsService userDetailsService;

    public void secureDatabase() {
        getJdbcTemplate().query("select username, password from users",
                new RowCallbackHandler(){
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        String username = rs.getString(1);
                        String password = rs.getString(2);
                        UserDetails user =
                                userDetailsService.loadUserByUsername(username);
                        String encodedPassword =
                                passwordEncoder.encode(password);
                        getJdbcTemplate().update("update users set password = ? where username = ?", encodedPassword, username);
                        logger.debug("Updating password for username: "+username+" to: "+encodedPassword);
                    }
                });
    }
}
package com.app.wagon.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.wagon.dao.UserDAO;
import com.app.wagon.model.User;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    UserDAO dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = dao.findByUsername(username);

        // user.orElseThrow(() -> new UsernameNotFoundException("Not found: " +
        // username));

        return user.map(MyUserDetails::new).get();
    }
}

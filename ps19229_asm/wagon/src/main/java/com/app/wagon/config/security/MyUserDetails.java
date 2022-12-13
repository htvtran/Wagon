package com.app.wagon.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.wagon.model.Authority;
import com.app.wagon.model.Role;
import com.app.wagon.model.User;

public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;
    // private boolean active;
    private User u;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.u = user;
        this.userName = user.getUsername();
        this.password = user.getPasswords();
        // this.active = user.getActived();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>();
        for (Authority auth : this.u.getAuthorities()) {
            roles.add(auth.getRole());
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return this.u;
    }

    public String toString() {
        return "LogedIn: " + getUser();
    }

    public String getName() {
        return getUser().getFullname();
    }

    public String getId() {
        return getUser().getUsername();
    }

}

package com.app.wagon.config.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.wagon.config.security.MyUserDetails;
import com.app.wagon.model.User;

@Component
public class UserLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        User u = userDetails.getUser();

        System.out.println(u);

        super.onAuthenticationSuccess(request, response, authentication);

    }

}

package com.app.wagon.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.app.wagon.service.CategoryService;

@Component
public class AppInterceptor implements HandlerInterceptor {

    @Autowired
    CategoryService cat;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();

        session.setAttribute("catList", cat.findAll());

        return true;
    }

}

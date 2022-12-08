package com.app.wagon.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends BaseViewController<LoginController> {

    public static Authentication auth;

    @RequestMapping({ "/user/login" })
    public String getHomeView() {
        System.out.println(getDefaultTitle() + "dsa");
        return getShopTemplateViewName("login_1");
    }

    @GetMapping({ "/user/login/success" })
    public String getErorrView() {

        return getShopTemplateViewName("success");
    }

    @GetMapping({ "/logout/success" })
    public String getLogOutRedirect() {

        return "redirect:/index";
    }

    @GetMapping("/access/denied")
    public String accessDeniedView() {

        return getShopTemplateViewName("denied");
    }

    public String redirectLinkAfterLogin() {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        return "";
    }

    public static Authentication getAuth() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;

    }

    public static boolean isAuthenticated() {
        auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
            return false;
        }
        return auth.isAuthenticated();
    }

}
package com.app.wagon.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.wagon.config.security.MyUserDetails;
import com.app.wagon.model.User;

@Controller
public class LoginController extends BaseViewController<LoginController> {

    public static Authentication auth;

    @RequestMapping({ "/user/login" })
    public String getHomeView() {

        if (isAuthenticated())
            return "redirect:/index";
        return getShopTemplateViewName("login_1");
    }

    @ModelAttribute("title")
    public String getTitle() {
        return getDefaultTitle();
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
    public String accessDeniedView(Model model) {
        model.addAttribute("message", "You can't access Admin page");
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

    public static User getCurrent() {
        MyUserDetails userDetails = (MyUserDetails) getAuth().getPrincipal();
        return userDetails.getUser();
    }

}

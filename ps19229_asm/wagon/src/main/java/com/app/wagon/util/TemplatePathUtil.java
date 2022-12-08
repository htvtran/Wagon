package com.app.wagon.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TemplatePathUtil {

    private static String shop;
    private static String admin;

    @Value("${app.shop.fe.path}")
    private void getShopPath(String path) {
        shop = path;

    }

    @Value("${app.admin.fe.path}")
    private void getAdminPath(String path) {
        admin = path;

    }

    public static String getShopTemplatePath() {
        return shop;
    }

    public static String getAdminTemplatePath() {
        return admin;
    }

    public String getShopTemplateViewName(String viewName) {
        return String.format("%s/%s", shop, viewName);
    }

    public String getAdminTemplateViewName(String viewName) {
        return getTemplateViewName(admin, viewName);
    }

    public String getTemplateViewName(String path, String viewName) {
        return String.format("%s/%s", path, viewName);
    }

}

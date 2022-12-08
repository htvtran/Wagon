package com.app.wagon.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TemplatePathUtil {

    private static String shop;

    @Value("${app.shop.fe.path}")
    private void getShopPath(String path) {
        shop = path;

    }

    public static String getShopTemplatePath() {
        return shop;
    }

    public String getShopTemplateViewName(String viewName) {
        return String.format("%s/%s", shop, viewName);
    }
}

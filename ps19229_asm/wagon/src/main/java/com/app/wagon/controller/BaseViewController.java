package com.app.wagon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import com.app.wagon.util.TemplatePathUtil;

@Component

public class BaseViewController<T> {

    private final Class<T> genericType;
    @Autowired
    private TemplatePathUtil path;

    protected String getShopTemplateViewName(String name) {
        return path.getShopTemplateViewName(name);
    }

    public String getDefaultTitle() {
        return formatDefaulName(genericType.getSimpleName());
    }

    private String formatDefaulName(String name) {
        return name.substring(0, name.indexOf("Controller"));
    }

    public BaseViewController() {
        this.genericType = (Class<T>) GenericTypeResolver
                .resolveTypeArgument(getClass(), BaseViewController.class);

    }

}

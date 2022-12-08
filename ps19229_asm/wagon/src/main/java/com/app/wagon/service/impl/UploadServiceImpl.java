package com.app.wagon.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.wagon.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    ServletContext app;

    @Override
    public File save(MultipartFile file, String folder) {
        System.out.println(app.getRealPath(folder));
        File dir = new File(app.getRealPath("/images/" + folder));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));

        System.out.println(" dir - " + dir.getAbsolutePath());
        try {
            File savedFile = new File(dir, name);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

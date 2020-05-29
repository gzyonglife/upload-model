package com.jinlong.uploadmodel.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 文件上传服务器
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/19 16:07
 */
@RestController
public class UploadModelController {
    @ResponseBody
    @PostMapping("/uploadFolder")
    public String uploadFolder(@RequestParam("folder") MultipartFile[] folder) throws IOException {
        Set<String> set = new HashSet<>();

        for (MultipartFile field : folder) {
            String originalFilename = "H:/" + field.getOriginalFilename();
            String folderPath = originalFilename.substring(0, originalFilename.lastIndexOf("/"));
            File folderFile = null;
            if (set.add(folderPath)) {
                folderFile = new File(folderPath);
                if (!folderFile.exists()) {
                    folderFile.mkdirs();
                }
            }
            File file = new File(originalFilename);
            file.createNewFile();
            field.transferTo(new File(originalFilename));
        }
        return "123";
    }
}

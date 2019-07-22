package com.report.controller;

import com.report.util.FileUtil;
import com.report.util.ResponseObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author: Kris
 * @Date: 2019-07-04  09:55
 */
@RestController
@RequestMapping("/api/util")
public class UploadController {

    @PostMapping("/upload")
    public ResponseObject upload(HttpServletRequest request) throws Exception {
        MultipartFile file = ((MultipartHttpServletRequest) request)
                .getFile("file");
        String name = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + name.substring(name.lastIndexOf("."));
        String url = null;
        String s = FileUtil.uploadFile(file.getBytes(), "/Users/xiaoqiao/", UUID.randomUUID().toString());
        return ResponseObject.success(s);

    }


}

package com.report.util;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

public class FileUtil {
    public static String uploadFile(byte[] fileData, String fileDir, String fileName) throws Exception {
        File targetFile = new File(fileDir);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        String path = fileDir + fileName;
        FileOutputStream out = new FileOutputStream(path);
        try {
            if(fileData != null ){
                out.write(fileData);
            }
            else{
                out.write(0x00);
            }
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }

        }

        return path;
    }


    public static byte[] readFile(MultipartFile file) {

        InputStream in = null;
        try {
            in = file.getInputStream();
            byte[] data = new byte[in.available()];
            in.read(data);
            in.close();
            return  data;
        } catch (IOException e) {
            e.printStackTrace();
        }
      return  null;
    }

    /**
     * 将内容写入json文件
     * @param object
     * @param key
     * @param fileDir
     * @param fileName
     */
    public static void writeJsonToFile(Object object , String key,String fileDir, String fileName) {
        try {
            FileUtil.uploadFile(null, fileDir, fileName);//清空文件
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(null == object)
        {
            return;
        }

        String str = JSON.toJSON(object).toString();
        byte[] data = str.getBytes();


        try {

            FileUtil.uploadFile(data, fileDir, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void downloadFile(String path, HttpServletResponse res) throws IOException {
        String fileName = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(path));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } finally {
            if (bis != null) {
                bis.close();
            }
        }

    }

    //把base64加密的数据写入文件中
    public static void saveBase64ToFile(String base65Str,FileOutputStream outputStream ){
        try {
            byte[] buffer = new BASE64Decoder().decodeBuffer(base65Str);
            outputStream.write(buffer);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}

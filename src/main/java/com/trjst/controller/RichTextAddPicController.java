package com.trjst.controller;

import com.trjst.util.OrderNoUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 富文本
 */
@RestController
public class RichTextAddPicController {

    @Value("${adm.url}")
    private String webUrl;


    @PostMapping(value = "/uploadRichTextPicture")
    @ApiOperation(value = "上传富文本中的图片")
    public Map<String,Object> uploadHandle(HttpServletRequest request, HttpServletResponse response){


        Map<String,Object> map = new HashMap<String,Object>(0);
        map.clear();
        MultipartFile multipartFile = null;
        // 转型为MultipartHttpRequest：
        if(request instanceof MultipartHttpServletRequest){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获取对应file对象
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            Iterator<String> fileIterator = multipartRequest.getFileNames();
            while (fileIterator.hasNext()) {
                String fileKey = fileIterator.next();
                // 获取对应文件
                multipartFile = fileMap.get(fileKey);
                if (multipartFile.getSize() != 0L) {
                    // 目录
                    String realPath = request.getSession().getServletContext().getRealPath("/") + "..//upload/articlePic";
                    File dir = new File(realPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    // 获取上传文件原始名称
                    String fileName = multipartFile.getOriginalFilename();
                    // 获取文件的扩展名
                    String ext = fileName.substring(fileName.lastIndexOf("."));
                    String tmpExt = ext.toLowerCase(); // 验证图片类型
                    if (!tmpExt.contains(".jpg") && !tmpExt.contains(".gif") && !tmpExt.contains(".png") && !tmpExt.contains(".jpeg")) {
                        map.put("msg", "只能上传jpg、gif、png类型的图片。");
                        map.put("data", "");
                        return map;
                    }
                    // 重新给文件命名
                    String banName = OrderNoUtil.getOrderNum();
                    String newName = banName + ext;
                    // 封装为文件对象
                    File targetFile = new File(realPath, newName);
                    try {
                        // 上传文件
                        multipartFile.transferTo(targetFile);
                        // 返回给前端页面的图片

                        String path = "http://"+webUrl+"/upload/articlePic/"+newName;

                        map.put("data", path);
                        map.put("msg", "上传成功");
                    }catch(Exception e){
                        map.put("data", "");
                        map.put("msg", "上传失败");
                    }
                }
            }
        }
        return map;
    }

}

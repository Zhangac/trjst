package com.trjst.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.trjst.util.fileupload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(description = "文件上传、获取、下载")
public class UploadfileController {

    private Log logger = LogFactory.getLog(UploadfileController.class);

    @Value("${upload}")
    private String upload;

    @Value("${qrcode}")
    private String qrcode;

    //下载
    @RequestMapping(value = "/download", method = {RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "下载(pathname传视频链接比如：http://pu5fsxsdu.bkt.clouddn.com/bcc20c8923bfda630979aa1f2b66133f.mp4)")
    public String download(HttpServletRequest request, HttpServletResponse response, String pathname)throws Exception {
        String str = pathname.substring(pathname.indexOf("m/")+2).trim();
        String url = String.format(pathname+"?attname=%s",URLEncoder.encode(str, "utf-8"));
        logger.info(url);
        return "redirect:"+url;
    }

    //读取图片
    @RequestMapping(value = "/getimage", method = {RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "获取图片(pathname传图片全名就好了就是数据库返回给你的)")
    public void getImage(HttpServletRequest request, HttpServletResponse response, String pathname) {
        FileInputStream fis = null;
        response.setContentType("image/*");
        try {
            OutputStream out = response.getOutputStream(); //通过response显示图片
            File file = new File(upload + pathname);  //服务器上图片的路径
            System.out.println(upload + pathname);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取二维码图片
    @RequestMapping(value = "/qrcode", method = {RequestMethod.GET}, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "获取二维码图片(pathname传图片全名就好了就是数据库返回给你的)")
    public void qrcode(HttpServletRequest request, HttpServletResponse response, String pathname) {
        FileInputStream fis = null;
        response.setContentType("image/*");
        try {
            OutputStream out = response.getOutputStream(); //通过response显示图片
            File file = new File(qrcode + pathname);  //服务器上图片的路径
            System.out.println(qrcode + pathname);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //上传文件
    @RequestMapping(value = "/uploadfile", method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "上传文件")
    public JSONObject uploadpic(HttpServletRequest request, HttpServletResponse resp, @RequestParam("file") MultipartFile filePath) {
        HttpSession session = request.getSession();
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
        JSONObject jobj = new JSONObject();

        File folder = new File(request.getSession().getServletContext().getRealPath("/") + "..//upload");
//		File folder=new File(upload);
        if (!folder.exists()) {//如果文件夹不存在
            folder.mkdir();//创建文件夹
        }

        try {
            fileupload upload = new fileupload();
            String loadpath = folder + File.separator;
            File filePath2 = upload.saveAttachFile(filePath, loadpath, true);
            filePath.getOriginalFilename();
            System.out.println(filePath2.getName());
            jobj.put("recode", 100);
            jobj.put("filepath", filePath2.getName());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            jobj.put("recode", "400");
            jobj.put("reobj", "系统错误");
            jobj.put("restr", "系统错误");
        }
        return jobj;

    }

    //上传多个文件
    @RequestMapping(value = "/uploadfilelist", method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "上传多个文件")
    public JSONObject uploadpiclist(HttpServletRequest request, HttpServletResponse resp, @RequestParam("files") MultipartFile[] filePath) {
        HttpSession session = request.getSession();
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");
        JSONObject jobj = new JSONObject();

        File folder = new File(request.getSession().getServletContext().getRealPath("/") + "..//upload");
//		File folder=new File(upload);
        if (!folder.exists()) {//如果文件夹不存在
            folder.mkdir();//创建文件夹
        }

        try {
            fileupload upload = new fileupload();
            String loadpath = folder + File.separator;
            List<File> filePath2 = new ArrayList<>();
            for (int i = 0; i < filePath.length; i++){
                filePath2.add(upload.saveAttachFile(filePath[i], loadpath, true));
                filePath[i].getOriginalFilename();
            }
            jobj.put("recode", 100);
            for (int i=0;i<filePath2.size();i++){
                jobj.put("filepath"+i, filePath2.get(i).getName());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            jobj.put("recode", "400");
            jobj.put("reobj", "系统错误");
            jobj.put("restr", "系统错误");
        }
        return jobj;

    }

}

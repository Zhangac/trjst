package com.trjst.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class fileupload {

    public File saveAttachFile(MultipartFile file,String loadpath,boolean IsNumname) {

        File file2 = null;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                File filedir = new File(loadpath);
                if (!filedir.exists()) {
                    filedir.mkdirs();
                }
                String filename = file.getOriginalFilename();
                if(IsNumname) {
//					int point = filename.indexOf(".");
                    //得到最后一个.的位置
                    int point = filename.lastIndexOf(".");
                    //生成文件名编号
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    int sjcode = (int) (Math.random()*9000+1000);
                    String ordercode = sdf.format(new Date())+sjcode;
                    filename = ordercode + filename.substring(point, filename.length());

//					filename = (new Date()).getTime() + filename.substring(point, filename.length());
                }

                loadpath = loadpath + filename;
                // 转存文件
                file2 = new File(loadpath);
                file.transferTo(file2);

            } catch (Exception e) {
                e.printStackTrace();
                return file2;
            }
        }
        return file2;
    }

}

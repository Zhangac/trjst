package com.trjst.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.trjst.controller.admin.model.AdminReturnModel;
import com.trjst.model.Admin;
import com.trjst.service.admin.AdminService;
import com.trjst.util.EmptyUtil;
import com.trjst.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Alex Chin
 * */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    //管理员信息列表
    @RequestMapping("/adminlist")
    public String adminlist(HttpServletRequest request, HttpServletResponse resp)
            throws IOException {
        return "admnuser/adminlist";
    }
    //管理员信息列表
    @RequestMapping(value = "/adminlistajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String adminlistajax(HttpServletRequest request, Integer draw
    ) {
//        Enumeration enu=request.getParameterNames();
//        while(enu.hasMoreElements()){
//            String paraName=(String)enu.nextElement();
//            System.out.println(paraName+": "+request.getParameter(paraName));
//        }

        Integer start = Integer.valueOf(request.getParameter("start"));
        String length = request.getParameter("length");
        Integer length_number = Integer.valueOf(length);

        HttpSession session = request.getSession();

        // 每页显示条数
        List adminList = adminService.getAdminList(start,length_number); //本页管理员对象的list
        Integer countnumber = adminService.getAdminListCount(); //管理员总个数

        List<AdminReturnModel> adminReturnModels = new ArrayList<>();

        for(int i=0;i<adminList.size();i++){
            Admin admin = (Admin) adminList.get(i);
            AdminReturnModel adminReturnModel =new AdminReturnModel();

            adminReturnModel.setId(admin.getId());
            adminReturnModel.setAccount(admin.getAccount());
            adminReturnModel.setLevel(admin.getLevel());
            adminReturnModel.setStatus(admin.getStatus());
            adminReturnModel.setArea(admin.getArea());
            adminReturnModel.setCreate_time(admin.getCreate_time());
            if(admin.getLevel()==1){
                adminReturnModel.setLevelname("超级管理员");
            }else if(admin.getLevel()==2){
                adminReturnModel.setLevelname("管理员");
            }else if(admin.getLevel()==3){
                adminReturnModel.setLevelname("区市管理员");
            }

            if(admin.getArea()==1){
                adminReturnModel.setArea_name("市中区");
            }else if(admin.getArea()==2){
                adminReturnModel.setArea_name("薛城区");
            }else if(admin.getArea()==3){
                adminReturnModel.setArea_name("峄城区");
            }else if(admin.getArea()==4){
                adminReturnModel.setArea_name("台儿庄区");
            }else if(admin.getArea()==5){
                adminReturnModel.setArea_name("山亭区");
            }else if(admin.getArea()==6){
                adminReturnModel.setArea_name("滕州市");
            }else {
                adminReturnModel.setArea_name("");
            }
            adminReturnModels.add(adminReturnModel);
        }
        JSONObject jobj = new JSONObject();
        jobj.put("draw", draw);
        jobj.put("recordsFiltered", countnumber);
        jobj.put("recordsTotal", countnumber);
        jobj.put("data", adminReturnModels);

        System.out.println(jobj.toString());
        return jobj.toString();
    }


    //密码修改
    @RequestMapping("/adminpassword")
    public String landlordpassword(HttpServletRequest request, HttpServletResponse resp,
                                   Integer admin_id) {

        System.out.println("跳转页面请求传值:"+admin_id);

        request.setAttribute("admin_id", admin_id);
        return "admnuser/adminpassword";
    }
    //密码修改
    @RequestMapping(value = "/adminpasswordajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map adminpasswordajax(HttpServletRequest request,String oldpassword,String newpassword,String newpassword2,Integer admin_id)  {
        Map map = new HashMap<String, String>();

        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }
        System.out.println("newpassword:"+newpassword+",newpassword:"+newpassword+",admin_id:"+admin_id);

        try{
            Admin admin = adminService.findAdminById(admin_id);
            String oldpasswordmd5 = MD5.MD5Encode("y7<LF5H2qgfIx]AD{6Yg"+MD5.MD5Encode(oldpassword, "UTF-8"), "UTF-8");
            String newpasswordmd5 = MD5.MD5Encode("y7<LF5H2qgfIx]AD{6Yg"+MD5.MD5Encode(newpassword, "UTF-8"), "UTF-8");
            if(admin.getPassword().equals(oldpasswordmd5)){
                admin.setPassword(newpasswordmd5);
                adminService.updateAdmin(admin);
                map.put("code", "100");
                map.put("info", "修改成功");
            }else{
                map.put("code", "400");
                map.put("info", "原密码错误");
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", "400");
            map.put("info", "修改失败");
        }
        return map;
    }
    //添加管理员
    @RequestMapping("/adminadd")
    public String adminadd(HttpServletRequest request, HttpServletResponse resp,Integer admin_id) {
        request.setAttribute("admin_id", admin_id);
        return "admnuser/adminadd";
    }
    //添加管理员
    @RequestMapping(value = "/adminaddajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map adminaddajax(HttpServletRequest request,String account,
                            String newpassword,String newpassword2,Integer level,Integer area) {
        Map map = new HashMap<String, String>();
        try{
            Admin adminGet = adminService.findAdminByAccount(account);
            if(EmptyUtil.isNotEmpty(adminGet)){
                map.put("code", "300");
                map.put("info", "账号已存在");
            }else{
                String newpasswordmd5 = MD5.MD5Encode("y7<LF5H2qgfIx]AD{6Yg"+MD5.MD5Encode(newpassword, "UTF-8"), "UTF-8");
                Admin admin = new Admin();
                admin.setAccount(account);
                admin.setPassword(newpasswordmd5);
                admin.setLevel(level);
                admin.setStatus(1);
                admin.setCreate_time(new Date());
                admin.setArea(area);
                adminService.insertAdmin(admin);
                map.put("code", "100");
                map.put("info", "添加成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", "400");
            map.put("info", "添加失败");
        }
        return map;
    }
    //编辑管理员权限
    @RequestMapping("/adminedit")
    public String adminedit(HttpServletRequest request, HttpServletResponse resp,
                            Integer admin_id)
            throws IOException {
        request.setAttribute("admin_id", admin_id);
        Admin admin = adminService.findAdminById(admin_id);
        request.setAttribute("level", admin.getLevel());
        request.setAttribute("account", admin.getAccount());
        request.setAttribute("area", admin.getArea());
        return "admnuser/adminedit";
    }
    //编辑管理员权限
    @RequestMapping(value = "/admineditajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map admineditajax(HttpServletRequest request,Integer admin_id,
                             Integer level,String account,Integer area) throws IOException {
        Map map = new HashMap<String, String>();
        try{
            Admin admin = adminService.findAdminById(admin_id);
            admin.setLevel(level);
            admin.setAccount(account);
            admin.setArea(area);
            adminService.updateAdmin(admin);
            map.put("code", "100");
            map.put("info", "修改成功");

        }catch(Exception e){
            e.printStackTrace();
            map.put("code", "400");
            map.put("info", "修改失败");
        }
        return map;
    }
    //删除
    @RequestMapping(value = "/admindeleteajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map admindeleteajax(HttpServletRequest request,Integer id) throws IOException {
        Map map = new HashMap<String, String>();
        try{
            if(id==1){
                map.put("code", "200");
            }else{
                Admin admin = adminService.findAdminById(id);
                admin.setStatus(2);
                adminService.updateAdmin(admin);
                map.put("code", "100");
            }
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
    //重置密码（111111）
    @RequestMapping(value = "/adminresetpasswordajax", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map adminresetpasswordajax(HttpServletRequest request,Integer id) throws IOException {
        Map map = new HashMap<String, String>();
        try{
            Admin admin  = adminService.findAdminById(id);
            admin.setPassword("699950c26dd523e44080dda7d4001827");
            adminService.updateAdmin(admin);
            map.put("code", "100");
        }catch(Exception e){
            e.printStackTrace();
            map.put("code", "400");
        }
        return map;
    }
}

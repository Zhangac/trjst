package com.trjst.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderNoUtil {
    /**
     * Created by cc
     * 生成订单号
     * uuid: db30cc85-3387-4845-87bc-dc390b55b7ea 前8位 + 201508171108900(yyMMddHHMMSS)
     * 即： db30cc85201508171108900
     */

    public static  String getOrderNo(){
        String orderNo = "" ;
        UUID uuid = UUID.randomUUID();
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = uuid.toString().substring(0, 8);
        orderNo = orderNo + sdf ;
        return orderNo ;
    }

    //生成19随机单号 纯数字
    public static String getOrderNum(){
        String orderNo = "" ;
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
//          String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        StringBuffer sdf = new StringBuffer(new SimpleDateFormat("yyMMddHHMMSS").format(new Date()));
        orderNo = trandNo.toString().substring(0, 6);
        orderNo = /*orderNo +*/ sdf.reverse()+orderNo ;//倒序
        return orderNo ; }

    public static String getCoverNum(){
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 100000);
        String trandNo2 = String.valueOf((Math.random() * 9 + 1) * 1000000);
        return trandNo.toString().substring(0, 6)+"******"+trandNo2.toString().substring(0, 7);
    }


}


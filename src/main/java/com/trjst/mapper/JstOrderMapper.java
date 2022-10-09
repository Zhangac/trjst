package com.trjst.mapper;

import com.trjst.dto.DeliveryMerGroupDto;
import com.trjst.model.JstOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JstOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(JstOrder record);

    JstOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JstOrder record);

    List<JstOrder> orderList(JstOrder record);

    List<JstOrder> orderByMi(JstOrder record);

    List<JstOrder> orderAll();//查询待支付订单
    List<JstOrder> orderAll2();//查询待差价支付订单

    List getResultList(@Param("start") Integer start, @Param("length_number") Integer length_number,
                       @Param("order_no")String order_no, @Param("pay_status")Integer pay_status,
                       @Param("delivery_name")String delivery_name,
                       @Param("logmax")String logmax,@Param("logmin")String logmin,
                       @Param("phone")String phone);

    Integer getListCount(@Param("order_no")String order_no, @Param("pay_status")Integer pay_status,
                         @Param("delivery_name")String delivery_name,
                         @Param("logmax")String logmax,@Param("logmin")String logmin,
                         @Param("phone")String phone);

    // 商户本周
    @Select("SELECT * FROM jst_order WHERE DATEDIFF( date_format( now( ) , '%Y-%m-%d' ) , date_format( create_time, '%Y-%m-%d' ) ) = #{day} and merchant_id = #{merchant_id} and confirm_receipt=3")
    List<JstOrder> zhou(int day, Integer merchant_id);

    // 商户本月
    @Select("select * from jst_order  where date_format(create_time,'%Y-%m')=date_format(now(),'%Y-%m') and merchant_id = #{merchant_id} and confirm_receipt=3")
    List<JstOrder> yue(Integer merchant_id);

    // 商户本年
    @Select("select * from `jst_order` where YEAR(create_time)=YEAR(NOW()) and merchant_id = #{merchant_id} and confirm_receipt=3")
    List<JstOrder> nian(Integer merchant_id);

    @Select("SELECT o.id,o.pay_status,o.confirm_receipt,o.type,o.delivery_id,o.merchant_id,m.merchant_name,m.address,m.NAME,m.des,m.operate_head FROM jst_order o LEFT JOIN merchant_info m ON o.merchant_id = m.id WHERE o.delivery_id = #{delivery_id} and o.type = #{type} GROUP BY o.merchant_id ORDER BY o.create_time DESC")
    List<DeliveryMerGroupDto> deliveryMerGroup(Integer delivery_id,Integer type);

    List getOrderDown(@Param("order_no")String order_no, @Param("pay_status")Integer pay_status,
                       @Param("delivery_name")String delivery_name,
                      @Param("logmax")String logmax,@Param("logmin")String logmin,
                      @Param("phone")String phone);
}
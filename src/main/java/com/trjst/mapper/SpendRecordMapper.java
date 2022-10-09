package com.trjst.mapper;

import com.trjst.model.SpendRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface SpendRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SpendRecord record);

    SpendRecord selectByPrimaryKey(Integer id);

    SpendRecord selectByOrderId(String order_id);

    List<SpendRecord> selectByUserId(Integer userid);

    int updateByPrimaryKeySelective(SpendRecord record);

    // 本周
    @Select("SELECT * FROM spend_record WHERE DATEDIFF( date_format( now( ) , '%Y-%m-%d' ) , date_format( create_time, '%Y-%m-%d' ) ) = #{day} and user_id = #{userId}")
    List<SpendRecord> getStrangeNumBeforeSomeDays(int day,Integer userId);

    // 本月
    @Select("select * from spend_record  where date_format(create_time,'%Y-%m')=date_format(now(),'%Y-%m') and user_id = #{userId}")
    List<SpendRecord> yue(Integer userId);

    // 本年
    @Select("select * from `spend_record` where YEAR(create_time)=YEAR(NOW()) and user_id = #{userId}")
    List<SpendRecord> nian(Integer userId);

}
package com.trjst.service.api;

import com.trjst.mapper.SpendRecordMapper;
import com.trjst.model.SpendRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SpendRecordService {

    @Autowired
    private SpendRecordMapper spendRecordMapper;

    public List<SpendRecord> selectByUserId(Integer user_id){
        return spendRecordMapper.selectByUserId(user_id);
    }

    public int addCheckList(SpendRecord record){
        return spendRecordMapper.insertSelective(record);
    }

    public int updateCheckList(SpendRecord record){
        return spendRecordMapper.updateByPrimaryKeySelective(record);
    }

    public int delCheckList(Integer id){
        return spendRecordMapper.deleteByPrimaryKey(id);
    }



    //周月年统计
    public Map getStrangeNumCurWeek(Integer userId) {
        Map result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        for(int i=1;i<=7;i++){
            data.put(String.valueOf(i),0);
        }
        int weekday = getWeekday();
        List<SpendRecord> sr = new ArrayList<>();
        for(int i=1;i<=weekday;i++){
            List<SpendRecord> s = spendRecordMapper.getStrangeNumBeforeSomeDays(weekday-i,userId);
            data.put(String.valueOf(i),s);
            sr.addAll(s);
        }
        BigDecimal shouRu = new BigDecimal(0);
        BigDecimal zhiChu = new BigDecimal(0);
        for (SpendRecord as :sr){
            if(as.getType()==1){
                zhiChu=zhiChu.add(as.getSpend_amount());
            }else if(as.getType()==2){
                shouRu=shouRu.add(as.getSpend_amount());
            }
        }
        List<SpendRecord> yue = spendRecordMapper.yue(userId);
        BigDecimal yueShouRu = new BigDecimal(0);
        BigDecimal yueZhiChu = new BigDecimal(0);
        if(yue.size()!=0){
            for (SpendRecord as :yue){
                if(as.getType()==1){
                    yueZhiChu=yueZhiChu.add(as.getSpend_amount());
                }else if(as.getType()==2){
                    yueShouRu=yueShouRu.add(as.getSpend_amount());
                }
            }
        }

        List<SpendRecord> nian = spendRecordMapper.nian(userId);
        BigDecimal nianShouRu = new BigDecimal(0);
        BigDecimal nianZhiChu = new BigDecimal(0);
        if(nian.size()!=0){
            for (SpendRecord as :nian){
                if(as.getType()==1){
                    nianZhiChu=nianZhiChu.add(as.getSpend_amount());
                }else if(as.getType()==2){
                    nianShouRu=nianShouRu.add(as.getSpend_amount());
                }
            }
        }

        result.put("code",200);
        result.put("shouRu",shouRu);
        result.put("zhiChu",zhiChu);
        result.put("yueShouRu",yueShouRu);
        result.put("yueZhiChu",yueZhiChu);
        result.put("nianShouRu",nianShouRu);
        result.put("nianZhiChu",nianZhiChu);
        return result;
    }



    public int getWeekday(){
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
        if(weekday==1)
            return 7;
        else return weekday-1;
    }
}

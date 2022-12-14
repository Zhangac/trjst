package com.trjst.service.api;

import com.trjst.mapper.CommodityInfoMapper;
import com.trjst.mapper.ImgesMapper;
import com.trjst.mapper.OperationRecordMapper;
import com.trjst.mapper.SpeciMapper;
import com.trjst.model.CommodityInfo;
import com.trjst.model.Imges;
import com.trjst.model.OperationRecord;
import com.trjst.model.Speci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommodityInfoService {

    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    @Autowired
    private OperationRecordMapper operationRecordMapper;

    @Autowired
    private ImgesMapper imgesMapper;

    @Autowired
    private SpeciMapper speciMapper;

    public List<CommodityInfo> commodityInfoList(CommodityInfo record){
        List<CommodityInfo> list = commodityInfoMapper.commodityList(record);
        for(CommodityInfo ci :list){
            List<Imges> imgesList = imgesMapper.selectByFkList(new Imges(ci.getId(), 1));
            ci.setImgesList(imgesList);
            List<Imges> imgesList2 = imgesMapper.selectByFkList(new Imges(ci.getId(), 2));
            ci.setImgesDetail(imgesList2);
            List<Speci> speciList = speciMapper.selectByCommId(ci.getId());
            ci.setSpeciDetail(speciList);
        }
        return list;
    }

    public List<CommodityInfo> commodityInfoList2(CommodityInfo record){
        List<CommodityInfo> list = commodityInfoMapper.commodityList2(record);
        for(CommodityInfo ci :list){
            List<Imges> imgesList = imgesMapper.selectByFkList(new Imges(ci.getId(), 1));
            ci.setImgesList(imgesList);
            List<Imges> imgesList2 = imgesMapper.selectByFkList(new Imges(ci.getId(), 2));
            ci.setImgesDetail(imgesList2);
            List<Speci> speciList = speciMapper.selectByCommId(ci.getId());
            ci.setSpeciDetail(speciList);
        }
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public int addCommodityInfo(CommodityInfo record){

        int num = commodityInfoMapper.insertSelective(record);

        if(num > 0) {

            if (record.getImgesList().size() > 0 && record.getImgesList()!=null) {
                for (Imges i : record.getImgesList()) {
                    Imges imges = new Imges();
                    imges.setAddress(i.getAddress());
                    imges.setFk_id(record.getId());
                    imges.setType(1);
                    imgesMapper.insertSelective(imges);
                }
            }

            if (record.getImgesDetail().size() > 0 && record.getImgesList()!=null) {
                for (Imges i : record.getImgesDetail()) {
                    Imges imges = new Imges();
                    imges.setAddress(i.getAddress());
                    imges.setFk_id(record.getId());
                    imges.setType(2);
                    imgesMapper.insertSelective(imges);
                }
            }

            if (record.getSpeciDetail().size() > 0 && record.getSpeciDetail()!=null) {
                for (Speci i : record.getSpeciDetail()) {
                    Speci speci = new Speci();
                    speci.setCommodity_info_id(record.getId());
                    speci.setSpeci_name(i.getSpeci_name());
                    speci.setSpeci_price(i.getSpeci_price());
                    speci.setSpeci_regu(i.getSpeci_regu());
                    speci.setVip_price(i.getVip_price());
                    speciMapper.insertSelective(speci);
                }
            }
        }
        OperationRecord or = new OperationRecord();
        or.setDes("????????????"+record.getMerchant_id()+"?????????????????????????????????????????????"+record.toString());
        operationRecordMapper.insertSelective(or);

        return num;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateCommodityInfo(CommodityInfo record){

        int num = commodityInfoMapper.updateByPrimaryKeySelective(record);

        if(num > 0) {
            if ( null != record.getImgesList() &&  record.getImgesList().size() > 0) {
                List<Imges> imgesList = imgesMapper.selectByFkList(new Imges(record.getId(), 1));
                if (imgesList.size() > 0) {
                    for (Imges i : imgesList) {
                        imgesMapper.deleteByPrimaryKey(i.getId());
                    }
                }
                for (Imges i : record.getImgesList()) {
                    Imges imges = new Imges();
                    imges.setAddress(i.getAddress());
                    imges.setFk_id(record.getId());
                    imges.setType(1);
                    imgesMapper.insertSelective(imges);
                }
            }

            if (null != record.getImgesDetail() &&  record.getImgesDetail().size() > 0) {
                List<Imges> imgesList = imgesMapper.selectByFkList(new Imges(record.getId(), 2));
                if (imgesList.size() > 0) {
                    for (Imges i : imgesList) {
                        imgesMapper.deleteByPrimaryKey(i.getId());
                    }
                }
                for (Imges i : record.getImgesDetail()) {
                    Imges imges = new Imges();
                    imges.setAddress(i.getAddress());
                    imges.setFk_id(record.getId());
                    imges.setType(2);
                    imgesMapper.insertSelective(imges);
                }
            }

            if ( null != record.getSpeciDetail() &&  record.getSpeciDetail().size() > 0) {
                List<Speci> speciList = speciMapper.selectByCommId(record.getId());
                if (speciList.size() > 0) {
                    for (Speci i : speciList) {
                        speciMapper.deleteByPrimaryKey(i.getSpeci_id());
                    }
                }
                for (Speci i : record.getSpeciDetail()) {
                    Speci speci = new Speci();
                    speci.setCommodity_info_id(record.getId());
                    speci.setSpeci_name(i.getSpeci_name());
                    speci.setSpeci_price(i.getSpeci_price());
                    speci.setSpeci_regu(i.getSpeci_regu());
                    speci.setVip_price(i.getVip_price());
                    speciMapper.insertSelective(speci);
                }
            }
        }
        OperationRecord or = new OperationRecord();
        or.setDes("??????id???"+record.getId()+"????????????????????????????????????"+record.toString());
        operationRecordMapper.insertSelective(or);
        return num;
    }

    @Transactional(rollbackFor = Exception.class)
    public int delCommodityInfo(Integer id){
        int num = commodityInfoMapper.deleteByPrimaryKey(id);
        if (num > 0){
            List<Imges> imgesList = imgesMapper.selectByFkList(new Imges(id));
            if (imgesList.size() > 0 ){
                for (Imges i : imgesList){
                    imgesMapper.deleteByPrimaryKey(i.getId());
                }
            }

            List<Speci> speciList = speciMapper.selectByCommId(id);
            if (speciList.size() > 0) {
                for (Speci i : speciList) {
                    speciMapper.deleteByPrimaryKey(i.getSpeci_id());
                }
            }
        }
        OperationRecord or = new OperationRecord();
        or.setDes("??????id???"+id+"??????????????????");
        return num;
    }

    public Map onStatus(CommodityInfo req){
        Map map = new HashMap();
        if(req.getStatus()==1) {
            CommodityInfo cif = commodityInfoMapper.selectByPrimaryKey2(req.getId());
            if (cif.getAssort_id() == 0) {
                map.put("code", 201);
                map.put("msg", "???????????????????????????????????????????????????");
            } else {
                CommodityInfo record = new CommodityInfo();
                record.setId(req.getId());
                record.setStatus(req.getStatus());
                int num = commodityInfoMapper.updateByPrimaryKeySelective(record);
                if (num > 0) {
                    map.put("code", 200);
                    map.put("msg", "success");
                }
            }
        }else {
            CommodityInfo record = new CommodityInfo();
            record.setId(req.getId());
            record.setStatus(req.getStatus());
            int num = commodityInfoMapper.updateByPrimaryKeySelective(record);
            if (num > 0) {
                map.put("code", 200);
                map.put("msg", "success");
            }
        }
        return map;
    }
}

package com.example.lyy.util.id;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: cjw
 * @Description: id生成工具类
 * @Date: 18:02 2018/8/6
 */
@Component
public class IDMaker {

//    @Autowired
//    private TSeqDefCustomMapper tSeqDefMapper;
//
//    @Autowired
//    private TUnderwritingAttrDefMapper tUnderwritingAttrDefMapper;
//
//    private static IDMaker idMaker;
//
//    @PostConstruct
//    public void init(){
//        idMaker = this;
//        idMaker.tSeqDefMapper = this.tSeqDefMapper;
//        idMaker.tUnderwritingAttrDefMapper = this.tUnderwritingAttrDefMapper;
//    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static Random random = new Random();

    public static String getOrderCode(){
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++){
                fourRandom = "0" + fourRandom;
            }
        }
        return "DD" + simpleDateFormat.format(new Date()) + fourRandom;
    }

    public static String getApplicationCode(){
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++){
                fourRandom = "0" + fourRandom;
            }
        }
        return "TB" + simpleDateFormat.format(new Date()) + fourRandom;
    }

    public static String getTempSerialNo(){
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++){
                fourRandom = "0" + fourRandom;
            }
        }
        return simpleDateFormat.format(new Date()) + fourRandom;
    }

//    public static synchronized String generateOrderId(ContextMessage contextMessage){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String day = simpleDateFormat.format(new Date());
//        TUnderwritingAttrDef tUnderwritingAttrDef = new TUnderwritingAttrDef();
//        ContextApplicationFormInformation tempInfo = contextMessage.getBody().getApplicationForms().get(0).getInformation();
//        tUnderwritingAttrDef.setProductCode(tempInfo.getProductCode());
//        tUnderwritingAttrDef.setChannelCode(tempInfo.getChannalCode());
//        tUnderwritingAttrDef.setAttribute("mappingType");
//        String mappingType = idMaker.tUnderwritingAttrDefMapper.findAttributeValue(tUnderwritingAttrDef);
//        TSeqDef tSeqDef = new TSeqDef();
//        tSeqDef.setProductCode(tempInfo.getProductCode());
//        tSeqDef.setChannelCode(tempInfo.getChannalCode());
//        tSeqDef.setDay(day);
//        tSeqDef.setType("01");
//        String seqNumber = getSeqNumber(tSeqDef);
//        String code = "DD" + mappingType + day + seqNumber;
//        return code;
//    }
//
//    public static synchronized String generateAppId(ContextApplicationFormInformation tempInfo){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String day = simpleDateFormat.format(new Date());
//        TUnderwritingAttrDef tUnderwritingAttrDef = new TUnderwritingAttrDef();
//        tUnderwritingAttrDef.setProductCode(tempInfo.getProductCode());
//        tUnderwritingAttrDef.setChannelCode(tempInfo.getChannalCode());
//        tUnderwritingAttrDef.setAttribute("mappingType");
//        String mappingType = idMaker.tUnderwritingAttrDefMapper.findAttributeValue(tUnderwritingAttrDef);
//        TSeqDef tSeqDef = new TSeqDef();
//        tSeqDef.setProductCode(tempInfo.getProductCode());
//        tSeqDef.setChannelCode(tempInfo.getChannalCode());
//        tSeqDef.setDay(day);
//        tSeqDef.setType("02");
//        String seqNumber = getSeqNumber(tSeqDef);
//        String code = "TB" + mappingType + day + seqNumber;
//        return code;
//    }
//
//
//    private static String getSeqNumber(TSeqDef tSeqDef){
//        TSeqDef newTSeqDef = idMaker.tSeqDefMapper.selectSeqNumber(tSeqDef);
//        if(newTSeqDef==null){
//            idMaker.tSeqDefMapper.tableLock();
//            tSeqDef.setId(IDUtil.getId());
//            tSeqDef.setCurSeq(2);
//            idMaker.tSeqDefMapper.insertSelective(tSeqDef);
//            return "000001";
//        }else {
//            Integer curSeq = newTSeqDef.getCurSeq();
//            String substring = ("000000" + curSeq.toString()).substring(curSeq.toString().length());
//            curSeq = curSeq + 1;
//            newTSeqDef.setCurSeq(curSeq);
//            idMaker.tSeqDefMapper.updateByPrimaryKey(newTSeqDef);
//            return substring;
//        }
//    }

}

package com.example.lyy.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lyy.domain.ResponseData;
import com.example.lyy.entity.*;
import com.example.lyy.mapper.*;
import com.example.lyy.merge.sql.SqlUtil;
import com.example.lyy.merge.templateSql.GenerateSqlUtil;
import com.example.lyy.merge.templateSql.MySqlUtil;
import com.example.lyy.service.TestService;
import com.example.lyy.thread.MyRejectedPolicy;
import com.example.lyy.thread.RunTask;
import com.example.lyy.util.*;
import com.example.lyy.util.auxiliary.DateUtil;
import com.example.lyy.util.auxiliary.FileUtil;
import com.example.lyy.util.auxiliary.MoneyToCNFormat;
import com.example.lyy.util.auxiliary.ResponseUtil;
import com.example.lyy.util.id.SnowflakeIdWorker;
import com.example.lyy.util.invoke.ReflectionFieldUtil;
import com.example.lyy.util.pdf.DFormat;
import com.example.lyy.util.pdf.Html2PdfUtil;
import com.example.lyy.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Resource
    RunTask runTask;

    @Autowired
    SqlSession sqlSession;

    @Autowired
    TFullpathElementDefMapper tFullpathElementDefMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    TProductMdMapper mdMapper;

    @Autowired
    TBizEntryRelaMapper tBizEntryRelaMapper;

    @Autowired
    TPremFactorRelaMapper tPremFactorRelaMapper;

    @Autowired
    TLogMapper tLogMapper;

    @Autowired
    TaskImpl task;

    @Autowired
    TOrderMapper tOrderMapper;

    @Autowired
    TUserMapper tUserMapper;

    @Autowired
    TUnderWritingMapper tUnderWritingMapper;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private TemplateMapper templateMapper;



    @Override
    public void checkProduct(String data, CountDownLatch begin,CountDownLatch excute) throws Exception {
        return;
    }




    final CountDownLatch latch = new CountDownLatch(2);

    @Override
    public List<AreaCode> packAreaJson(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = new JSONArray();
        JSONArray array = (JSONArray) jsonObject.get("children");
        Json build = Json.builder().op("equals").key((String) jsonObject.get("value")).value((String) jsonObject.get("label")).build();
        jsonArray.add(build);
        packJson(array,jsonArray);
        List<Json> jsons = jsonArray.toJavaList(Json.class);
        Set<String> set888 = FileUtil.readFileByLines("D:\\888.txt");
        List<AreaCode> list = new ArrayList<>();
        for (String s : set888) {
            String[] split = s.split(",");
            for (Json userJson : jsons) {
                if (split[0].equals(userJson.getValue())){
                    list.add(AreaCode.builder().code(userJson.getKey()).count(Integer.valueOf(split[1])).name(split[0]).build());
                }
            }
        }
        System.out.println(list);
        return list;
    }

    @Override
    public List<Date> searchDate() {
        return null;
    }





    private void packJson(JSONArray array,JSONArray jsonArray){
        for (Iterator iterator = array.iterator(); iterator.hasNext(); ) {
            JSONObject object = (JSONObject) iterator.next();
            Json build = Json.builder().op("equals").key((String) object.get("value")).value((String) object.get("label")).build();
            jsonArray.add(build);
            if(object.containsKey("children")){
                packJson((JSONArray) object.get("children"),jsonArray);
            }
        }
    }


    @Override
    public List<Json> cacheTest() throws Exception {
        List<TLog> tLogs = new ArrayList<>();
        tLogs.add(TLog.builder().productCode("李阳阳111").insertDate(new Date()).id(1111l).build());
        tLogs.add(TLog.builder().productCode("李阳阳222").insertDate(new Date()).id(2222l).build());
//
        Example example = new Example(TLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productCode",111);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("example",example);
        String showSql = MySqlUtil.showSql(sqlSession,tLogMapper, "deleteByExample", new Object[]{example});
        System.out.println(showSql);
//        String t_log = GenerateSqlUtil.getParams("t_log", tLogs);
//        List<TOrder> singletonList = Collections.singletonList(TOrder.builder().orderId("1").build());
//        String template = GenerateSqlUtil.getParams("t_order", singletonList);

//        MappedStatement sql = sqlSessionFactory.getConfiguration().getMappedStatement("com.example.lyy.mapper.TLogMapper.insertListExtend");
//        System.out.println(sql);
//
//        String executeSql = SqlUtil.getExecuteSql(tLogs, tLogMapper);
//        System.out.println(executeSql);
//        TLog tLog = tLogMapper.selectLog(TLog.builder().id(1111l).productCode("22222222").build());
       /* List<Json> list = mdMapper.selectProMapping();
        for (Json json : list) {
            json.setOp("equals");
        }

        return list;*/
       return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String configProduct(String json,String entryCode,String exitCode) {
        List<TPremFactorRela> tPremFactorRelas = tPremFactorRelaMapper.select(TPremFactorRela.builder().productCode("19PR756248").build());
        if(CollectionUtils.isEmpty(tPremFactorRelas)){
            throw new RuntimeException("没有前置可用因子19PR756248");
        }
        StringBuilder stringBuilder = new StringBuilder();
        Map map = (Map) JSON.parse(json);
        String codes = (String)map.get("code");
        String[] split = codes.split(",");
        Arrays.asList(split).stream().forEach(code ->{
            List<TBizEntryRela> list = null;
            if(isExist(code,stringBuilder)){
                list = new ArrayList<>();
                list.add(TBizEntryRela.builder()
                        .channelCode("tour")
                        .productCode(code)
                        .source("00")
                        .entry(entryCode)
                        .kind("A1")
                        .build());
                list.add(TBizEntryRela.builder()
                        .channelCode("tour")
                        .productCode(code)
                        .source("00")
                        .entry(exitCode)
                        .kind("A2")
                        .build());
                list.add(TBizEntryRela.builder()
                        .channelCode("tour")
                        .productCode(code)
                        .source("01")
                        .entry(entryCode)
                        .kind("A1")
                        .build());
                list.add(TBizEntryRela.builder()
                        .channelCode("tour")
                        .productCode(code)
                        .source("01")
                        .entry(exitCode)
                        .kind("A2")
                        .build());
                tBizEntryRelaMapper.insertListExtend(list);
                if(isExistFac(code,stringBuilder)){
                    Map<String,String> httpMap = new HashMap<>();
                    tPremFactorRelas.parallelStream().forEach(tPremFactorRela -> {
                        tPremFactorRela.setId(null);
                        tPremFactorRela.setProductCode(code);
                    });
                    httpMap.put("param",code);
                    httpMap.put("url","http://prod-svc-dev.jiangtai.com/productdetailsvc");
                    String resp = HttpClientUtil.doHttpGet(httpMap);
                    System.out.println("=============================="+resp);
                    Map respMap = (Map) JSON.parse(resp);
                    Map dataMap = (Map) respMap.get("data");
                    if(CollectionUtils.isEmpty(dataMap)){
                        stringBuilder.append(","+code+"下架啦！");
                    }else{
                        List<TPremFactorRela> factorRelas = new ArrayList<>();
                        if (dataMap.containsKey("factorVOList")){
                            List<Map<String,String>> factorList = (List<Map<String,String>>) dataMap.get("factorVOList");
                            if(!CollectionUtils.isEmpty(factorList)){
                                List<String> factorCodes = factorList.parallelStream().filter(Objects::nonNull).map(item -> item.get("factorCode")).collect(Collectors.toList());
                                Future<String> stringFuture = task.insertDataBase(stringBuilder, code, factorCodes, tPremFactorRelas, factorRelas);
                                try {
                                    if ("0000".equals(stringFuture.get())) {
                                        log.info("=========================插入的list" + factorRelas);
                                        tPremFactorRelaMapper.insertListExtend(factorRelas);
                                    } else {
                                        stringBuilder.append("," + code + "没有可用的因子");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    throw new RuntimeException("线程阻塞或数据库死锁，回滚。。。");
                                }
                            }
                        } else {
                            stringBuilder.append(","+code+"没有因子，入口估计配错啦！:)");
                        }
                    }
                } else {
                    stringBuilder.append(","+ code +"已经配置过因子啦！:)");
                }
            }else{
                stringBuilder.append(","+ code +"已经配置过入口啦！:)");
            }

        });
        log.debug("======================开始配偶underWriting");
        insertTUnderWriting(Arrays.asList(split));

        if(StringUtils.isEmpty(stringBuilder.toString()) ){
            return "成功";
        }
        return stringBuilder.substring(1);

    }
    private void insertTUnderWriting(List<String> productCodes){
        List<TUnderWriting> tUnderWritings = new ArrayList<>();
        productCodes.parallelStream().forEach(code ->{
            tUnderWritings.add(TUnderWriting.builder().productCode(code)
                    .note("线上出单")
                    .attribute("issueType")
                    .channelCode("tour")
                    .attributeValue("01")
                    .build());
        });
        if (!CollectionUtils.isEmpty(tUnderWritings)){
            tUnderWritingMapper.insertListExtend(tUnderWritings);
        }
    }

    private Boolean isExist(String code,StringBuilder stringBuilder){
        TBizEntryRela build = TBizEntryRela.builder().productCode(code).build();
        List<TBizEntryRela> select = tBizEntryRelaMapper.select(build);
        if(CollectionUtils.isEmpty(select)){
            return true;
        }else{
            return false;
        }

    }

    private Boolean isExistFac(String code,StringBuilder stringBuilder){
        TPremFactorRela build = TPremFactorRela.builder().productCode(code).build();
        List<TPremFactorRela> select = tPremFactorRelaMapper.select(build);
        if(CollectionUtils.isEmpty(select)){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public JSONObject searchProByDate(List<Date> dates) {

        return null;
    }


    @Override
    public JSONObject insertItemJson(JSONObject jsonObject,StringBuilder stringBuilder) {
       /* List<Map> list = new ArrayList<>();
        List<Item> items = itemMapper.selectAll();
        jsonObject = (JSONObject)jsonObject.get("lyy");
        JSONObject json = new JSONObject();
        Map map = (Map)jsonObject.get("lyy");
        insertJson(jsonObject,items,json,stringBuilder);
        return json;*/
       return null;
    }

    @Override
    public List<TFullpathElementDef> testProvider() {
        return tFullpathElementDefMapper.selectAll();


    }

    @Override
    public void testMapperProvider(String column) {
        switch (column){
            case "select":
                String[] annotation = ReflectionFieldUtil.getAnnotation2(new TOrder());
                System.out.println("================="+annotation);
                break;
            case "insert":

             default:
                 break;
        }
    }

    @Override
    public void diff() throws IOException {
        Set<String> set = FileUtil.readFileByLines("D:\\888.txt");
        List<DiffInfo> diffInfos = new ArrayList<>();
        for (String s : set) {
            String[] split = s.split(",");
            diffInfos.add(DiffInfo.builder().appCode(split[0]).prem(split[1]).policyNo(split[2]).desc(split[3]).build());
        }
        String name = "1.txt";
        Map<String, List<DiffInfo>> collect = diffInfos.stream().collect(Collectors.groupingBy(DiffInfo::getDesc));
        for (String s : collect.keySet()) {
            List<DiffInfo> temp = collect.get(s);
            List<String> list = new ArrayList<>();
            for (DiffInfo diffInfo : temp) {
                String tempStr = diffInfo.getAppCode() +"\t" +diffInfo.getPrem()+"\t" +diffInfo.getPolicyNo()+"\t" +diffInfo.getDesc();
                list.add(tempStr);
            }
            FileUtil.write2LocalFold(list,"D:\\",name);
            name += "1";
        }
    }

    @Override
    public List<Json> exportMedData() {
        Map<String,String> httpMap = new HashMap<>();
        httpMap.put("param","26");
        httpMap.put("url","http://user-svc-dev.jiangtai.com/api/v1.5/orgElementList/edu");
        String resp = HttpClientUtil.doHttpGet(httpMap);
        Map respMap = (Map) JSON.parse(resp);
        List<Map> list = (List<Map>)respMap.get("list");
        List<Json> jsonList = new ArrayList<>();
        for (Map map : list) {
            jsonList.add(Json.builder().key((String) map.get("valuesCode")).value((String)map.get("elementValues")).build());
        }
        return jsonList;
    }

    @Override
    public void htmlToPdf() throws Exception {
        Map<String,String> map = new HashMap<>();
        String nextId = "";
        map.put("no",nextId);
        map.put("code","lyy123456");
        map.put("description","鼠标键盘");
        map.put("unit","套");
        map.put("quantity","1");
        map.put("price","2366￥");
        map.put("remark","测试数据");
        map.put("accounting","李阳阳");
        map.put("receivables","李老板");
        map.put("ticket", "T" + DateUtil.date2Str(new Date(),DateUtil.DATETIME));
        map.put("amount", MoneyToCNFormat.formatToCN(Double.valueOf(2366)));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String format = simpleDateFormat.format(date);
        map.put("date",format);

        List<Template> templates = templateMapper.selectAll();
        String content = new String(templates.get(0).getTestContent(), "utf-8");
        content =  DFormat.freemarkerProcess(map,content);
        Document parse = Jsoup.parse(content);


        System.out.println(content);
        generateHtmlFile(content);

    }

    @Override
    public ResponseData checkData() throws Exception {
        for (int i = 0; i < 150; i++) {
            runTask.insertLog(i);
        }


//        Set<String> set = new HashSet<>();
//        Set<String> policyNoSet = new HashSet<>();
//        Set<String> premSet = new HashSet<>();
//        List<Data2> data2s = data2Mapper.selectAll();
//        List<Data1> data1s = data1Mapper.selectAll();
//        System.out.println("-----"+data1s.get(0));
//        System.out.println("-----"+data1s.get(0).getRid());
//
//        data2s.parallelStream().forEach(item -> {
//            Optional<Data1> first = data1s.parallelStream().filter(data1 -> data1.getRid().equals(item.getAppCode2())).findFirst();
//            Optional<Data1>  first2 = data1s.parallelStream().filter(data1 -> data1.getAppCode().equals(item.getAppCode2())).findFirst();
//            if (first.isPresent() | first2.isPresent()){
//                Data1 data1 =  null;
//                if (first.isPresent()){
//                    data1 = first.get();
//                }else if (first2.isPresent()){
//                    data1 = first2.get();
//                }
//                if (!data1.getPolicyNo().equals(item.getPolicyNo2())){
//                    policyNoSet.add(data1.getAppCode() + "\t" + item.getAppCode2() + "\t" + data1.getPolicyNo() + "\t" + item.getPolicyNo2() );
//                }
//                if (!data1.getPrem().equals(item.getPrem2())){
//                    premSet.add(data1.getAppCode() + "\t"+ item.getAppCode2()+  "\t" + data1.getPrem() +"\t"+ item.getPrem2() );
//                }
//            }else{
//                if (item.getPrem2().compareTo(BigDecimal.ZERO) == 0){
//
//                }else{
//                    set.add(item.getAppCode2() + "\t" + item.getPrem2() );
//                }
//            }
//        });
//        FileUtil.write2LocalFold(set,"D:\\ygd.txt");
//        FileUtil.write2LocalFold(policyNoSet,"D:\\ygdpolicy.txt");
//        FileUtil.write2LocalFold(premSet,"D:\\ygdprem.txt");
//        checlData2();

        return null;
    }

    @Override
    public ResponseData selectUser(String name) {
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程："+Thread.currentThread().getName()+"执行");
                    TLog build = TLog.builder().productCode("111111").insertDate(new Date()).build();
                    tLogMapper.insertSelective(build);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("子线程："+Thread.currentThread().getName()+"执行");
                    TLog build = TLog.builder().productCode("222222").insertDate(new Date()).build();
                    tLogMapper.insertSelective(build);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es2.shutdown();
        System.out.println("等待两个线程执行完毕…… ……");

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
        TLog build = TLog.builder().productCode("333333").insertDate(new Date()).build();
        tLogMapper.insertSelective(build);
        List<TLog> tLogs = tLogMapper.selectAll();
        return ResponseUtil.packSuccessMultiResponseDataObject(tLogs,tLogs.size());
    }

    void checlData2() throws IOException {

//        Set<String> set = new HashSet<>();
//        Set<String> policyNoSet = new HashSet<>();
//        Set<String> premSet = new HashSet<>();
//
//        List<Data1> data1s2 = data1Mapper.selectAll();
//        List<Data2> data2s2 = data2Mapper.selectAll();
//        data1s2.parallelStream().forEach(item -> {
//            Optional<Data2> first = data2s2.parallelStream().filter(data2 -> data2.getAppCode2().equals(item.getAppCode())).findFirst();
//            Optional<Data2> first2 = data2s2.parallelStream().filter(data2 -> data2.getAppCode2().equals(item.getRid())).findFirst();
//            if (first.isPresent() | first2.isPresent()){
//                Data2 data2 = null;
//                if (first.isPresent()){
//                    data2 = first.get();
//                }else if (first2.isPresent()){
//                    data2 = first2.get();
//                }
//                if (!data2.getPolicyNo2().equals(item.getPolicyNo())){
//                    policyNoSet.add(data2.getAppCode2() + "\t" + data2.getPolicyNo2() + "\t" + item.getPolicyNo() );
//                }
//                if (!data2.getPrem2().equals(item.getPrem())){
//                    premSet.add(data2.getAppCode2() + "\t" + data2.getPrem2() +"\t"+ item.getPrem() );
//                }
//            }else{
//                set.add(item.getAppCode() + "\t" + item.getPrem());
//            }
//        });
//        FileUtil.write2LocalFold(set,"D:\\insd.txt");
//        FileUtil.write2LocalFold(policyNoSet,"D:\\insdpolicy.txt");
//        FileUtil.write2LocalFold(premSet,"D:\\insdprem.txt");


    }

    private void generateHtmlFile(String content) throws Exception {
        /*String path = ResourceUtils.getURL("classpath:").getPath();
        File file = new File(path + System.currentTimeMillis() + ".html");*/
        File temp = File.createTempFile("temp", ".html");
        System.out.println(temp.getAbsolutePath());
        FileOutputStream out = new FileOutputStream(temp);
        out.write(content.getBytes("UTF-8"));
        generatePdfFile(temp);
    }

    private void generatePdfFile(File htmlFile) throws IOException {
        Html2PdfUtil.convert(htmlFile.getAbsolutePath(),"D:/11.pdf","wkhtmltopdf");
        htmlFile.deleteOnExit();
    }


    /*private void insertJson(JSONObject jsonObject, List<Item> items, JSONObject json,StringBuilder stringBuilder) {
        for (Item item : items) {
            if(item.getType().equals("00")){
                String jsonKey = null;
                if(item.getItem().contains("\\.")){
                    String[] split = item.getItem().split("\\.");
                    jsonKey = split[split.length-1];
                }else{
                    jsonKey = item.getItem();
                }
                String[] split = item.getItem().split("\\.");
                if (split.length==1){
                    if(StringUtils.isEmpty(json.get(jsonKey))){
                        json.put(jsonKey,jsonObject.get(jsonKey));
                    }
                }else{
                    JSONObject temp = json;
                    for (int i = 0; i < split.length-1; i++) {
                        String s = split[i];
                        if(!temp.containsKey(s)){
                            temp.put(s,new JSONObject());
                        }
                        temp = (JSONObject) temp.get(s);
                    }
                    if(item.getIsmust().equals("Y") && StringUtils.isEmpty(jsonObject.get(split[split.length-1]))){
                        stringBuilder.append(item.getItemdesc()+"不能为空,");
                    }
                    temp.put(split[split.length-1],jsonObject.get(split[split.length-1]));
                }
            }else if(item.getType().equals("01")){
                JSONObject temp = json;
                JSONArray array = new JSONArray();
                String jsonKey = null;
                String[] split = item.getItem().split("\\.");
                for (int i = 0; i < split.length-2; i++) {
                    jsonKey = split[i];
                    if(!temp.containsKey(jsonKey)){
                        temp.put(jsonKey,new JSONObject());
                    }
                    temp = (JSONObject) temp.get(jsonKey);
                }
                if(!temp.containsKey("extend")){
                    temp.put("extend",new JSONArray());
                }
                array = (JSONArray) temp.get("extend");
                JSONObject con = new JSONObject();
                con.put("key",jsonKey);
                if(item.getIsmust().equals("Y") && StringUtils.isEmpty(jsonObject.get(split[split.length-1]))){
                    stringBuilder.append(item.getItemdesc()+"不能为空,");
                }
                con.put("value",jsonObject.get(split[split.length-1]));
                array.add(con);
            }else if(item.getType().equals("02")){
                String path = item.getItem();
                String[] split = path.split("\\.");
                String s = split[split.length - 1].toString();
                insertJson((JSONObject) jsonObject.get(s),items,json,stringBuilder);
            }
        }
    }*/



}

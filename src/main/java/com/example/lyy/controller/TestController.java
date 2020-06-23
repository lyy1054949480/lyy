package com.example.lyy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lyy.aspect.CheckParam;
import com.example.lyy.domain.ProByDateVO;
import com.example.lyy.domain.PromptingMessageEnum;
import com.example.lyy.domain.ResponseCode;
import com.example.lyy.domain.ResponseData;
import com.example.lyy.entity.*;
import com.example.lyy.limit.RateLimiter;
import com.example.lyy.mapper.TOrderMapper;
import com.example.lyy.mapper.TUserMapper;
import com.example.lyy.merge.templateSql.EntityClass;
import com.example.lyy.mq.RabbitSender;
import com.example.lyy.service.JsonService;
import com.example.lyy.service.TestService;
import com.example.lyy.thread.ExecutorConfig;
import com.example.lyy.thread.MyRejectedPolicy;
import com.example.lyy.thread.RunTask;
import com.example.lyy.token.JsonWebToken;
import com.example.lyy.util.auxiliary.ResponseUtil;
import com.example.lyy.util.ecxel.ExcelUtil;
import com.example.lyy.util.ecxel.ExportExcelUtil;
import com.example.lyy.util.ecxel.ExportExcelWrapper;
import com.example.lyy.util.id.IDMaker;
import com.example.lyy.util.id.SnowflakeIdWorker;
import com.example.lyy.util.invoke.ReflectionFieldUtil;
import com.example.lyy.util.redis.RedisLock;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

@Controller
@RequestMapping("test")
@Api(tags = "测试接口")
@Slf4j
@EnableTransactionManagement
public class TestController {


    @Autowired
    ExecutorConfig executorConfig;

    @Autowired
    RabbitSender rabbitSender;

    @Autowired
    TestService testService;

    @Autowired
    JsonService jsonService;

    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private RedisLock redisLock;

    @Value("${lyy.entryCode}")
    private String entryCode;

    @Value("${lyy.exitCode}")
    private String exitCode;

    @Value("${lyy.entryCodePer}")
    private String entryCodePer;

    @Value("${lyy.exitCodePer}")
    private String exitCodePer;

    @Autowired
    TUserMapper tUserMapper;

    @Resource
    Executor asyncServiceExecutor;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object,Object> template;

    private List<Date> dates;

    @Autowired
    RunTask runTask;

    @Autowired
    EntityClass entityClass;
    private final String 团险 = "团险";

    @PostConstruct
    public void searchDate(){
        List<Date> dates = testService.searchDate();
        this.dates = dates;
        log.info("时间----------------------：{}",dates);
    }

    @ApiOperation(value = "对象转xml", notes = "测试")
    @GetMapping(value = "perform", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = User.class, message = "--") })
    //@CheckParam
    public ResponseData perform(@RequestParam String name,HttpServletRequest request) throws Exception {
//        TUser aaaa = TUser.builder().name("aaaa").build();
//        Boolean lyy970120 = redisTemplate.opsForValue().setIfAbsent("lyy", "lyy970120");
//        template.opsForHash().put("my",aaaa.getName(),aaaa);
//        TUser tUser = new TUser();
//        tUser.setName(name);
//        redisLock.setIfAbsent("lyy",tUser,10);
//        stringRedisTemplate.opsForValue().set("lyy2","hahahha",20,TimeUnit.SECONDS);
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
//        Claims claims = Jwts.parser().setSigningKey("123").parseClaimsJws(authorization).getBody();
//        System.out.println(JSON.toJSON(claims).toString());
        return testService.selectUser(name);
    }

    @ApiOperation(value = "去除xml中的换行符", notes = "测试")
    @PostMapping(value = "formatMessage", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String formatMessage(@RequestBody String json) throws Exception {
        Map map = (Map) JSON.parse(json);
        String data = (String) map.get("data");
        return data.replace("\\|\r|\n","");
    }

    @ApiOperation(value = "查询产品是否投保", notes = "测试")
    @PostMapping(value = "checkProduct", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public void checkProduct(@RequestBody String json) throws Exception {
//        return ResponseUtil.packSuccessResponseData(testService.checkProduct(json), null, 0);
    }

    @ApiOperation(value = "查询每天的投保单", notes = "测试")
    @PostMapping(value = "/searchProByDate/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataType = "String", paramType = "query",
                    allowableValues = "id1,id2,id3", allowMultiple = false)
    })
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String searchProByDate(@PathVariable(name = "id") String id){
        JSONObject jsonObject = testService.searchProByDate(this.dates);
        return JSON.toJSONString(jsonObject);
    }

    @ApiOperation(value = "校验参数是否正确", notes = "测试")
    @PostMapping(value = "insertItem", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public JSONObject insertItem(@RequestBody String json) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jsonObject = testService.insertItemJson(JSONObject.parseObject(json), stringBuilder);
        if(StringUtils.isEmpty(stringBuilder.toString())){
            return jsonObject;
        }else{
            String s = ResponseUtil.packErrorResponseData(PromptingMessageEnum.MESSAGE_1.getMessage(), stringBuilder.substring(0,stringBuilder.length()-1).toString());
            return JSONObject.parseObject(s);
        }
    }


   /* @ApiOperation(value = "包装json", notes = "测试")
    @PostMapping(value = "/packJson")
    *//*@ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataType = "String", paramType = "query",
                    allowableValues = "id1,id2,id3", allowMultiple = false)
    })*//*
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "结果") })
    public String packJson(@RequestBody String json){
        Map<String, List<TFullpathElementDef>> map = jsonService.searchAndPack();
        if(!CollectionUtils.isEmpty(map)){
            return ResponseUtil.packSuccessResponseData(map,null,map.size());
        }else{
            return ResponseUtil.packErrorResponseData(ResponseCode.SystemError.getCode(),PromptingMessageEnum.MESSAGE_8.getMessage());
        }
    }*/


    @ApiOperation(value = "校验参数是否正确", notes = "测试")
    @PostMapping(value = "packAreaJson", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = ProByDateVO.class, message = "--") })
    public ResponseData packAreaJson(@RequestBody String json) throws Exception {

        System.out.println();
        List<AreaCode> jsons = testService.packAreaJson(json);
        return ResponseUtil.packSuccessMultiResponseDataObject(jsons,0);
    }

    @ApiOperation(value = "pdf", notes = "测试")
    @GetMapping(value = "testPdf", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public void testPdf(HttpServletResponse response) throws Exception {
        testService.htmlToPdf();
    }

    @ApiOperation(value = "新老映射", notes = "测试")
    @PostMapping(value = "oldNewMapping", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String oldNewMapping(@RequestBody String json) throws Exception {
        Map map = (Map)JSON.parse(json);
        JSONArray jsonArray = new JSONArray();
        String old = (String) map.get("old");
        String notOld = (String)map.get("new");
        String[] oldSplit = old.split(",");
        String[] newSplit = notOld.split(",");
        for (int i = 0; i < oldSplit.length; i++) {
            String newString = newSplit[i];
            String oldString = oldSplit[i];
            Json equals = Json.builder().op("equals").key(oldString).value(newString).build();
            jsonArray.add(equals);

        }
        return ResponseUtil.packSuccessResponseData(jsonArray,null,0);
    }


    @ApiOperation(value = "copyPro(BeanUtils.copyProperties)", notes = "测试")
    @PostMapping(value = "copyPro", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String copyPro(@RequestBody ParamVo paramVo) throws Exception {
        ParamForVo paramForVo = new ParamForVo();
        BeanUtils.copyProperties(paramVo,paramForVo);
        return ResponseUtil.packSuccessResponseData(paramForVo,null,0);
    }

    @ApiOperation(value = "submitOrder", notes = "测试")
    @PostMapping(value = "submitOrder", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public JSONObject submitOrder(@RequestBody String json, HttpServletRequest request) throws Exception {
        return jsonService.insertNewJson(json,request);
    }


    @ApiOperation(value = "testMultithreading", notes = "测试")
    @PostMapping (value = "testMultithreading")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public void testMultithreading(@RequestBody String json) throws Exception {
       /* Data data = Data.builder().productCode("ssaa").createTime(new Date()).productName("ddddd").build();
        List<Data> list = new ArrayList<>();
        list.add(data);

        MyTask myTask = new MyTask();
        myTask.setList(list);
        ExecutorService executor = Executors.newFixedThreadPool(20);
        FutureTask futureTask = new FutureTask(myTask);
        executor.execute(futureTask);
        Object o = futureTask.get();
        System.out.println(o);
        executor.shutdown();*/
    }







    @ApiOperation(value = "1.8", notes = "测试")
    @GetMapping(value = "cacheTest")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String cacheTest() throws Exception {
        entityClass.getEntityClass();
        List<Json> list = testService.cacheTest();
        return null;
    }


    @ApiOperation(value = "1.8", notes = "mq")
    @PostMapping(value = "/api/mq")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public void mq(HttpServletRequest request) throws Exception {
        System.out.println(request.getHeader("name"));
        //rabbitSender.send("hahah");
    }

    @ApiOperation(value = "1.8", notes = 团险)
    @PostMapping(value = "configProduct")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String configProduct(@RequestBody String json) throws Exception {
        String s = testService.configProduct(json,entryCode,exitCode);
        return ResponseUtil.packErrorResponseData(ResponseCode.Success.getCode(),s);
    }

    @ApiOperation(value = "1.8", notes = "个险")
    @PostMapping(value = "configProductPerson")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String configProductPerson(@RequestBody String json) throws Exception {
        String s = testService.configProduct(json,entryCodePer,exitCodePer);
        return ResponseUtil.packErrorResponseData(ResponseCode.Success.getCode(),s);

    }


    @ApiOperation(value = "1.8", notes = "个险")
    @GetMapping(value = "/testMQ/{orderCode}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = ParamForVo.class, message = "--") })
    public String testMQ(@PathVariable(name = "orderCode") String orderCode) throws Exception {
        if("1".equals(orderCode)){
            ParamForVo build = ParamForVo.builder().id("111").paramNaem("1111").type("11").username("444").build();
            return ResponseUtil.packSuccessResponseData(build,null,1);
        }else{
            return ResponseUtil.packErrorResponseData("9999","error");
        }
        //rabbitSender.send("hello");
    }


    @ApiOperation(value = "excel", notes = "测试")
    @GetMapping(value = "excel", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public ResponseEntity<byte[]> excel(HttpServletResponse response) throws Exception {
        ExportExcelWrapper exportExcelWrapper = new ExportExcelWrapper();
        MedVO medVo = new MedVO();
        URL resource = this.getClass().getResource("/");
        System.out.println(resource);
        BufferedReader br = new BufferedReader(new FileReader("D:/ideaproject/java/lyy/target/classes/1.json"));
        String str=null;
        String data="";
        while((str=br.readLine())!=null) {
            data=data+str+"\n";
        }
        String json = data;
        List<MedVO> medVOS = JSON.parseArray(json, MedVO.class);
        String[] strings = ReflectionFieldUtil.getAnnotation2(medVo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(System.currentTimeMillis()+".xlsx","utf-8"));
        /*response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(System.currentTimeMillis()+".xls", "UTF-8"));// 设置文件名*/
        exportExcelWrapper.exportExcel( "sheet1",strings,medVOS,outputStream, ExportExcelUtil.EXCEl_FILE_2007);
        return new ResponseEntity<byte[]>(outputStream.toByteArray(),headers, HttpStatus.OK);
        /*ExcelUtil<TFullpathElementDef> excelUtil = new ExcelUtil<>(TFullpathElementDef.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode("111.xls", "UTF-8"));// 设置文件名
        excelUtil.exportExcel(testService.testProvider(),"excel",65536,response.getOutputStream());*/
        /*HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(System.currentTimeMillis()+".xls","utf-8"));*/
        //return new ResponseEntity<byte[]>(outputStream.toByteArray(),headers, HttpStatus.OK);
    }


    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "elevator", value = "电梯编号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "elevatorExitNo", value = "电梯出厂编号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", dataType = "String", paramType = "query",
                    allowableValues = "苹果,荔枝,菠萝", allowMultiple = false)
    })
    @ApiOperation(value = "电梯编号(或出厂编号)查询标的信息", notes = "特设渠道")
    @GetMapping(value = "/sequipObjects/{appCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({@ApiResponse(code = 200, response = String.class, message = "sequip--标的信息")})
    public String sequipAppForms(@PathVariable(name="appCode")String appCode, @RequestParam(value="elevator",required = false)String elevator, @RequestParam(value = "elevatorExitNo",required = false)String elevatorExitNo) {
        if (StringUtils.isEmpty(appCode) || (StringUtils.isEmpty(elevator) && StringUtils.isEmpty(elevatorExitNo))){
            return ResponseUtil.packErrorResponseData(ResponseCode.SystemError.getCode(), PromptingMessageEnum.MESSAGE_7.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "校验参数是否正确", notes = "测试")
    @GetMapping(value = "testMapperProvider", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = ProByDateVO.class, message = "--") })
    public void testMapperProvider(HttpServletResponse response) throws Exception {
        ExcelUtil excelUtil = new ExcelUtil(User.class);
        List<User> list = new ArrayList<>();
        list.add(User.builder().password("1111").username("lyy").build());
        list.add(User.builder().password("2222").username("lyy").build());
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("lyyyyyy", "UTF-8") + ".xls");
        excelUtil.exportExcel(list,"用户",list.size(),response.getOutputStream());
//        return ResponseUtil.packSuccessNoneDataObject();
//        return ResponseUtil.packResponseData(ResponseCode.Success.getCode(), PromptingMessageEnum.MESSAGE_8.getMessage(), null, null, null);
          //testService.testMapperProvider("select");
//        List<String> list = new ArrayList<>();
//        return user;
    }


    @ApiOperation(value = "1.8", notes = "token")
    @PostMapping(value = "token")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String token(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        Claims claims = JsonWebToken.parserJavaWebTokenBySigningKey(token.substring(7));
        if (claims != null){
            return "true";
        }
        return "error";
    }


    @ApiOperation(value = "1.8", notes = "getToken")
    @GetMapping(value = "getToken")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String getToken() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("user","admin");
        String javaWebToken = JsonWebToken.createJavaWebToken(map, 100000000L);
        return javaWebToken;
    }


    @ApiOperation(value = "1.8", notes = "")
    @GetMapping(value = "oauth2")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String oauth2() throws Exception {
        return null;
    }

    @ApiOperation(value = "1.8", notes = "")
    @GetMapping(value = "callback")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    @CheckParam()
    public void callback(String code) throws Exception {
        System.out.println("=============================callback"+code);

    }


    @ApiOperation(value = "check_ins_data", notes = "")
    @PostMapping(value = "checkInsData")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    @MyRejectedPolicy("insertLog")
    public String checkInsData() throws Exception {
        testService.checkData();
        return null;
    }

    @ApiOperation(value = "executeTaskCount", notes = "")
    @GetMapping(value = "executeTaskCount")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public String executeTaskCount() throws Exception {
        LinkedHashMap<String, CopyOnWriteArrayList<FutureTask>> rejectTaskMap = ExecutorConfig.getRejectTaskMap();
        System.out.println(JSON.toJSON(rejectTaskMap));
        ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
        for (String s : rejectTaskMap.keySet()) {
            for (FutureTask  futureTask: rejectTaskMap.get(s)) {
                EXECUTOR_SERVICE.execute(futureTask);
            }
        }
        return null;
    }


    @ApiOperation(value = "diff", notes = "")
    @PostMapping(value = "diff")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    public void diff(HttpServletRequest request) throws Exception {

//        Font font = GetFontUtil.getFont(Font.BOLD, 20f);
//        StackedBarChart stackedBarChart = new StackedBarChart();
//        ChartPanel chart = stackedBarChart.createChart();
//        System.out.println(font.getFontName());
//        System.out.println("--------------------");
//        String name = request.getHeader("name");
//        System.out.println(name);

    }

    @GetMapping("/index")
    public ModelAndView  index(){
        ModelAndView mav=new ModelAndView("socket");
        mav.addObject("uid", IDMaker.getApplicationCode());
        return mav;
    }

    @ApiOperation(value = "thread", notes = "")
    @PostMapping(value = "thread")
    @ResponseBody
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    @MyRejectedPolicy("runTest")
    /**
     * CountDownLatch.countDown 返回 future有可能并未执行完，future.get会阻塞线程，导致一直等待执行结果
     */
    public void thread(Integer i) throws Exception {
        final CountDownLatch switchLatch = new CountDownLatch(1);
        final CountDownLatch latch = new CountDownLatch(1);
//        runTask.setSwitchLatch(switchLatch);
//        runTask.setExecuteLatch(latch);
        Future<String> stringFuture =  runTask.runTest(i, switchLatch, latch);
        Future<String> stringFuture1 = runTask.runTest2(i, switchLatch, latch);
        Future<String> stringFuture2 = runTask.runTest3(i, switchLatch, latch);
//        Future<String> stringFuture3 = runTask.runTest4(i, switchLatch, latch);
        switchLatch.countDown();
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while(!stringFuture.isDone()){
            System.out.println("execute.......");
        }
        while(!stringFuture1.isDone()){
            System.out.println("execute1.......");
        }
        while(!stringFuture2.isDone()){
            System.out.println("execute2.......");
        }


        System.out.println(stringFuture.get());
        System.out.println(stringFuture1.get());
        System.out.println(stringFuture2.get());
//        System.out.println(stringFuture3.get());




    }

    @GetMapping("/testRateLimit")
    @RateLimiter(value = 5)
    @ApiResponses({ @ApiResponse(code = 200, response = String.class, message = "--") })
    @ResponseBody
    public void testRateLimit(){
        System.out.println("testRateLimit");
    }

}



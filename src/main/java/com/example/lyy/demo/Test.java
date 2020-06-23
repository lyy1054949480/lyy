package com.example.lyy.demo;
import com.example.lyy.util.ClassTools;
import com.example.lyy.util.id.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;


@Slf4j
public class Test {
    private static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(255,0);

    private static boolean modifyFileContent(String fileName, String oldstr, String newStr) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileName, "rw");
            String line = null;
            long lastPoint = 0; //记住上一次的偏移量
            while ((line = raf.readLine()) != null) {
                final long ponit = raf.getFilePointer();
                if(line.contains(oldstr)){
                    String str=line.replace(oldstr, newStr);
                    raf.seek(lastPoint);
                    raf.writeBytes(str);
                }
                lastPoint = ponit;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void aaa(){

        Set<Class<?>> classes = ClassTools.getClasses("jtpf.ins.svc.controller");
        for (Class<?> aClass : classes) {
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                //获取到该方法的参数们
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (Class<?> parameterType : parameterTypes) {
                    Constructor<?>[] constructors = parameterType.getConstructors();
                    for (Constructor<?> constructor : constructors) {
                        String[] parameterNames = u.getParameterNames(constructor);
                        if (parameterNames != null && parameterNames.length > 0){
                            for (String parameterName : parameterNames) {
                                if ("userCode".equals(parameterName) | "holderCode".equals(parameterName)){
                                    System.out.println(aClass.getSimpleName()+"------"+method.getName());
                                }
                            }
                        }
                    }

                }


            }
        }

    }

    public static void main(String[] args) throws Exception {
        ArrayList<Object> objects = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        objects.stream().forEach(item -> {
            list.add(1);
        });
//        String json = "{\"code\":\"0000\",\"data\":\"\"}";
//        Object read = JSONPath.read(json, "$.code");
//        System.out.println(read);
        //        String hashpw = BCrypt.hashpw("112233", BCrypt.gensalt());
//        System.out.println(hashpw);
//        boolean checkpw = BCrypt.checkpw("112233", hashpw);
//        System.out.println(checkpw);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode("112233");
//        System.out.println(encode);
//        File f = new File("C:/Users/Administrator/Desktop/1.xml");
//        SAXReader reader = new SAXReader();
//        Document doc = reader.read(f);
//        String s = doc.asXML();
//        Element root = doc.getRootElement();
//        Map<String, String> map = readStringXml(s);
//        System.out.println(JSON.toJSON(map).toString());
        //System.out.println(        generateText("eduAppFormForCaseQo"));


        /*String ss = "A111";
        System.out.println(ss.substring(1));*/
        /*System.out.println("selectByPage".matches("(.*)ByPage"));
        System.out.println(Pattern.matches("selectByPage","(.*)ByPage"));
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/user/info","/user/i"));
        System.out.println(antPathMatcher.isPattern("/*"));*/
       /* Set<String> insset = FileUtil.readFileByLines("D:\\111.csv");
        Set<String> ygset = FileUtil.readFileByLines("D:\\333.csv");
        insset.removeAll(ygset);
        FileUtil.write2LocalFold(insset,"D:\\888.txt");
*/

      /*  Set<String> insset = FileUtil.readFileByLines("D:\\111.csv");
        Set<String> ygset = FileUtil.readFileByLines("D:\\333.csv");
        System.out.println(ygset.size());
        System.out.println(insset.size());*/
        /*ygset.removeAll(insset);
        FileUtil.write2LocalFold(insset,"D:\\999.txt");
*/
        /*LocalDate now = LocalDate.now();
        System.out.println(now);
        long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(l);
        System.out.println(System.currentTimeMillis());*/
        /*ApplicationContext ctx=new ClassPathXmlApplicationContext("demo.xml");
        User user = (User)ctx.getBean("user");
        System.out.println(user);*/
        /*Set<String> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(String.valueOf(snowflakeIdWorker.nextId()));
        }
        System.out.println(set);*/
        /*Test2 test2 = new Test2();
        Class aClass1 = test2.getClass();
        //Class<?> aClass = Class.forName(Test2.class.getName());
        Method p = aClass1.getDeclaredMethod("aaaaa", (new Object()).getClass());
        Object a = 1;
        Object invoke = p.invoke(aClass1.newInstance(),a);
        System.out.println(invoke);
*/

    /*    Stu stu = new Stu();
        int i = (stu.getAge() == 0) ? 1 : 2;
        System.out.println(i);*/
        /*String s = "[{factorInfos=[{factorCode=19YZ235022, stringFactor=invalidation}, {factorCode=19YZ145546, factorInfos=[{factorCode=19YZ554928, stringFactor=8}, {factorCode=19YZ357893, stringFactor=2202}, {factorCode=19YZ648369, stringFactor=1}], no=1}], productCode=19PR861859, channelNo=sequip, renewal=0, policyNum=1, renewalStatus=0, buyCopies=1, policyNo=432132, userCode=19UC1568120202179117056, insuranceVOS=[{count=1, programCode=19FA155408, riskBeans=[{riskCode=19XZ107609, floatPremiums=[]}]}, {count=1, programCode=19FA277782, riskBeans=[{riskCode=19XZ289924, floatPremiums=[]}]}]}]";
        String s1 = JSONObject.toJSONString(s);
        System.out.println(s1);*/
//        String s = DateUtil.date2Str(new Date(), DateUtil.TIME);
//        System.out.println(s);
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        System.out.println(list.toString());
/*        BigDecimal bigDecimal = new BigDecimal("-0.05");
        DecimalFormat decimalFormat = new DecimalFormat("0.00%");
        String format = decimalFormat.format(bigDecimal);
        System.out.println(format);
        System.out.println(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(DateUtil.date2Str(date,DateUtil.TIME));
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));*/
        /*String aa = "AA";
        String[] split = aa.split(",");
        BigDecimal bigDecimal = new BigDecimal("2.37532");
        BigDecimal decimal = new BigDecimal("10");
        bigDecimal = bigDecimal.divide(decimal,2,BigDecimal.ROUND_HALF_UP);
        DecimalFormat format = new DecimalFormat("0.00%");
        System.out.println(format.format(bigDecimal));
        Address sssss = Address.builder().addressId("1111").addressName("sssss").build();
        Object addressId = ReflectionFieldUtil.getMethod(sssss, "addressId");
        System.out.println(addressId);
        Predicate<String> mp = e->"aa".equals(e.toLowerCase());
        List<String> collect = Arrays.stream(split).filter(mp).collect(Collectors.toList());
        System.out.println(collect);*/
       /* for (int i = 0;i < 100;i++){
            System.out.println(snowflakeIdWorker.nextId("S"));
        }*/
        /*List<Date> dates = new ArrayList<>();
        dates.add(new Date());
        dates.add(new Date());
        dates.stream().filter(Objects::isNull);
        Optional<Date> max = dates.stream().max(Comparator.comparing(Date::getTime));
        if (max.isPresent()){
            System.out.println(max.get());
        }
*/





        /*Map<Integer,String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i,"val"+i);
        }
        map.merge(2,"val2",(value,newValue) -> value.concat(newValue));
        System.out.println(map.get(2));

        long l = System.currentTimeMillis();
        System.out.println(l/1000);*/

        /*StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(",0000");
        System.out.println(stringBuilder.substring(1));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
        Process ipconfig = Runtime.getRuntime().exec("ipconfig");
        InputStream fis=ipconfig.getInputStream();
        //用一个读输出流类去读
        InputStreamReader isr=new InputStreamReader(fis,"GBK");
        //用缓冲器读行
        BufferedReader br=new BufferedReader(isr);
        String line=null;
        //直到读完为止
        while((line=br.readLine())!=null)
        {
            System.out.println(line);
        }*/
        /*BigDecimal decimal = new BigDecimal("1.22452");
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(decimal);*/
        //System.out.println(PlatformOption.getHostNameAndIp());
//        List<Date> list = new ArrayList<>();
//        System.out.println(CollectionUtils.isEmpty(list));
      /*  Predicate predicate = Objects::isNull;
        System.out.println(predicate);*/
     /* List<String> list = new ArrayList<>();
      list.add("sss");
      list.add("aaa");
      list.add("cc");
        Optional<String> first = list.stream().map(String::toUpperCase).findFirst();
        String s = first.get();
        System.out.println(s);*/
     /*String str = "sssss,xx,ll,dd";
      Arrays.stream(str.split(",")).forEach(System.out::println);*/
        /*List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("2.5"));
        list.add(new BigDecimal(("3.92")));
        list.removeIf(Predicate.isEqual(new BigDecimal("2.5")));
        BigDecimal reduce = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce);*/
        //check(list,obj -> check2((Collection) obj),obj -> ((String)obj).length()>2);
        /*TestTask testTask = new TestTask();
        Future<String> stringFuture = testTask.doTaskOne();
        Future<String> stringFuture1 = testTask.doTaskTwo();
        Future<String> stringFuture2 = testTask.doTaskThree();
        if ("success".equals(stringFuture.get()) && "success".equals(stringFuture1) && "success".equals(stringFuture2.get())){
            System.out.println("--------------");
        }*/
       /* TOrder tOrder = TOrder.builder().contextMsg("ss").operator("aaa").orderId("1111").build();
        List<TOrder> list = new ArrayList<>();
        list.add(tOrder);
        MyTask myTask = MyTask.builder().list(list).build();
        ExecutorService executor = Executors.newFixedThreadPool(20);
        FutureTask futureTask = new FutureTask(myTask);
        Future<?> submit = executor.submit(futureTask);
        AsyncResult o = (AsyncResult)futureTask.get();
        System.out.println(o.get());
        executor.shutdown();*/
        /*long count = Arrays.asList(tOrder.getClass().getDeclaredFields()).stream().filter(field -> ReflectionFieldUtil.getMethod(tOrder, field.getName()) == null).count();

        System.out.println(count);*/

//        Arrays.asList(methods).forEach(System.out::println);


        /*ClassPathResource classPathResource = new ClassPathResource("1.json");
        System.out.println(classPathResource.getPath());*/
        /*String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
*/
//        System.out.println(PlatformOption.getHostNameAndIp());


/*
        Map<String,Object> map = new HashMap<>();
        map.put("user","admin");
        String javaWebToken = JavaWebToken.createJavaWebToken(map,100000L);
        System.out.println(javaWebToken);
        Thread.sleep(100);
        Claims claims = JavaWebToken.parserJavaWebToken(javaWebToken);
        System.out.println(claims.get("user"));
*/
      /*  List<BigDecimal> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
           list.add(new BigDecimal(i));
        }
       *//* DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(BigDecimal::doubleValue));
        double sum = statistics.getSum();
        System.out.println(sum);*//*
        Optional<BigDecimal> reduce = list.stream().reduce(BigDecimal::add);
        System.out.println(reduce.get());*/
        /*User user = new User();
        Field[] declaredFields = user.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }*/
        /*Map<String,String> map = new HashMap<>();
        map.put("param",null);
        for (int i =0;i<100;i++){
            System.out.println("---------------------------------------"+i);
            String s = HttpClientUtil.doHttpGet(map);
            System.out.println(s);
            Thread.sleep(100);
        }*/
       /* Set<String> set = FileUtil.readFileByLines("D:\\888.txt");
        Set<String> set2 = FileUtil.readFileByLines("D:\\999.txt");
        set.removeAll(set2);
        for (String s : set) {
            System.out.println(s);
        }*/
        /*Set<String> set = FileUtil.readFileByLines("D:\\888.txt");
        List<ErrorInfo> errorInfos = new ArrayList<>();
        for (String s : set) {
            String[] split = s.split(",");
            errorInfos.add(ErrorInfo.builder().line(split[0]).fileName(split[1]).build());
        }
        Set<String> stringSet = new HashSet<>();
        Map<String, List<ErrorInfo>> collect = errorInfos.stream().collect(Collectors.groupingBy(ErrorInfo::getFileName));
        for (String fileName : collect.keySet()) {
            List<String> temp = FileUtil.readFileByLinesList("D:\\"+fileName);
            List<ErrorInfo> errorInfoList = collect.get(fileName);
            for (ErrorInfo errorInfo : errorInfoList) {
                stringSet.add(temp.get(Integer.valueOf(errorInfo.getLine())-1));
            }

        }
        for (String s : stringSet) {
            System.out.println(s);
        }*/
       /* List<String> temp = FileUtil.readFileByLinesList("D:\\invoice_group2.sql");
        System.out.println(temp.get(0));*/

        /*System.out.println(set2);
        BigDecimal bigDecimal = new BigDecimal(10);
        BigDecimal bigDecimal1 = new BigDecimal(-10);
        System.out.println(bigDecimal.add(bigDecimal1).equals(BigDecimal.ZERO));*/
       /* System.out.println("912224243078002000 ".trim());
        System.out.println("".isEmpty());
        String aa = null;
        System.out.println(aa == null);*/

       /* Set<String> set = FileUtil.readFileByLines("D:\\temp1.txt");
        Set<String> set2 = FileUtil.readFileByLines("D:\\temp2.txt");
        set.removeAll(set2);
        System.out.println(set);*/
        /*String appCode = "sss";
        String pol = "aaaa";
        String json = "{\n" +
                "    \"appCode\": \""+appCode+"\",\n" +
                "    \"attachments\": [\n" +
                "      {\n" +
                "        \"id\": 0,\n" +
                "        \"type\": \"string\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"endDate\": \"2020-01-10 23:59:59\",\n" +
                "    \"issueDate\": \"2020-01-02 16:00:00\",\n" +
                "    \"policyNo\": \""+ pol+"\",\n" +
                "    \"startDate\": \"2020-01-03 00:00:00\",\n" +
                "    \"userCode\": \"19UC1702248319981715458\"\n" +
                "  }";
        System.out.println(json);*/



    }

    public static Map<String, String> readStringXml(String xml) throws DocumentException {
        Document doc = DocumentHelper.parseText(xml);
        Element books = doc.getRootElement();
        System.out.println("rootNode:" + books.getName());
        Iterator Elements = books.elementIterator();
        Map<String, String> map = new HashMap<>();
        while (Elements.hasNext()) {
            Element user = (Element) Elements.next();
            map.put(user.getName(), user.getText());
        }
        return map;
    }

    private static String generateText(String old) {
        return  "limit <bind name=\"LIMIT\" value=\"("+old+".pn - 1) * "+old+".ps\" /> #{LIMIT,jdbcType=INTEGER},#{"+old+".ps,jdbcType=INTEGER}";
    }


    public static void check(Collection collection, Consumer consumer, Predicate predicate) {

        if (!CollectionUtils.isEmpty(collection)) {
            collection.stream().forEach(obj -> {
                if (predicate.test(obj)) {
                    consumer.accept(collection);
                }else{
                    consumer.accept(collection);
                }
            });
        }
    }

    public static void check2(Collection collection) {
        System.out.println(collection.size());
    }

    public static void check3(Collection collection) {
        System.out.println(collection.hashCode());
    }

}

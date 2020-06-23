package com.example.lyy.demo;

import com.alibaba.fastjson.JSON;
import com.example.lyy.entity.*;
import com.example.lyy.util.auxiliary.AesUtil;
import com.example.lyy.util.auxiliary.FileUtil;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;


public class Test2 {

    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(10);


    public static void main(String[] args) throws Exception {
        EXECUTOR_SERVICE.schedule(() -> create(),1000, TimeUnit.MILLISECONDS);
//        Set<String> yg2 = FileUtil.readFileByLines("D:\\888.txt");
//        Set<String> set = new HashSet<>();
//        for (String s : yg2) {
//            String[] split = s.split(",");
//            String trim = split[0].trim();
//            String trim1 = split[1].trim();
//            String s1 = trim +","+ trim1;
//            set.add(s1);
//
//        }
//        FileUtil.write2LocalFold(set,"D:\\888.txt");
        /*String json = "{\"otherParams\":{\"holderName\":\"连云港市第一人民医院\",\"insuredName\":\"连云港市第一人民医院\"},\"appCode\":\"T2003151507411485305\",\"policyNo\":\"PZAB202032070000000001\",\"issueDate\":\"2020-03-13\",\"startDate\":\"2020-01-01 00:00:00\",\"endDate\":\"2020-12-31 23:59:59\",\"userCode\":\"19UC1636731497279127552\",\"attachments\":[{\"id\":\"1909976543582764458\",\"type\":\"A002\"}]}";
        Map jsonMap = (Map) JSON.parse(json);
        String issueDateString = jsonMap.get("issueDate").toString();
        String startDateString = jsonMap.get("startDate").toString();
        String endDateString = jsonMap.get("endDate").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date issueDate = sdf2.parse(issueDateString);
        Date startDate = sdf.parse(startDateString);
        Date endDate = sdf.parse(endDateString);
        System.out.println(issueDate);
        System.out.println(startDate);
        System.out.println(endDate);*/
//        List<Cata> catas = new ArrayList<>();
//        for (int i = 1; i < 5; i++) {
//            Cata cata = Cata.builder().name("cata" + i).build();
//            List<Detail> list = new ArrayList<>(3);
//            for (int j = 1; j < 4; j++) {
//                Detail detail = Detail.builder().info("detail" + i * j).build();
//                List<Option> options = new ArrayList<>(4);
//                for (int k = 1; k < 5; k++) {
//                    Option option = Option.builder().build();
//                    List<String> optionList = new ArrayList<>(4);
//                    for (int l = 0; l < 3; l++) {
//                        Thread.sleep(100);
//                        optionList.add("option"+System.currentTimeMillis());
//                    }
//                    option.setOptions(optionList);
//                    options.add(option);
//                }
//                detail.setList(options);
//                list.add(detail);
//            }
//            cata.setDetails(list);
//            catas.add(cata);
//        }
//        System.out.println(JSON.toJSON(catas).toString());

       /* String body = "<if test=\"eduAppFormForCaseQo.pn !=null and eduAppFormForCaseQo.pn != '' and eduAppFormForCaseQo.ps !=null and eduAppFormForCaseQo.ps != ''\">\n";
        int i = body.indexOf("\"");
        int i1 = body.indexOf(".");
        String substring = body.substring(i+1, i1);
        System.out.println(substring);

        String sql = "limit <bind name=\"LIMIT\" value=\"("+substring+".pn - 1) * "+substring+".ps\" /> #{LIMIT,jdbcType=INTEGER},#{"+substring+".ps,jdbcType=INTEGER}\n";
        System.out.println(sql);*/




/*
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("20");
        list2.add("30");
        List<String> strings = list;
        strings.removeAll(list2);
        System.out.println(strings);
        System.out.println(list);
        System.out.println(list2);*/


        /*List<Human> list = new ArrayList<>();
        list.add(Human.builder().age("1").sex("00").weight("100").build());
        list.add(Human.builder().age("2").sex("01").weight("1001").build());
        list.add(Human.builder().age("3").sex("01").weight("1002").build());
        list.add(Human.builder().age("3").sex("01").weight("1003").build());
        list.add(Human.builder().age("2").sex("01").weight("1005").build());
        list.add(Human.builder().age("1").sex("00").weight("1009").build());
        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(Human::getAge, Collectors.counting()));
        collect.forEach((k,v) ->{
            System.out.println(k+"==="+v);
        });
        Map<Boolean, List<Human>> listMap = list.stream().collect(partitioningBy(Human::isMale));
        listMap.forEach((k,v) ->{
            System.out.println(k+"==="+v);
        });
        Map<Boolean, Map<String, Long>> map = list.stream().collect(partitioningBy(Human::isMale, groupingBy(Human::getAge, counting())));
        List<Human> humans = list.stream().filter(item -> "1".equals(item.getAge())).collect(Collectors.toList());
        Map<Boolean, List<Human>> listHumanMap = list.stream().collect(partitioningBy(human -> "1".equals(human.getAge())));
        System.out.println("-0--------");
        listHumanMap.forEach((k,v) ->{
            System.out.println(k+"==="+v);
        });*/
        /*Set<String> yg2 = FileUtil.readFileByLines("D:\\888.txt");//阳光
        Set<String> jt2 = FileUtil.readFileByLines("D:\\999.txt");//江泰
        jt2.removeAll(yg2);
        FileUtil.write2LocalFold(new ArrayList<>(jt2),"D:\\du.txt");*/
        /*DecimalFormat decimalFormat = new DecimalFormat("0.00%");
        String format = decimalFormat.format(Double.valueOf(-0.01));
        System.out.println(format);
        User user = new User();
        user.setPassword("111");
        user.setAddressList(new ArrayList<>());
        user.setUsername(null);
        System.out.println(JSON.toJSON(user).toString());
        System.out.println(JSON.toJSONString(user));
        System.out.println(JSONObject.toJSONString(user, SerializerFeature.WriteMapNullValue));*/
       /* Date date = new Date();
        int i = date.compareTo(null);
        System.out.println(i);*/
        /*Date date = new Date();
        Thread.sleep(1000);
        Date date1 = new Date();
        int i = date1.compareTo(date);
        System.out.println(i);*/
        /*System.out.println(BigDecimal.ZERO);
        BigDecimal bigDecimal = new BigDecimal("13478400");
        System.out.println(bigDecimal.toPlainString());
        Class<?> userClass = Class.forName("com.example.lyy.entity.User");
        Method print = userClass.getDeclaredMethod("print", Object.class);
        Object o = "s";
        Object invoke = print.invoke(userClass.newInstance(), o);*/
        /*Human human = new Human();
        human.setSex("3");
        Human2 human2 = new Human2();
        PropertyUtils.copyProperties(human,human2);
        System.out.println(human2);
*/
        /*String str = "S_I_";

        for (int i = 1; i <= 6 - "255".length(); i++) {
            str += "0";
        }
        str += "255";
        System.out.println(str);*/
        /*String str = "S_I_000000";
        String substring = str.substring(4, 5);
        System.out.println(substring);*/

        /*Set<String> cp = FileUtil.readFileByLines("D:\\888.txt");
        Set<String> tb = FileUtil.readFileByLines("D:\\999.txt");
        cp.removeAll(tb);
        System.out.println(cp);*/
       /* InputStream aaaaaa = IOUtils.toInputStream("aaaaaa");
        FileInputStream fileInputStream = new FileInputStream("D:\\1.png");
        *//*String s = IOUtils.toString(fileInputStream, "GBK");
        System.out.println(s);
*//*
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,"ISO8859-1"));
        String line = null;
        while((line = bufferedReader.readLine()) != null)
        {

            stringBuilder.append(System.getProperty("line.separator"));

            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());*/

       /* Map<Integer,String> companyMap = new HashMap<>();
        companyMap.put(0,"0001300050");
        companyMap.put(1,"0001300052");
        companyMap.put(2,"0001300049");
        companyMap.put(3,"0001300033");
        companyMap.put(4,"0001300048");
        companyMap.put(5,"0001300051");
        Set<String> cp = FileUtil.readFileByLines("D:\\888.txt");
        Map<String,List<Integer>> dataMap = new HashMap<>();
        for (String s : cp) {
            String[] split = s.split(",");
            String code = split[1];
            List<Integer> integerList = searchAllIndex(code);
            dataMap.put(split[0],integerList);
        }
        List<String> sql = new ArrayList<>();
        dataMap.forEach((k,v) ->{
            List<Integer> list = v;
            for (Integer integer : list) {
                sql.add("UPDATE t_issue_log SET status = \'00\',create_date = NOW() WHERE application_form_code = \'"+k+"\' AND company_code LIKE \'"+companyMap.get(integer)+"%\';");
            }
        });
        FileUtil.write2LocalFold(sql,"D:\\999.txt");*/

       /* Set<String> cp = FileUtil.readFileByLines("D:\\aaa.txt");
        Set<String> cp2 = FileUtil.readFileByLines("D:\\bbb.txt");

        cp.removeAll(cp2);
        ArrayList<String> strings = new ArrayList<>(cp);
        FileUtil.write2LocalFold(strings,"D:\\999.txt");
        System.out.println(cp);*/
        /*List<String> list = new ArrayList<>();
        for (String s : cp) {
            if (s.contains(",")){
                String[] split = s.split(",");
                List<String> temp = new ArrayList<>();
                for (String s1 : split) {
                    temp.add(new String(s1.getBytes(),"utf-8"));
                }
                list.addAll(temp);
            }else{
                list.add(new String(s.getBytes(),"utf-8"));
            }
        }
        FileUtil.write2LocalFold(list,"D:\\999.txt");*/
        /*Set<String> set888 = FileUtil.readFileByLines("D:\\888.txt");
        Set<String> set999 = FileUtil.readFileByLines("D:\\999.txt");
        set999.removeAll(set888);
        System.out.println(set999);*/
        /*List<User> list1 = new ArrayList<>();
        List<User> list2 = new ArrayList<>();
        Date date = new Date();
        list1.add(User.builder().password("1111").username("1111").testDate(date).build());
        list1.add(User.builder().password("2222").username("2222").testDate(date).build());
        list2.add(User.builder().password("2222").username("2222").testDate(date).build());

        list2.add(User.builder().password("1111").username("1111").testDate(date).build());
        System.out.println("equals"+Objects.equals(list1,list2));
        System.out.println("deepEquals"+Objects.deepEquals(list1,list2));*/

       /* Set<String> set2 = FileUtil.readFileByLines("D:\\insprem.txt");
        Set<String> set3 = FileUtil.readFileByLines("D:\\ygdprem.txt");
        HashSet<String> objects2 = new HashSet<>(set2);
        objects2.retainAll(set3);
        FileUtil.write2LocalFold(objects2,"D:\\premdifference.txt");*/

//        List<String> set = FileUtil.readFileByLinesList("D:\\888.txt");
//        Map<String, List<String>> collect8 = set.stream().collect(Collectors.groupingBy(String::toString));
//        for (String s : collect8.keySet()) {
//            if (collect8.get(s).size()>1){
//                System.out.println(s +"-------"+ collect8.get(s).size());
//            }
//        }
//        Set<String> set999 = FileUtil.readFileByLines("D:\\999.txt");
//        set999.removeAll(set888);
//        System.out.println(set999);
//        int a=1;
//        int b= 2;

//        ClassPathResource classPathResource = new ClassPathResource("/static/11.ttf");
//        System.out.println(classPathResource.getPath());
//        File file = classPathResource.getFile();
//        System.out.println(file.getAbsolutePath());
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Beijing"));
//
//        String s = "{\"date\":\"1988-04-10 00:00:00\"}";
//        App app = JSONObject.parseObject(s, App.class);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(app.getDate());
//        System.out.println(format);
//        XMlToDocx.makeWord(null,"C:\\Users\\Administrator\\Desktop\\word","C:\\Users\\Administrator\\Desktop\\1",null);

        List<Long> list111 = new ArrayList<>();
        list111.add(1l);
        List<Long> list222 = new ArrayList<>();
        list222.add(1l);
        List<Long> collect8 = Stream.of(list111, list222).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect8));
        System.out.println(JSON.toJSON(list222).toString());
        Image image = Image.builder().name("a").build();
        Optional.ofNullable(image).ifPresent(item -> {
            System.out.println(item.getName());
        });

        List<App> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            App app = new App();
            app.setAppCode(String.valueOf(i));
            app.setOrderCode(String.valueOf(i));
            app.setAge(i);
            list.add(app);
        }
        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(App::getAppCode, counting()));
        Map<String, Double> collect1 = list.stream().collect(Collectors.groupingBy(App::getAppCode, averagingInt(App::getAge)));
        Map<Boolean, Map<String, Long>> collect2 = list.stream().collect(Collectors.partitioningBy(item -> item.getAge() > 3, groupingBy(App::getAppCode, counting())));
        String collect3 = list.stream().collect(mapping(App::getAppCode, joining(",")));
        String collect4 = list.stream().map(App::getAppCode).collect(Collectors.joining(","));
        list.stream().sorted(new Comparator<App>() {
            @Override
            public int compare(App o1, App o2) {
                return 0;
            }
        });
        List<Student> students = new ArrayList<>();
        students.add(new Student("Liucy" ,48 ,true , "C1" , 90));
        students.add(new Student("Wangyx" ,29 ,true , "C2" , 10));
        students.add(new Student("Zhangmj" ,27 ,false , "C1" ,80 ));
        students.add(new Student("Huxz" , 18, true, "C1" , 100));
        students.add(new Student("Yangdd" , 28, true, "C2" ,55 ));
        students.add(new Student("Zhuhj" , 31, false, "C3" , 90));
        students.add(new Student("Weisb" , 20, true, "C3" , 50));
        students.add(new Student("Suns" , 38, true, "C1" , 60));
        students.add(new Student("Luxw" , 19, false, "C2" , 45));
        students.add(new Student("Luxw" , 19, false, "C3" , 45));
        students.add(new Student("Luxw" , 19, false, "C3" , 45));
        Map<String,Map<Boolean,Student>> result = students.stream().collect(groupingBy(Student::getClassNumber,partitioningBy(Student::isMale,collectingAndThen(maxBy(comparing(Student::getScore)), Optional::get))));
        result.forEach((s,m)->{
            System.out.println(s);
            m.forEach((g,ss)->System.out.println(g+"---"+ss.getName()));
        });
        Map<String, Integer> collect6 = students.stream().collect(toMap(Student::getName, Student::getScore,(k1,k2) -> k1+k2));
        Map<String, List<Integer>> collect7 = students.stream().collect(toMap(Student::getName,
                item -> {
                    List<Integer> scores = new ArrayList<>(students.size());
                    scores.add(item.getScore());
                    return scores;
                },
                (List<Integer> value1, List<Integer> value2) -> {
                    value1.addAll(value2);
                    return value1;
                }
        ));
        Map<Boolean, List<Student>> collect5 = students.stream().collect(partitioningBy(item -> item.getAge() > 30));
        System.out.println(JSON.toJSON(collect));
        System.out.println(JSON.toJSON(collect1));
        System.out.println(JSON.toJSON(collect2));
        System.out.println(JSON.toJSON(collect3));
        System.out.println(JSON.toJSON(collect4));
        System.out.println(JSON.toJSON(collect5));
        System.out.println(JSON.toJSON(collect7));
//        System.out.println(JSON.toJSON(collect6));
        System.out.println(        AesUtil.decrypt("1212"));

        String type = "00";
        String s = "01".equals(type) ? "02" : ("02".equals(type) ? "01" : type);
        System.out.println(s);
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        System.out.println(JSON.toJSON(list1));


//        LinkedList<String> linkedList = new LinkedList<>();
//        List<String> list2 = FileUtil.readFileByLinesList("D:\\888.txt");
//        List<String> list3 = FileUtil.readFileByLinesList("D:\\999.txt");
//        list3.removeAll(list2);
//        for (String s1 : list3) {
//            System.out.println(s1);
//        }
        Set<String> set = FileUtil.readFileByLines("D:\\888.txt");
        Set<String> set1 = FileUtil.readFileByLines("D:\\999.txt");
        set1.removeAll(set);
        for (String s1 : set1) {
            System.out.println(s1);
        }


//        PathMatcher matcher = new AntPathMatcher();
//        boolean match = matcher.match("/user/select**", "/user/select？id=1");
//        System.out.println(match);


    }


    private static void create(){
        System.out.println("----");
    }

    private static List<Integer> searchAllIndex(String code) {
        List<Integer> list = new ArrayList<>();
        Integer a = code.indexOf("1");
        while (a != -1) {
            list.add(a);
            a = code.indexOf("1", a + 1);
        }
        return list;
    }

}

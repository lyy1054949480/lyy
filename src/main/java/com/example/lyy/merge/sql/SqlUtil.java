package com.example.lyy.merge.sql;

import com.example.lyy.merge.MyMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.JdbcType;
import org.apache.poi.ss.formula.functions.T;
import tk.mybatis.mapper.common.BaseMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class SqlUtil<T> {

    private static final int MaxBatchLength = 100;

    public static  <T> String getExecuteSql(List<T> list, BaseMapper<T> mapper) {

        if (!Proxy.isProxyClass(mapper.getClass())) {
            throw new RuntimeException("mapper必须是代理对象");
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(mapper);
        if (null == invocationHandler) {
            throw new RuntimeException("mapper必须是有处理器的代理对象");
        }
        Field fieldSession;
        try {
            fieldSession = invocationHandler.getClass().getDeclaredField("sqlSession");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("从mapper代理对象中获取不到sqlSession", e);
        }
        Field fieldMapper;
        try {
            fieldMapper = invocationHandler.getClass().getDeclaredField("mapperInterface");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("从mapper代理对象中获取不到mapperInterface", e);
        }
        fieldSession.setAccessible(true);
        SqlSession session;
        try {
            session = (SqlSession) fieldSession.get(invocationHandler);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("从mapper代理对象中获取sqlSession失败，不应该出现此异常", e);
        }
        fieldMapper.setAccessible(true);
        Class<?> mapperInterface;
        try {
            mapperInterface = (Class<?>) fieldMapper.get(invocationHandler);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("从mapper代理对象中获取mapperInterface失败，不应该出现此异常", e);
        }
        // 方法名(mybatis的对应xml中的sql语句的id)
        String methodName = mapperInterface.getName() + ".updateEntityBatch";
        System.out.println("获取方法的SQL：" + methodName);
        //传递参数保证，要更新的字段存在（若没有判空，则可以不用传递参数）
        BoundSql boundSql = session.getConfiguration().getMappedStatement(methodName).getBoundSql(list.get(0));

        //是否是独立的事务
        boolean atmo = true, succ = false;
        System.out.println("每次批量执行最大长度为：" + MaxBatchLength);

        //获取批量执行的sql
        String sql = boundSql.getSql();
//        //获取连接
//        Connection connection = null;
//        PreparedStatement ps = null;
//        List<Closeable> closeables = new LinkedList<>();
//        try {
//            connection = session.getConnection();
//            if (atmo = null == connection || connection.isClosed()) {
//                DataSource dataSource = session.getConfiguration().getEnvironment().getDataSource();
//                connection = dataSource.getConnection();
//                //事务不自动提交
//                connection.setAutoCommit(false);
//                System.out.println("session中的连接不可使用，使用独立的连接和事务");
//            } else {
//                System.out.println("使用session的连接，事务和session保持一致");
//            }
//
//            ps = connection.prepareStatement(sql);
//
//            int index = 0;
//            System.out.println("需要批量更新"+list.size()+"个对象");
//
//            for (int i = 0, j = list.size(); i < j; i++, index++) {
//                T t = list.get(i);
//                //将实体类转换为map
//                BeanMap map = BeanMap.create(t);
//                System.out.println("绑定对象:"+ map);
//                for (int ii = 1, jj = boundSql.getParameterMappings().size(); ii <= jj; ii++) {
//                    ParameterMapping parameterMapping = boundSql.getParameterMappings().get(ii - 1);
//                    String name = parameterMapping.getProperty();
//                    Object value = map.get(name);
//                    if (null == value) {
//                        // 为空时候尝试取默认值
//                        value = map.get(name + "Default");
//                    }
//                    if (null != value && value instanceof Date) {
//                        Timestamp date = new Timestamp(((Date) value).getTime());
//                        value = date;
//                    }
//                    // 单独处理clob类型
//                    if (JdbcType.CLOB.equals(parameterMapping.getJdbcType())) {
//                        StringReader sr = new StringReader(null == value ? "" : value.toString());
//                        ps.setClob(ii, sr);
//                        closeables.add(sr);
//                    } else {
//                        ps.setObject(ii, value, parameterMapping.getJdbcType().TYPE_CODE);
//                    }
//                }
//                ps.addBatch();
//                if (index > MaxBatchLength) {
//                    ps.executeBatch();
//                    ps.clearBatch();
//                    index = 0;
//                }
//            }
//            if (index > 0) {
//                //执行剩下的
//                ps.executeBatch();
//            }
//            succ = true;
//        }catch (Exception e){
//            throw new RuntimeException("批量更新失败",e);
//        }finally {
//            // 如果是独立的事务
//            if (atmo && null != connection) {
//                log.info("检测到独立事务，判断提交/回滚");
//                if (succ) {
//                    try {
//                        connection.commit();
//                        log.info("独立事务提交成功");
//                    } catch (SQLException e) {
//                        log.info("独立事务提交失败");
//                        throw new RuntimeException(e);
//                    }
//                } else {
//                    try {
//                        connection.rollback();
//                        log.info("独立事务回滚成功");
//                    } catch (SQLException e) {
//                        log.info("独立事务回滚失败");
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//            if (null != ps) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (atmo && null != connection) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            for (Closeable closeable : closeables) {
//                try {
//                    closeable.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        return sql;

    }
}

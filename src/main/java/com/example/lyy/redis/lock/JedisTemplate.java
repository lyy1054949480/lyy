package com.example.lyy.redis.lock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTemplate {

    public static final String IP_ADDRESS = "127.0.0.1";
    /**
     * 端口号
     */
    public static final int PORT = 6379;


    public static Jedis operate(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxIdle(50);
        poolConfig.setMaxWaitMillis(1000);
        JedisPool jedisPool = new JedisPool(poolConfig, IP_ADDRESS, PORT);
        return jedisPool.getResource();
    }
}

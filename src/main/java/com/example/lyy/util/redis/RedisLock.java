package com.example.lyy.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Protocol;
import redis.clients.util.SafeEncoder;

import java.io.Serializable;

@Component
public class RedisLock {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     *
     * @param key
     * @param value
     * @param second (second)
     * @return
     */
    public boolean setIfAbsent(final String key, final Serializable value, final long second) {
        Boolean b = (Boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(org.springframework.data.redis.connection.RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
                RedisSerializer keySerializer = redisTemplate.getKeySerializer();
                Object obj = redisConnection.execute("set", keySerializer.serialize(key),
                        valueSerializer.serialize(value),
                        SafeEncoder.encode("NX"),
                        SafeEncoder.encode("EX"),
                        Protocol.toByteArray(second));
                return obj != null;
            }
        });
        return b;
    }
}

package com.example.lyy.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisReadLock {
    public void lock(String name){
        tryLock(name, Long.MAX_VALUE, 30, TimeUnit.SECONDS);
    }
    
    public void lock(String name, long leaseTime, TimeUnit unit){
        tryLock(name, Long.MAX_VALUE, leaseTime, unit);
    }

    public boolean tryLock(String name, long waitTime, long leaseTime, TimeUnit unit){
        Long waitUntilTime = unit.toMillis(waitTime) + System.currentTimeMillis();
        if(waitUntilTime < 0){
            waitUntilTime = Long.MAX_VALUE;
        }
        Long leastTimeLong = unit.toMillis(leaseTime);
        StringBuilder sctipt = new StringBuilder();
        
        // write-lock read-lock uuid leaseTime，后面会专门说这段脚本
        sctipt.append("if not redis.call('GET',KEYS[1]) then ")
                     //redis.call('GET',KEYS[1])之类的命令，若没有值返回的布尔类型的false，不是nil
                    .append("local count = redis.call('HGET',KEYS[2],KEYS[3]);")
                    .append("if count then ")
                        .append("count = tonumber(count) + 1;")
                        .append("redis.call('HSET',KEYS[2],KEYS[3],count);")
                    .append("else ")
                        .append("redis.call('HSET',KEYS[2],KEYS[3],1);")
                    .append("end;")
                    .append("local t = redis.call('PTTL', KEYS[2]);")
                    .append("redis.call('PEXPIRE', KEYS[2], math.max(t, ARGV[1]));")
                    .append("return 1;")
                .append("else ")
                     .append("return 0;")
                .append("end;");
        for(;;){
            if(System.currentTimeMillis() > waitUntilTime){
                return false;
            }
            Long res = (Long) JedisTemplate.operate().eval(sctipt.toString(), 3, RedisReadWriteLock.getWriteLockKey(name), RedisReadWriteLock.getReadLockKey(name), RedisReadWriteLock.getThreadUid(), leastTimeLong.toString());
            if(res.equals(1L)){
                //successGetReadLock
                log.debug("success get read lock,  readLock={}", RedisReadWriteLock.getReadLockKey(name));
                break;
            }else {
                //need to wait write lock to be released
                log.debug("wait write lock release,  writeLock={}", RedisReadWriteLock.getWriteLockKey(name));
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    log.error("wait write lock release exception", e);
                }
            }
        }
        return true;
    }

    public void unlock(String name){
        StringBuilder sctipt = new StringBuilder();
        sctipt.append("local count = redis.call('HGET',KEYS[1],KEYS[2]);")
                .append("if count then ")
                    .append("if (tonumber(count) > 1) then ")
                        .append("count = tonumber(count) - 1;")
                        .append("redis.call('HSET',KEYS[1],KEYS[2],count);")
                    .append("else ")
                      .append("redis.call('HDEL',KEYS[1],KEYS[2]);")
                    .append("end;")
                .append("end;")
                .append("return;");
        JedisTemplate.operate().eval(sctipt.toString(), 2, RedisReadWriteLock.getReadLockKey(name), RedisReadWriteLock.getThreadUid());
        log.debug("success unlock read lock, readLock={}", RedisReadWriteLock.getReadLockKey(name));
    }

}

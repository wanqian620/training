package com.whstone.common.feign.fallback;

import com.whstone.common.feign.RedisClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RedisFallbackFactory implements FallbackFactory<RedisClient> {
    @Override
    public RedisClient create(final Throwable cause) {
        return new RedisClient() {
            @Override
            public boolean expire(String key, long time) {
                return false;
            }

            @Override
            public long getExpire(String key) {
                return 0;
            }

            @Override
            public boolean hasKey(String key) {
                return false;
            }

            @Override
            public void delKey(String key) {

            }

            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public boolean set(String key, String value) {
                return false;
            }

            @Override
            public boolean setWithTime(String key, String value, long time) {
                return false;
            }

            @Override
            public long incr(String key, long delta) {
                return 0;
            }

            @Override
            public long decr(String key, long delta) {
                return 0;
            }

            @Override
            public String hget(String key, String item) {
                return null;
            }

            @Override
            public boolean hset(String key, String item, String value) {
                return false;
            }

            @Override
            public boolean hsetWithTime(String key, String item, Object value, long time) {
                return false;
            }

            @Override
            public void hdel(String key, String item) {

            }

            @Override
            public boolean hHasKey(String key, String item) {
                return false;
            }

            @Override
            public double hincr(String key, String item, double by) {
                return 0;
            }

            @Override
            public double hdecr(String key, String item, double by) {
                return 0;
            }

            @Override
            public Set<Object> sGet(String key) {
                return null;
            }

            @Override
            public long sSet(String key, Object value) {
                return 0;
            }

            @Override
            public long sSetAndTime(String key, long time, Object value) {
                return 0;
            }

            @Override
            public boolean isMember(String key, String value) {
                return false;
            }

            @Override
            public long sGetSetSize(String key) {
                return 0;
            }

            @Override
            public Set<Object> setDiff(String key1, String key2) {
                return null;
            }

            @Override
            public long setRemove(String key, Object value) {
                return 0;
            }

            @Override
            public List<Object> lGet(String key, long start, long end) {
                return null;
            }

            @Override
            public long lGetListSize(String key) {
                return 0;
            }

            @Override
            public boolean lrSet(String key, String value) {
                return false;
            }

            @Override
            public boolean lrSetTime(String key, Object value, long time) {
                return false;
            }

            @Override
            public boolean llSet(String key, Object value) {
                return false;
            }

            @Override
            public boolean llSetTime(String key, Object value, long time) {
                return false;
            }

            @Override
            public long lRemove(String key, long count, Object value) {
                return 0;
            }

            @Override
            public String lPop(String key) {
                return null;
            }

            @Override
            public String rPop(String key) {
                return null;
            }

            @Override
            public Set<Object> distinctRandomMembers(String key, Long count) {
                return null;
            }

            @Override
            public boolean zSet(String key, String value, double number) {
                return false;
            }

            @Override
            public Set<Object> zGet(String key, long start, long end) {
                return null;
            }

            @Override
            public Set<Object> rangeByScore(String key, double min, double max) {
                return null;
            }

            @Override
            public Set<Object> rangeByScoreLimit(String key, double min, double max, long offset, long count) {
                return null;
            }

            @Override
            public long zRemove(String key, String value) {
                return 0;
            }

            @Override
            public Set<Object> reverseRange(String key, long start, long end) {
                return null;
            }

            @Override
            public Set<Object> reverseRangeByScore(String key, double min, double max) {
                return null;
            }

            @Override
            public Set<Object> reverseRangeByScoreLimit(String key, double min, double max, long offset, long count) {
                return null;
            }

            @Override
            public long zCount(String key, double min, double max) {
                return 0;
            }
        };
    }
}

package com.whstone.redis.controller;

import com.whstone.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Api(value = "RedisController", tags = "Redis服务")
@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    /* TODO ----------------------------------------    Key相关操作    --------------------------------------------------- */

    @PostMapping("/expire")
    @ApiOperation("指定缓存失效时间")
    public boolean expire(String key, long time) {
        return redisService.expire(key, time);
    }

    @GetMapping("/getExpire")
    @ApiOperation("根据key 获取过期时间")
    public long getExpire(String key) {
        return redisService.getExpire(key);
    }


    @GetMapping("/hasKey")
    @ApiOperation("判断key是否存在")
    public boolean hasKey(String key) {
        return redisService.hasKey(key);
    }

    @DeleteMapping("/delKey")
    @ApiOperation("删除key")
    public void delKey(String key) {
        redisService.del(key);
    }




    /* TODO ----------------------------------------    String 相关操作    --------------------------------------------------- */

    @GetMapping("/get")
    @ApiOperation("获取普通缓存")
    public String get(String key) {
        return redisService.get(key);
    }

    @PostMapping("/set")
    @ApiOperation("存储普通缓存")
    public boolean set(String key, String value) {
        return redisService.set(key, value);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    @PostMapping("/setWithTime")
    @ApiOperation("存储普通缓存并设置过期时间")
    public boolean setWithTime(String key, String value, long time) {
        return redisService.set(key, value, time);
    }

    /**
     * 递增
     *
     * @param delta 要增加几(大于0)
     */
    @PostMapping("/incr")
    @ApiOperation("递增")
    public long incr(String key, long delta) {
        return redisService.incr(key, delta);
    }

    /**
     * 递减
     *
     * @param delta 要减少几(大于0)
     * @return
     */
    @PostMapping("/decr")
    @ApiOperation("递减")
    public long decr(String key, long delta) {
        return redisService.decr(key, delta);
    }

    /* TODO ----------------------------------------    Hash 相关操作    --------------------------------------------------- */

    /**
     * HashGet
     */
    @GetMapping("/hget")
    @ApiOperation("HashGet")
    public Object hget(String key, String item) {
        return redisService.hget(key, item);
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @return true 成功 false失败
     */
    @PostMapping("/hset")
    @ApiOperation("HashSet")
    public boolean hset(String key, String item, String value) {
        return redisService.hset(key, item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    @PostMapping("/hsetWithTime")
    @ApiOperation("HashSetWithTime")
    public boolean hsetWithTime(String key, String item, Object value, long time) {
        return redisService.hset(key, item, value, time);
    }


    @DeleteMapping("/hdel")
    @ApiOperation("删除hash表中的值")
    public void hdel(String key, String item) {
        redisService.hdel(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @return true 存在 false不存在
     */
    @GetMapping("/hHasKey")
    @ApiOperation("判断hash表中是否有该项的值")
    public boolean hHasKey(String key, String item) {
        return redisService.hHasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @return
     */
    @PostMapping("/hincr")
    @ApiOperation("hash递增 如果不存在,就会创建一个")
    public double hincr(String key, String item, double by) {
        return redisService.hincr(key, item, by);
    }


    @PostMapping("/hdecr")
    @ApiOperation("hash递减 如果不存在,就会创建一个")
    public double hdecr(String key, String item, double by) {
        return redisService.hdecr(key, item, by);
    }

    /* TODO ----------------------------------------    Set 相关操作    --------------------------------------------------- */

    @GetMapping("/sGet")
    @ApiOperation("根据key获取Set中的所有值")
    public Set<Object> sGet(String key) {
        return redisService.sGet(key);
    }

    /**
     * 将数据放入set缓存
     */
    @GetMapping("/sSetValue")
    @ApiOperation("将数据放入set缓存")
    public long sSet(String key, Object value) {
        return redisService.sSet(key, value);
    }

    /**
     * 将set数据放入缓存
     *
     * @return 成功个数
     */
    @PostMapping("/sSetAndTime")
    @ApiOperation("将数据放入set缓存")
    public long sSetAndTime(String key, long time, Object value) {
        return redisService.sSetAndTime(key, time, value);
    }

    @GetMapping("/isMember")
    @ApiOperation("Set是否存在该值")
    public boolean isMember(String key, String value) {
        return redisService.exists(key, value);
    }


    @GetMapping("/sGetSetSize")
    @ApiOperation("获取set缓存的长度")
    public long sGetSetSize(String key) {
        return redisService.sGetSetSize(key);
    }


    @GetMapping("/setDiff")
    @ApiOperation("取差集")
    public Set<Object> setDiff(String key1, String key2) {
        return redisService.setDiff(key1, key2);
    }


    @DeleteMapping("/setRemove")
    @ApiOperation("移除集合中的值")
    public long setRemove(String key, Object value) {
        return redisService.setRemove(key, value);
    }

    @ApiOperation("获取指定个数的随机元素")
    @GetMapping("/distinctRandomMembers")
    public Set<Object> distinctRandomMembers(String key, Long count) {
        return redisService.distinctRandomMembers(key, count);
    }

    /* TODO ----------------------------------------    List 相关操作    --------------------------------------------------- */

    @ApiOperation("获取list缓存的内容")
    @GetMapping("/lGet")
    public List<Object> lGet(String key, long start, long end) {
        return redisService.lGet(key, start, end);
    }


    @ApiOperation("获取list缓存的长度")
    @GetMapping("/lGetListSize")
    public long lGetListSize(String key) {
        return redisService.lGetListSize(key);
    }

    @ApiOperation("list尾部放入一个元素")
    @PostMapping("/lrSet")
    public boolean lrSet(String key, String value) {
        return redisService.lrSet(key, value);
    }

    @ApiOperation("list尾部放入一个元素并设计过期时间")
    @PostMapping("/lrSetTime")
    public boolean lrSetTime(String key, Object value, long time) {
        return redisService.lrSetTime(key, value, time);
    }

    @ApiOperation("list头部放入一个元素")
    @PostMapping("/llSet")
    public boolean llSet(String key, Object value) {
        return redisService.llSet(key, value);
    }

    @ApiOperation("list头部放入一个元素并设置过期时间")
    @PostMapping("/llSetTime")
    public boolean llSetTime(String key, Object value, long time) {
        return redisService.llSetTime(key, value, time);
    }


    @ApiOperation("移除,count标识删除相同的个数")
    @DeleteMapping("/lRemove")
    public long lRemove(String key, long count, Object value) {
        return redisService.lRemove(key, count, value);
    }


    @ApiOperation("删除key头部的第一个元素")
    @DeleteMapping("/lPop")
    public String lPop(String key) {
        return redisService.lPop(key);
    }


    @ApiOperation("删除key尾部的第一个元素")
    @DeleteMapping("/rPop")
    public String rPop(String key) {
        return redisService.rPop(key);
    }


    /* TODO ----------------------------------------    ZSet 相关操作    --------------------------------------------------- */


    @ApiOperation("存储zset集合")
    @PostMapping("/zSet")
    public boolean zSet(String key, String value, double number) {
        return redisService.zSet(key, value, number);
    }

    @ApiOperation("查询集合的值,从小到大")
    @GetMapping("/zGet")
    public Set<Object> zGet(String key, long start, long end) {
        return redisService.zGet(key, start, end);
    }

    @ApiOperation("查询集合中某个范围的值,从小到大")
    @GetMapping("/rangeByScore")
    public Set<Object> rangeByScore(String key, double min, double max) {
        return redisService.rangeByScore(key, min, max);
    }

    @ApiOperation("分页查询集合中某个范围的值,从小到大")
    @GetMapping("/rangeByScoreLimit")
    public Set<Object> rangeByScoreLimit(String key, double min, double max, long offset, long count) {
        return redisService.rangeByScore(key, min, max, offset, count);
    }

    @ApiOperation("删除zSet集合中的值")
    @DeleteMapping("/zRemove")
    public long zRemove(String key, String value) {
        return redisService.zRemove(key, value);
    }

    @ApiOperation("查询集合中的值,从大到小")
    @GetMapping("/reverseRange")
    public Set<Object> reverseRange(String key, long start, long end) {
        return redisService.reverseRange(key, start, end);
    }

    @ApiOperation("查询集合中某个范围的值,从大到小")
    @GetMapping("/reverseRangeByScore")
    public Set<Object> reverseRangeByScore(String key, double min, double max) {
        return redisService.reverseRangeByScore(key, min, max);
    }

    @ApiOperation("分页查询集合中某个范围的值,从大到小")
    @GetMapping("/reverseRangeByScoreLimit")
    public Set<Object> reverseRangeByScoreLimit(String key, double min, double max, long offset, long count) {
        return redisService.reverseRangeByScore(key, min, max, offset, count);
    }

    @ApiOperation("查当前Key在范围内有几个值")
    @GetMapping("/zCount")
    public long zCount(String key, double min, double max) {
        return redisService.zCount(key, min, max);
    }
}

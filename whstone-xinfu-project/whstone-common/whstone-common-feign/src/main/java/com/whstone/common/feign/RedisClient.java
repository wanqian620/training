package com.whstone.common.feign;

import com.whstone.common.feign.fallback.RedisFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name="whstone-redis-server",fallbackFactory = RedisFallbackFactory.class)
public interface RedisClient {

    /* TODO ----------------------------------------    KEY 相关操作    --------------------------------------------------- */
    /**
     * 设置KEY放入时间
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    @RequestMapping(value = "/redis/expire",method = RequestMethod.POST)
    boolean expire(@RequestParam("key") String key, @RequestParam("time") long time);

    /**
     * 后去当前key失效时间
     */
    @RequestMapping(value = "/redis/getExpire",method = RequestMethod.GET)
    long getExpire(@RequestParam("key") String key);

    /**
     * 判断KEY 是否存在
     */
    @RequestMapping(value = "/redis/hasKey",method = RequestMethod.GET)
    boolean hasKey(@RequestParam("key") String key);

    /**
     * 删除KEY
     */
    @RequestMapping(value = "/redis/delKey",method = RequestMethod.DELETE)
    void delKey(@RequestParam("key") String key);




    /* TODO ----------------------------------------    String 相关操作    --------------------------------------------------- */

    /**
     * 获取KEY的值
     */
    @RequestMapping(value = "/redis/get",method = RequestMethod.GET)
    String get(@RequestParam("key") String key);

    /**
     * 存储KEY的值
     */
    @RequestMapping(value = "/redis/set",method = RequestMethod.POST)
    boolean set(@RequestParam("key") String key, @RequestParam("value") String value);

    /**
     * 普通缓存放入并设置时间
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    @RequestMapping(value = "/redis/setWithTime",method = RequestMethod.POST)
    boolean setWithTime(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("time") long time);

    /**
     * 递增
     * @param delta  要增加几(大于0)
     */
    @RequestMapping(value = "/redis/incr",method = RequestMethod.POST)
    long incr(@RequestParam("key") String key, @RequestParam("delta") long delta);

    /**
     * 递减
     * @param delta 要减少几(大于0)
     * @return
     */
    @RequestMapping(value = "/redis/decr",method = RequestMethod.POST)
    long decr(@RequestParam("key") String key, @RequestParam("delta") long delta);


    /* TODO ----------------------------------------    Hash 相关操作    --------------------------------------------------- */

    /**
     * HashGet
     */
    @RequestMapping(value = "/redis/hget",method = RequestMethod.GET)
    String hget(@RequestParam("key") String key,@RequestParam("item")String item);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @return true 成功 false失败
     */
    @RequestMapping(value = "/redis/hset",method = RequestMethod.POST)
    boolean hset(@RequestParam("key") String key,@RequestParam("item") String item, @RequestParam("value") String value);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param time
     *            时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    @RequestMapping(value = "/redis/hsetWithTime",method = RequestMethod.POST)
    boolean hsetWithTime(@RequestParam("key") String key,@RequestParam("item") String item,@RequestParam("value") Object value,@RequestParam("time") long time);

    /**
     * 将数据从缓存删除
     */
    @RequestMapping(value = "/redis/hdel",method = RequestMethod.DELETE)
    void hdel(@RequestParam("key") String key,@RequestParam("item") String item);

    /**
     * 判断hash表中是否有该项的值
     * @return true 存在 false不存在
     */
    @RequestMapping(value = "/redis/hHasKey",method = RequestMethod.GET)
    boolean hHasKey(@RequestParam("key") String key,@RequestParam("item") String item);

    /**
     * hash递增
     * @return
     */
    @RequestMapping(value = "/redis/hincr",method = RequestMethod.POST)
   double hincr(@RequestParam("key") String key,@RequestParam("item") String item,@RequestParam("by") double by);

    /**
     * hash递减
     * @return 成功个数
     */
    @RequestMapping(value = "/redis/hdecr",method = RequestMethod.POST)
    double hdecr(@RequestParam("key") String key, @RequestParam("item") String item,@RequestParam("by") double by);

    /* TODO ----------------------------------------    Set 相关操作    --------------------------------------------------- */

    /**
     * 获取SET集合
     * @return 成功个数
     */
    @RequestMapping(value = "/redis/sGet",method = RequestMethod.GET)
    Set<Object> sGet(@RequestParam("key") String key);

    /**
     * 将数据放入set缓存
     */
    @RequestMapping(value = "/redis/sSetValue",method = RequestMethod.POST)
    long sSet(@RequestParam("key")  String key,@RequestParam("value") Object value);

    /**
     * 将set数据放入缓存并设置过期时间
     * @return 成功个数
     */
    @RequestMapping(value = "/redis/sSetAndTime",method = RequestMethod.POST)
    long sSetAndTime(@RequestParam("key") String key,@RequestParam("time") long time,@RequestParam("value") Object value);

    /**
     * 判断该值是否在集合中存在
     *
     */
    @RequestMapping(value = "/redis/isMember",method = RequestMethod.GET)
    boolean isMember(@RequestParam("key") String key,@RequestParam("value")  String value);

    /**
     * 查看集合的大小
     */
    @RequestMapping(value = "/redis/sGetSetSize",method = RequestMethod.GET)
   long sGetSetSize(@RequestParam("key") String key);

    /**
     * 求两个集合的差集
     */
    @RequestMapping(value = "/redis/setDiff",method = RequestMethod.GET)
    Set<Object> setDiff(@RequestParam("key1") String key1,@RequestParam("key2") String key2);

    /**
     * 删除集合中的某个值
     */
    @RequestMapping(value = "/redis/setRemove",method = RequestMethod.DELETE)
    long setRemove(@RequestParam("key") String key,@RequestParam("value") Object value);

    /* TODO ----------------------------------------    List 相关操作    --------------------------------------------------- */

    /**
     * 获取集合值
     * @param key
     * @param start 开始下标
     * @param end   结束下标
     * @return
     */
    @RequestMapping(value = "/redis/lGet",method = RequestMethod.GET)
    List<Object> lGet(@RequestParam("key") String key,@RequestParam("start") long start,@RequestParam("end") long end);

    /**
     * 获取集合的长度
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/lGetListSize",method = RequestMethod.GET)
    long lGetListSize(@RequestParam("key") String key);

    /**
     * 从集合的尾部进行插入
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/lrSet",method = RequestMethod.POST)
    boolean lrSet(@RequestParam("key") String key,@RequestParam("value") String value);

    /**
     * 从集合的尾部进行插入并设置过期时间
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/lrSetTime",method = RequestMethod.POST)
    boolean lrSetTime(@RequestParam("key") String key,@RequestParam("value") Object value,@RequestParam("time") long time);

    /**
     * 从集合的头部进行插入
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/llSet",method = RequestMethod.POST)
    boolean llSet(@RequestParam("key") String key,@RequestParam("value")  Object value);

    /**
     * 从集合的头部进行插入并设置过期时间
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/llSetTime",method = RequestMethod.POST)
    boolean llSetTime(@RequestParam("key") String key,@RequestParam("value") Object value,@RequestParam("time") long time);

    /**
     * 移除,count标识删除相同的个数
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/lRemove",method = RequestMethod.DELETE)
    long lRemove(@RequestParam("key") String key,@RequestParam("count") long count,@RequestParam("value") Object value);

    /**
     * 删除key头部的第一个元素
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/lPop",method = RequestMethod.DELETE)
    String lPop(@RequestParam("key") String key);

    /**
     * 删除key尾部的第一个元素
     * @param key
     * @return
     */
    @RequestMapping(value = "/redis/rPop",method = RequestMethod.DELETE)
    String rPop(@RequestParam("key") String key);

    /**
     * 获取指定个数的随机元素
     * @param key
     * @param count
     * @return
     */
    @RequestMapping(value = "/redis/distinctRandomMembers",method = RequestMethod.GET)
    Set<Object> distinctRandomMembers(@RequestParam("key") String key,@RequestParam("count") Long count);

    /**
     * 存zSet集合
     * @param key
     * @param value
     * @param number
     * @return
     */
    @PostMapping("/zSet")
    boolean zSet(@RequestParam("key")String key,@RequestParam("value") String value,@RequestParam("number") double number);

    @RequestMapping(value = "/redis/zGet",method = RequestMethod.GET)
    Set<Object> zGet(@RequestParam("key")String key,@RequestParam("start")long start,@RequestParam("end") long end);

    @RequestMapping(value = "/redis/rangeByScore",method = RequestMethod.GET)
    Set<Object> rangeByScore(@RequestParam("key")String key,@RequestParam("min") double min,@RequestParam("max") double max);

    @RequestMapping(value = "/redis/rangeByScoreLimit",method = RequestMethod.GET)
    Set<Object> rangeByScoreLimit(@RequestParam("key")String key,@RequestParam("min") double min,@RequestParam("max") double max,
                                  @RequestParam("offset")long offset,@RequestParam("count") long count);

    @RequestMapping(value = "/redis/zRemove",method = RequestMethod.DELETE)
    long zRemove(@RequestParam("key")String key,@RequestParam("value")String value);

    @RequestMapping(value = "/redis/reverseRange",method = RequestMethod.GET)
    Set<Object> reverseRange(@RequestParam("key")String key,@RequestParam("start")long start,@RequestParam("end") long end);

    @RequestMapping(value = "/redis/reverseRangeByScore",method = RequestMethod.GET)
    Set<Object> reverseRangeByScore(@RequestParam("key")String key,@RequestParam("min") double min,@RequestParam("max") double max);

    @RequestMapping(value = "/redis/reverseRangeByScoreLimit",method = RequestMethod.GET)
    Set<Object> reverseRangeByScoreLimit(@RequestParam("key")String key,@RequestParam("min") double min,@RequestParam("max") double max,
                                         @RequestParam("offset")long offset,@RequestParam("count") long count);

    @RequestMapping(value = "/redis/zCount",method = RequestMethod.GET)
    long zCount(@RequestParam("key")String key,@RequestParam("min") double min,@RequestParam("max") double max);
}

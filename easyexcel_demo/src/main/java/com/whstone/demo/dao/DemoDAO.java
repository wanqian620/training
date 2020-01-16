package com.whstone.demo.dao;

import com.alibaba.fastjson.JSON;
import com.whstone.demo.entity.DemoData;

import java.util.List;

/**
 * @Author: wangzh
 * @Date: 2020/1/12
 */
public class DemoDAO {

    public <T> void save(List<T> list) {
        //处理数据
        System.out.println(JSON.toJSONString(list));
    }
}

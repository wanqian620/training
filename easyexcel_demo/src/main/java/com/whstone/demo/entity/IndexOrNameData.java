package com.whstone.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: wangzh
 * @Date: 2020/1/12
 */
@Data
public class IndexOrNameData {
    /**
     * 强制读取第三个 这里不建议 index（代表第几列从0开始） 和 name 同时用，要么一个对象只用index，要么一个对象只用name去匹配
     */
    @ExcelProperty(index = 0)
    private String name;
    /**
     * 用名字去匹配，这里需要注意，如果名字重复，会导致只有一个字段读取到数据
     */
    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty(index = 2)
    private String address;
}

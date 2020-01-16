package com.whstone.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wangzh
 * @Date: 2020/1/12
 */
@Data
public class DemoData {

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("地址")
    private String address;

    private String address1;




}

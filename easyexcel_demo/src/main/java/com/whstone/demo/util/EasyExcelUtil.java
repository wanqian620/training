package com.whstone.demo.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @Author: wangzh
 * @Date: 2020/1/13
 */
public class EasyExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EasyExcelUtil.class);

    /**
     * 简单的读取excel
     *
     * @param inputStream 文件输入流
     * @return
     */
    public static List<Object> simpleRead(InputStream inputStream) {
        EasyExcelListener<Object> listener = new EasyExcelListener<>();
        EasyExcel.read(inputStream, listener).sheet().doRead();
        return listener.getData();
    }

    /**
     * 简单的读取excel,根据模型读取
     *
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> List<E> simpleRead(Class<E> clazz, InputStream inputStream) {
        EasyExcelListener<E> listener = new EasyExcelListener<>();
        EasyExcel.read(inputStream, clazz, listener).sheet().doRead();
        return listener.getData();
    }

    /**
     * 读取所有的sheet
     *
     * @param inputStream
     * @return
     */
    public static List<Object> repeatedRead(InputStream inputStream) {
        EasyExcelListener<Object> listener = new EasyExcelListener<>();
        // 这里需要注意监听器的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
        EasyExcel.read(inputStream, listener).doReadAll();
        return listener.getData();
    }

    /**
     * 根据sheet索引读取，索引从0开始
     *
     * @param inputStream
     * @param sheetIndex
     * @return
     */
    public static List<Object> readBySheetIndex(InputStream inputStream, Integer sheetIndex) {
        EasyExcelListener<Object> listener = new EasyExcelListener<>();
        EasyExcel.read(inputStream, listener).sheet(sheetIndex).doRead();
        return listener.getData();
    }

    /**
     * 根据sheet索引读取，索引从0开始
     *
     * @param clazz
     * @param inputStream
     * @param sheetIndex
     * @param <E>
     * @return
     */
    public static <E> List<E> readBySheetIndex(Class<E> clazz, InputStream inputStream, Integer sheetIndex) {
        EasyExcelListener<E> listener = new EasyExcelListener<>();
        EasyExcel.read(inputStream, clazz, listener).sheet(sheetIndex).doRead();
        return listener.getData();
    }


    /**
     * 简单的写，通过Class设置头信息
     *
     * @param outputStream 输出流
     * @param sheetName    sheet的名称
     * @param data         写入的数据
     * @param headClass    头信息（根据该头信息去匹配）
     * @param <T>
     */
    public static <T> void simpleWrite(OutputStream outputStream, String sheetName,
                                       List<T> data, Class<T> headClass) {
        EasyExcel.write(outputStream, headClass).sheet(sheetName).doWrite(data);
    }

    /**
     * 简单的写，动态设置头信息
     *
     * @param outputStream 输出流
     * @param sheetName    sheet的名称
     * @param data         写入的数据
     * @param head         头信息
     */
    public static void simpleWrite(OutputStream outputStream, String sheetName,
                                   List<?> data, List<List<String>> head) {
        EasyExcel.write(outputStream)
                // 这里放入动态头
                .head(head).sheet(sheetName)
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(data);
    }

    /**
     * 不同模型导出到不同的sheet
     *
     * @param outputStream 输出流
     * @param data         K:导出数据的class V：导出数据
     */
    public static void repeatedWrite(OutputStream outputStream, Map<Class<?>, List<?>> data) {
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
        int sheetIndex = 1;
        for (Map.Entry<Class<?>, List<?>> classListEntry : data.entrySet()) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetIndex).head(classListEntry.getKey()).build();
            excelWriter.write(classListEntry.getValue(), writeSheet);
            sheetIndex++;
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 根据模板导入数据
     *
     * @param outputStream 输出流
     * @param inputStream  模板输入流
     * @param data         数据
     * @param headClass
     * @param sheetIndex
     * @param <T>
     */
    public static <T> void templateWrite(OutputStream outputStream, InputStream inputStream,
                                         List<T> data, Class<T> headClass,
                                         Integer sheetIndex) {
        EasyExcel.write(outputStream, headClass)
                .withTemplate(inputStream)
                .sheet(sheetIndex).doWrite(data);
    }

    /**
     * 排除某些字段进行导入
     *
     * @param outputStream            输出流
     * @param sheetName               sheet名称
     * @param data                    导入数据
     * @param headClass               头字段
     * @param excludeColumnFiledNames 排除字段集合
     * @param <T>
     */
    public static <T> void excludeWrite(OutputStream outputStream, String sheetName,
                                        List<T> data, Class<T> headClass,
                                        Set<String> excludeColumnFiledNames) {
        EasyExcel.write(outputStream, headClass).
                excludeColumnFiledNames(excludeColumnFiledNames).sheet(sheetName).doWrite(data);
    }


    /**
     * 解析监听器
     */
    public static class EasyExcelListener<T> extends AnalysisEventListener<T> {

        /**
         * 保存解析数据
         */
        private List<T> data = new ArrayList<>();

        /**
         * 保存表头信息
         */
        private Map<Integer, String> headMap = new HashMap<>();

        /**
         * 这个每一条数据解析都会来调用
         *
         * @param t
         * @param analysisContext
         */
        @Override
        public void invoke(T t, AnalysisContext analysisContext) {
            if (t != null) {
                this.data.add(t);
            }
        }

        /**
         * 所有数据解析完成了,都会来调用,每一个sheet读取完都会调用
         *
         * @param analysisContext
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            //TODO do something

        }

        /**
         * 获取头字段信息
         *
         * @param headMap
         * @param context
         */
        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
            LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
            this.headMap = headMap;
        }

        /**
         * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
         *
         * @param exception
         * @param context
         * @throws Exception
         */
        @Override
        public void onException(Exception exception, AnalysisContext context) {
            LOGGER.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
            // 如果是某一个单元格的转换异常 能获取到具体行号
            if (exception instanceof ExcelDataConvertException) {
                ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
                LOGGER.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                        excelDataConvertException.getColumnIndex() + 1);
            }
        }

        private List<T> getData() {
            return data;
        }

        private Map<Integer, String> getHeadMap() {
            return headMap;
        }
    }
}

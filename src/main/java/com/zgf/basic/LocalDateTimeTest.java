/**
 * Copyright (C) 2006-2019 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2019/11/26
 * Description: LocalDate和Time
 */
package com.zgf.basic;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate和Time
 * @author zhangguifeng
 * @create 2019-11-26 15:38
 **/
public class LocalDateTimeTest {
    public static void main(String[] args) {
        testLocalDateTime();
    }

    private static void testLocalDateTime() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime time = LocalDateTime.now();

        System.out.println(time.toString()); //字符串表示
        System.out.println(time.toLocalTime().toString()); //获取时间(LocalTime)
        System.out.println(time.toLocalDate().toString()); //获取日期(LocalDate)
        System.out.println(time.getMonthValue());
        System.out.println(time.getMonth());
        System.out.println(time.getDayOfMonth()); //获取当前时间月份的第几天
        System.out.println(time.getDayOfWeek());  //获取当前周的第几天
        System.out.println(time.getDayOfWeek().getValue());  //获取当前周的第几天
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println("------1-----------------------------");
//        格式化输出
        System.out.println(time.format(formatter));
//        构造时间
        LocalDateTime startTime = LocalDateTime.of(2018, 1, 3, 20, 31, 20);
        LocalDateTime endTime = LocalDateTime.of(2018, 1, 3, 20, 31, 20);
//        比较时间
        System.out.println(time.isAfter(startTime));
        System.out.println(time.isBefore(endTime));
        System.out.println("---------2--------------------------");
//        时间运算，相加相减
        System.out.println(time.plusYears(2)); //加2年
        System.out.println(time.plusDays(2)); //加两天
        System.out.println(time.minusYears(2)); //减两年
        System.out.println(time.minusDays(2)); //减两天
        System.out.println("---------3--------------------------");
//        获取秒数(使用Instant)
        System.out.println(time.atZone(ZoneOffset.of("+8")).toInstant().getEpochSecond());
//        获取毫秒数(使用Instant)
        System.out.println("毫秒数 1 = " + time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        long timestamp = time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("毫秒数 2 = " + timestamp);
        // 毫秒数 转 LocalDateTime
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime convertObj = LocalDateTime.ofInstant(instant, ZoneOffset.of("+8"));
        System.out.println("convertObj  = " + convertObj.format(formatter));

        System.out.println("---------4--------------------------");
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime minTime = localDateTime.with(LocalTime.MIN);
        LocalDateTime maxTime = localDateTime.with(LocalTime.MAX);

        String min = minTime.format(formatter);
        String max = maxTime.format(formatter);

        System.out.println(min);
        System.out.println(max);
        System.out.println("---------5--------------------------");
        LocalDateTime parseLocalDateTime = LocalDateTime.parse("2019-11-29 18:05:45", formatter);
        System.out.println("parseLocalDateTime = " + formatter.format(parseLocalDateTime));
        System.out.println("---------6--------------------------");
    }
}

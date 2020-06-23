package com.zgf.kafka;

import cn.hutool.core.collection.CollUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    // 定义主题
    private static String topic = "test";

    public static void main(String[] args) {
        Properties properties = new Properties();
        // brokers 地址
        properties.put("bootstrap.servers", "localhost:9092");
        // 指定该 consumer 将加入的消费组
        properties.put("group.id", "zgf-group");
        // 开启自动提交 offset
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");

        properties.put("auto.offset.reset", "latest");
        properties.put("session.timeout.ms", "30000");
        properties.put("heartbeat.interval.ms", "10000");
        properties.put("max.poll.interval.ms", "100000");
        // 指定序列化类
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        // 订阅消费主题
        kafkaConsumer.subscribe(CollUtil.newArrayList(topic));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("-----------------");
                System.out.println(String.format("offset = %s, value = %s", record.offset(), record.value()));
                System.out.println();
            }
        }
    }
}

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
        // 指定了消费者在被认为死亡之前可以与服务器断开连接的时间
        // 如果消费者没有在session.timeout.ms 指定的时间内发送心跳给群组协调器,就被认为已经死亡,协调器就会触发再均衡
        properties.put("session.timeout.ms", "30000");
        // 指定了 poll() 方法向协调器发送心跳的频率，
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

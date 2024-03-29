package org.wsd.app.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.transaction.support.AbstractPlatformTransactionManager.SYNCHRONIZATION_ON_ACTUAL_TRANSACTION;

@EnableKafka
@Configuration
public class KafkaConfig {


    /*@Bean
    public KafkaTransactionManager<String, String> kafkaTransactionManager(ProducerFactory<String, String> producerFactory) {
        KafkaTransactionManager<String, String> transactionManager = new KafkaTransactionManager<>(producerFactory);
        transactionManager.setTransactionSynchronization(SYNCHRONIZATION_ON_ACTUAL_TRANSACTION);
        return transactionManager;
    }*/

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        return new KafkaAdmin(configs);
    }


    @Bean
    public NewTopic userLocationTopic() {
        return TopicBuilder.name("user-location")
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

}

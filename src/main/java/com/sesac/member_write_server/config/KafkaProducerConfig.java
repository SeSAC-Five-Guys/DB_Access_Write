package com.sesac.member_write_server.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableKafka
@Configuration
public class KafkaProducerConfig {
	/*@Value("#{'${spring.kafka.bootstrap-servers}'.split(',')}")
	private List<String> servers;*/
	/*@Value("${kafka.producer.acksConfig}")
	private String acksConfig;

	@Value("${kafka.producer.retry}")
	private Integer retry;

	@Value("${kafka.producer.enable-idempotence}")
	private Boolean enableIdempotence;
	@Value("${kafka.producer.max-in-flight-requests-per-connection}")
	private Integer maxInFlightRequestsPerConnection;*/
	@Value("${spring.kafka.bootstrap-servers}")
	private String servers;
	@Bean
	public ProducerFactory<String, Map<String,String>> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();

		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		log.warn(servers);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		/*configProps.put(ProducerConfig.RETRIES_CONFIG, retry);
		configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence);
		configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, maxInFlightRequestsPerConnection);*/

		return new DefaultKafkaProducerFactory<>(configProps);
	}
	@Bean
	public KafkaTemplate<String, Map<String,String>> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}

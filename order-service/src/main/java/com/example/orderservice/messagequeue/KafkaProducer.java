package com.example.orderservice.messagequeue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//import org.springframework.kafka.core.KafkaTemplate;

@Service
@Slf4j
public class KafkaProducer {
//    private KafkaTemplate<String, String> kafkaTemplate;

//    @Autowired
//    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public OrderDto send(String topic, OrderDto orderDto) {
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonInString = "";
//        try {
//            jsonInString = mapper.writeValueAsString(orderDto);
//        } catch(JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//
//        kafkaTemplate.send(topic, jsonInString);
//        log.info("Kafka Producer sent data from the Order microservice: " + orderDto);
//
//        return orderDto;
//    }
}

package br.com.mv.cloud.aws.product_consumer.service;


import com.fasterxml.jackson.core.JsonProcessingException;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public interface ProductEventConsumerService {

    void receiveProductEvent(TextMessage textMessage) throws JMSException, JsonProcessingException;
}

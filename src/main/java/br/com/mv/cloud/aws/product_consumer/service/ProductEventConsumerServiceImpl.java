package br.com.mv.cloud.aws.product_consumer.service;

import br.com.mv.cloud.aws.product_consumer.model.ProductEvent;
import br.com.mv.cloud.aws.product_consumer.model.SnsMessage;
import br.com.mv.cloud.aws.product_consumer.model.TopicEnvelope;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class ProductEventConsumerServiceImpl implements ProductEventConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ProductEventConsumerService.class);
    private final ObjectMapper objectMapper;

    public ProductEventConsumerServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void receiveProductEvent(TextMessage textMessage) throws JMSException, JsonProcessingException {
        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(), SnsMessage.class);

        TopicEnvelope topicEnvelope = objectMapper.readValue(snsMessage.getMessage(), TopicEnvelope.class);

        ProductEvent productEvent = objectMapper.readValue(topicEnvelope.getData(), ProductEvent.class);

        log.info("Product event received: - Event {} - ProductId: {} - "
                , topicEnvelope.getEventType()
                , productEvent.getProductId());
    }
}

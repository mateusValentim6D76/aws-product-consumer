package br.com.mv.cloud.aws.product_consumer.service;

import br.com.mv.cloud.aws.product_consumer.model.ProductEvent;
import br.com.mv.cloud.aws.product_consumer.model.ProductEventLog;
import br.com.mv.cloud.aws.product_consumer.model.SnsMessage;
import br.com.mv.cloud.aws.product_consumer.model.TopicEnvelope;
import br.com.mv.cloud.aws.product_consumer.repository.ProductEventLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Service
public class ProductEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(
            ProductEventConsumer.class);

    private ObjectMapper objectMapper;
    private ProductEventLogRepository productEventLogRepository;

    @Autowired
    public ProductEventConsumer(ObjectMapper objectMapper, ProductEventLogRepository productEventLogRepository) {
        this.objectMapper = objectMapper;
        this.productEventLogRepository = productEventLogRepository;
    }

    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void receiveProductEvent(TextMessage textMessage)
            throws JMSException, IOException {

        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(),
                SnsMessage.class);

        TopicEnvelope envelope = objectMapper.readValue(snsMessage.getMessage(),
                TopicEnvelope.class);

        ProductEvent productEvent = objectMapper.readValue(
                envelope.getData(), ProductEvent.class);

        log.info("Product event received - Event: {} - Data: {} - ProductId: {} - MessageId: {}",
                envelope.getEventType(),
                envelope.getData(),
                productEvent.getProductId(),
                snsMessage.getMessageId());

        ProductEventLog productEventLog = buildProductEventLog(envelope,
                productEvent);

        productEventLogRepository.save(productEventLog);
    }

    private ProductEventLog buildProductEventLog(TopicEnvelope envelope,
                                                 ProductEvent productEvent) {
        long timestamp = Instant.now().toEpochMilli();

        ProductEventLog productEventLog = new ProductEventLog();
        productEventLog.setPk(productEvent.getCode());
        productEventLog.setSk(envelope.getEventType() + "_" + timestamp);
        productEventLog.setEventTypeInform(envelope.getEventType());
        productEventLog.setProductId(productEvent.getProductId());
        productEventLog.setUserName(productEvent.getUsername());
        productEventLog.setTimestamp(timestamp);
        productEventLog.setTtl(Instant.now().plus(
                Duration.ofMinutes(10)).getEpochSecond());

        return productEventLog;
    }
}

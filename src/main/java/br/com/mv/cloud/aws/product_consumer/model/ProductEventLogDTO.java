package br.com.mv.cloud.aws.product_consumer.model;

import br.com.mv.cloud.aws.product_consumer.enums.EventTypeInform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProductEventLogDTO {

    private final String code;
    private final EventTypeInform eventType;
    private final long productId;
    private final String username;
    private final long timestamp;

    public ProductEventLogDTO(ProductEventLog productEventLog) {
        this.code = productEventLog.getPk();
        this.eventType = productEventLog.getEventTypeInform();
        this.productId = productEventLog.getProductId();
        this.username = productEventLog.getUserName();
        this.timestamp = productEventLog.getTimestamp();
    }

    public String getCode() {
        return code;
    }

    public EventTypeInform getEventType() {
        return eventType;
    }

    public long getProductId() {
        return productId;
    }

    public String getUsername() {
        return username;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
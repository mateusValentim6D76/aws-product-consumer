package br.com.mv.cloud.aws.product_consumer.model;

import br.com.mv.cloud.aws.product_consumer.enums.EventTypeInform;
import lombok.Getter;

@Getter
public class ProductEventLogDTO {

    private final String code;
    private final long productId;
    private final EventTypeInform enventType;
    private final String username;
    private final long timestamp;

    public ProductEventLogDTO(ProductEventLog productEventLog) {
        this.code = productEventLog.getPk();
        this.productId = productEventLog.getProductId();
        this.enventType = productEventLog.getEventTypeInform();
        this.username = productEventLog.getUserName();
        this.timestamp = productEventLog.getTimestamp();
    }

}

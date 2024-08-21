package br.com.mv.cloud.aws.product_consumer.model;

import br.com.mv.cloud.aws.product_consumer.enums.EventTypeInform;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "product-events")
public class ProductEventLog {

    @Id
    private ProductEventKey productEventKey;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "eventTypeInform")
    private EventTypeInform eventTypeInform;
    @DynamoDBAttribute(attributeName = "productId")
    private long productId;

    @DynamoDBAttribute(attributeName = "userName")
    private String userName;

    @DynamoDBAttribute(attributeName = "timestamp")
    private long timestamp;

    @DynamoDBAttribute(attributeName = "ttl")
    private long ttl;
    public ProductEventLog() {
    }

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return this.productEventKey != null ? this.productEventKey.getPk() : null;
    }
    public void setPk(String pk) {
        if(productEventKey == null){
            this.productEventKey = new ProductEventKey();
        }
        this.productEventKey.setPk(pk);
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return this.productEventKey != null ? this.productEventKey.getSk() : null;

    }

    public void setSk(String sk) {
        if(productEventKey == null){
            this.productEventKey = new ProductEventKey();
        }
        this.productEventKey.setSk(sk);
    }

    public EventTypeInform getEventTypeInform() {
        return eventTypeInform;
    }

    public void setEventTypeInform(EventTypeInform eventTypeInform) {
        this.eventTypeInform = eventTypeInform;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}

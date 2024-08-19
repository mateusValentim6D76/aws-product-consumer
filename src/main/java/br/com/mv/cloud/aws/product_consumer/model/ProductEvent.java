package br.com.mv.cloud.aws.product_consumer.model;

import lombok.Getter;
import lombok.Setter;

public class ProductEvent {

    private long productId;
    private String code;
    private String username;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

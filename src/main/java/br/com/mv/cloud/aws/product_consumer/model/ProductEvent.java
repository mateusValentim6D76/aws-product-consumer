package br.com.mv.cloud.aws.product_consumer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEvent {

    private long productId;
    private String code;
    private String username;
}

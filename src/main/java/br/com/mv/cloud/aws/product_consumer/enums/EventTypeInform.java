package br.com.mv.cloud.aws.product_consumer.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public enum EventTypeInform {

    PRODUCT_CREATE,
    PRODUCT_UPDATE,
    PRODUCT_DELETE
}

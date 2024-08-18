package br.com.mv.cloud.aws.product_consumer.model;


import br.com.mv.cloud.aws.product_consumer.enums.EventTypeInform;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicEnvelope {

    private EventTypeInform eventType;
    private String data;
}

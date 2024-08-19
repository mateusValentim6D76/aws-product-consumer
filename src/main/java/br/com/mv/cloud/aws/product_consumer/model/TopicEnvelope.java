package br.com.mv.cloud.aws.product_consumer.model;


import br.com.mv.cloud.aws.product_consumer.enums.EventTypeInform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class TopicEnvelope {

    private EventTypeInform eventType;
    private String data;

    public EventTypeInform getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeInform eventType) {
        this.eventType = eventType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

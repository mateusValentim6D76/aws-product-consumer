package br.com.mv.cloud.aws.product_consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SnsMessage {

    @JsonProperty("Message")
    private String message;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Topic_Arn")
    private String topicArn;
    @JsonProperty("Timestamp")
    private String timestamp;
    @JsonProperty("MessageId")
    private String messageId;


}

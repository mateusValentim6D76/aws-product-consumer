package br.com.mv.cloud.aws.product_consumer.service;


import br.com.mv.cloud.aws.product_consumer.model.ProductEventLog;
import br.com.mv.cloud.aws.product_consumer.model.ProductEventLogDTO;

import java.util.List;

public interface ProductEventConsumerService {

    List<ProductEventLogDTO> findAllByPk(String code);

    List<ProductEventLogDTO> findAllByPkAndStartsWith(String code, String eventType);

    List<ProductEventLogDTO> findAll();
}

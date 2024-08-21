package br.com.mv.cloud.aws.product_consumer.service;

import br.com.mv.cloud.aws.product_consumer.model.ProductEventLogDTO;
import br.com.mv.cloud.aws.product_consumer.repository.ProductEventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductEventConsumerServiceImpl implements ProductEventConsumerService {

    @Autowired
    ProductEventLogRepository productEventLogRepository;

    public List<ProductEventLogDTO> findAll() {
        return StreamSupport.stream(productEventLogRepository.findAll()
                        .spliterator(), false)
                .map(ProductEventLogDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductEventLogDTO> findAllByPk(String code) {
        return productEventLogRepository
                .findAllByPk(code)
                .stream()
                .map(ProductEventLogDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductEventLogDTO> findAllByPkAndStartsWith(String code, String eventType) {
        return productEventLogRepository.findAllByPkAndStartsWith(code, eventType)
                .stream()
                .map(ProductEventLogDTO::new)
                .collect(Collectors.toList());
    }
}
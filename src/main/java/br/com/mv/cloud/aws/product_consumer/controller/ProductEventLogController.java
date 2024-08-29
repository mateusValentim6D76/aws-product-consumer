package br.com.mv.cloud.aws.product_consumer.controller;

import br.com.mv.cloud.aws.product_consumer.enums.EventTypeInform;
import br.com.mv.cloud.aws.product_consumer.model.ProductEventLogDTO;
import br.com.mv.cloud.aws.product_consumer.repository.ProductEventLogRepository;
import br.com.mv.cloud.aws.product_consumer.service.ProductEventConsumerService;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/events-log")
public class ProductEventLogController {

    @Autowired
    private ProductEventLogRepository productEventLogRepository;
    @Autowired
    private ProductEventConsumerService productEventConsumerService;

    @GetMapping("/events")
    public List<ProductEventLogDTO> getAllEvents() {
        return StreamSupport
                .stream(productEventLogRepository.findAll().spliterator(), false)
                .map(ProductEventLogDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/events/{code}")
    public ResponseEntity<List<ProductEventLogDTO>> getEventsByCode(@PathVariable String code) {
        try {
            List<ProductEventLogDTO> dto = productEventConsumerService.findAllByPk(code);
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @GetMapping("/events/{code}/{event}")
    public ResponseEntity<List<ProductEventLogDTO>> findByCodeAndEventType(@PathVariable String code,
                                                           @PathVariable String event) {
        List<ProductEventLogDTO> dto = productEventConsumerService.findAllByPkAndStartsWith(code, event);
        return ResponseEntity.ok(dto);
    }
}
package br.com.mv.cloud.aws.product_consumer.controller;

import br.com.mv.cloud.aws.product_consumer.enums.EventTypeInform;
import br.com.mv.cloud.aws.product_consumer.model.ProductEventLogDTO;
import br.com.mv.cloud.aws.product_consumer.repository.ProductEventLogRepository;
import br.com.mv.cloud.aws.product_consumer.service.ProductEventConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-event-log")
public class ProductEventLogController {

    @Autowired
    private ProductEventConsumerService productEventConsumerService;

    @GetMapping()
    public ResponseEntity<List<ProductEventLogDTO>> getAllEvents(@PathVariable String code) {
        List<ProductEventLogDTO> dto = productEventConsumerService.findAllByPk(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("events/{code}")
    public ResponseEntity<List<ProductEventLogDTO>> getEventsByCode(@PathVariable String code) {
        List<ProductEventLogDTO> dto = productEventConsumerService.findAllByPk(code);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("events/{code}/eventType/{eventType}")
    public ResponseEntity<List<ProductEventLogDTO>> getEventsByCodeAndEventType(@PathVariable String code, @PathVariable String eventType) {
        List<ProductEventLogDTO> dto = productEventConsumerService.findAllByPkAndStartsWith(code, eventType);
        return ResponseEntity.ok(dto);
    }
}

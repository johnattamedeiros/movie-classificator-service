package com.classificator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.classificator.service.IntervalWinProducerService;
import com.classificator.service.dto.ProducerWinnerIntervalList;

@RestController
@RequestMapping("/api/producer")
public class ProducerResource {
    
    @Autowired
    IntervalWinProducerService intervalWinProducerService;
    
    @GetMapping("/producers/winner-successive-summary")
    public ResponseEntity<ProducerWinnerIntervalList> getProducersWithMaximumAndMinimalIntervalByVictories() {
        ProducerWinnerIntervalList producerWinnerIntervalList = intervalWinProducerService.findProducersWithMaximumAndMinimalIntervalByVictories();
        return ResponseEntity.ok().body(producerWinnerIntervalList);
    }
}


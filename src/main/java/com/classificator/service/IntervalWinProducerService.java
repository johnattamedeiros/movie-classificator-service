package com.classificator.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classificator.domain.IntervalWinProducer;
import com.classificator.domain.ProducerWinner;
import com.classificator.repository.IntervalWinProducerRepository;
import com.classificator.repository.ProducerWinnerRepository;
import com.classificator.service.dto.ProducerWinnerComparision;
import com.classificator.service.dto.ProducerWinnerIntervalList;
import com.classificator.service.dto.mapper.ProducerWinnerMapper;

@Service
@Transactional
public class IntervalWinProducerService {

    @Autowired
    private ProducerWinnerRepository producerWinnerRepository;

    @Autowired
    private IntervalWinProducerRepository intervalWinProducerRepository;
    
    @Autowired
    ProducerWinnerMapper producerWinnerMapper;

    public void setIntervalWinnersByProducer() {
        List<ProducerWinner> producersWithConsecutiveVictories = producerWinnerRepository.findAll();

        Map<Long, ProducerWinnerComparision> producerWinnerWithInterval = new HashMap<Long, ProducerWinnerComparision>();

        for (ProducerWinner producerWinner : producersWithConsecutiveVictories) {
            setProducerWinner(producerWinner, producerWinnerWithInterval);
            updateProducerWinnerIntervalAndWinYears(producerWinnerWithInterval, producerWinner);
        }

    }

    private void setProducerWinner(ProducerWinner producerWinner, Map<Long, ProducerWinnerComparision> producerWinnerWithInterval) {
        if (!producerWinnerWithInterval.containsKey(producerWinner.getIdProducer())) {
            producerWinnerWithInterval.put(producerWinner.getIdProducer(), new ProducerWinnerComparision(producerWinner.getIdProducer(), producerWinner.getName()));
        }

    }

    private void updateProducerWinnerIntervalAndWinYears(Map<Long, ProducerWinnerComparision> producerWinnerWithInterval, ProducerWinner producerWinner) {
        ProducerWinnerComparision producerToCompare = producerWinnerWithInterval.get(producerWinner.getIdProducer());

        if (producerToCompare.getPreviousWin() == null) {
            producerToCompare.setPreviousWin(producerWinner.getYearDate());
            updateHashProducer(producerWinnerWithInterval, producerToCompare);
            return;
        }

        if (producerToCompare.getFollowingWin() == null) {
            producerToCompare.setFollowingWin(producerWinner.getYearDate());
            producerToCompare.setIntervalWin(calculateInterval(producerToCompare));

            updateHashProducer(producerWinnerWithInterval, producerToCompare);
            saveNewIntervalByProducerComparision(producerToCompare);
            return;
        }

        producerToCompare.setPreviousWin(producerToCompare.getFollowingWin());
        producerToCompare.setFollowingWin(producerWinner.getYearDate());
        producerToCompare.setIntervalWin(calculateInterval(producerToCompare));
        updateHashProducer(producerWinnerWithInterval, producerToCompare);
        saveNewIntervalByProducerComparision(producerToCompare);
    }

    private void updateHashProducer(Map<Long, ProducerWinnerComparision> producerWinnerWithInterval, ProducerWinnerComparision producerToCompare) {
        ProducerWinnerComparision producerUpdate = producerWinnerWithInterval.get(producerToCompare.getId());
        producerUpdate.setPreviousWin(producerToCompare.getPreviousWin());
        producerUpdate.setFollowingWin(producerToCompare.getFollowingWin());
        producerUpdate.setIntervalWin(producerToCompare.getIntervalWin());
        producerWinnerWithInterval.put(producerToCompare.getId(), producerUpdate);
    }

    private void saveNewIntervalByProducerComparision(ProducerWinnerComparision producerToCompare) {
        IntervalWinProducer intervalWinProducer = new IntervalWinProducer(producerToCompare.getId(),
                producerToCompare.getName(), producerToCompare.getIntervalWin(), producerToCompare.getPreviousWin(),
                producerToCompare.getFollowingWin());
        intervalWinProducerRepository.save(intervalWinProducer);
    }

    private Long calculateInterval(ProducerWinnerComparision producerToCompare) {
        return producerToCompare.getFollowingWin() - producerToCompare.getPreviousWin();
    }

    public ProducerWinnerIntervalList findProducersWithMaximumAndMinimalIntervalByVictories() {
        ProducerWinnerIntervalList producerWinnerIntervalList = new ProducerWinnerIntervalList();
        List<IntervalWinProducer> producerWinnerWithMaxInterval = intervalWinProducerRepository.findProducerWithMaxInterval();
        List<IntervalWinProducer> producerWinnerWithMinInterval =intervalWinProducerRepository.findProducerWithMinInterval();
        
        
        producerWinnerIntervalList.setMax(producerWinnerMapper.intervalWinProducerToProducerWinnerSummary(producerWinnerWithMaxInterval));
        producerWinnerIntervalList.setMin(producerWinnerMapper.intervalWinProducerToProducerWinnerSummary(producerWinnerWithMinInterval));
        
        return producerWinnerIntervalList;
    }

}

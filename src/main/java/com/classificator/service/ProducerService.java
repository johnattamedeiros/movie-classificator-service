package com.classificator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classificator.domain.Producer;
import com.classificator.repository.ProducerRepository;

@Service
@Transactional
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    public void saveAll(List<Producer> producers) {
        producerRepository.saveAll(producers);

    }

}

package com.classificator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.classificator.domain.ProducerWinner;

public interface ProducerWinnerRepository extends JpaRepository<ProducerWinner, Long> {
    
}
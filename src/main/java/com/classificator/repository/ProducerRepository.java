package com.classificator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classificator.domain.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    
    Producer findByName(String name);

}
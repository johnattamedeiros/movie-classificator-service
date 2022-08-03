package com.classificator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classificator.domain.MovieProducer;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducer, Long> {

}
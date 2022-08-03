package com.classificator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.classificator.domain.IntervalWinProducer;

public interface IntervalWinProducerRepository extends JpaRepository<IntervalWinProducer, Long> {

    @Query(
            value = "SELECT * FROM MOVIES.INTERVAL_WIN_PRODUCER "
                    + "WHERE INTERVAL_WIN = (SELECT MAX(INTERVAL_WIN) FROM MOVIES.INTERVAL_WIN_PRODUCER)", 
            nativeQuery = true)
    List<IntervalWinProducer> findProducerWithMaxInterval();

    @Query(
            value = "SELECT * FROM MOVIES.INTERVAL_WIN_PRODUCER "
                    + "WHERE INTERVAL_WIN = (SELECT MIN(INTERVAL_WIN) FROM MOVIES.INTERVAL_WIN_PRODUCER)", 
            nativeQuery = true)
    List<IntervalWinProducer> findProducerWithMinInterval();
    
    
}
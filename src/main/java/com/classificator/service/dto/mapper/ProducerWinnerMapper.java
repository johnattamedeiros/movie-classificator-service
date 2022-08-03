package com.classificator.service.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.classificator.domain.IntervalWinProducer;
import com.classificator.service.dto.ProducerWinnerSummary;


@Mapper(componentModel = "spring", uses = {})
public interface ProducerWinnerMapper {

     @Mapping(source = "intervalWin", target = "interval")
     @Mapping(source = "name", target = "producer")
     ProducerWinnerSummary intervalWinProducerToProducerWinnerSummary(IntervalWinProducer intervalWinProducerList);
     
     @Mapping(source = "intervalWin", target = "interval")
     @Mapping(source = "name", target = "producer")
     List<ProducerWinnerSummary> intervalWinProducerToProducerWinnerSummary(List<IntervalWinProducer> intervalWinProducerList);

}

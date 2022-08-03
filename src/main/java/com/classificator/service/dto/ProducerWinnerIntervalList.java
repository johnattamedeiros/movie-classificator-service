package com.classificator.service.dto;

import java.util.List;

public class ProducerWinnerIntervalList {

    private List<ProducerWinnerSummary> min;
    private List<ProducerWinnerSummary> max;
    public List<ProducerWinnerSummary> getMin() {
        return min;
    }
    public void setMin(List<ProducerWinnerSummary> min) {
        this.min = min;
    }
    public List<ProducerWinnerSummary> getMax() {
        return max;
    }
    public void setMax(List<ProducerWinnerSummary> max) {
        this.max = max;
    }
    
}

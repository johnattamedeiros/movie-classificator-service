package com.classificator;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.classificator.service.IntervalWinProducerService;
import com.classificator.service.MovieImportService;

@SpringBootApplication
public class MovieClassificatorServiceApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(MovieClassificatorServiceApplication.class, args);
        MovieImportService movieImportService = applicationContext.getBean(MovieImportService.class);
        IntervalWinProducerService intervalWinProducerService = applicationContext.getBean(IntervalWinProducerService.class);
        movieImportService.importMoviesFromCsvFile();
        intervalWinProducerService.setIntervalWinnersByProducer();
    }

}

package com.classificator.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.classificator.domain.Movie;
import com.classificator.domain.MovieProducer;
import com.classificator.domain.Producer;
import com.classificator.repository.MovieProducerRepository;
import com.classificator.repository.MovieRepository;
import com.classificator.repository.ProducerRepository;

@Service
public class MovieImportService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    MovieProducerRepository movieProducerRepository;

    public void importMoviesFromCsvFile() {

        String CSVFile = "csv/movielist.csv";
        BufferedReader br = null;
        String line = "";
        Long idMovie = 1L;
        String csvDelimiter = ";";
        try {

            br = new BufferedReader(new FileReader(CSVFile));
            List<Movie> movies = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                String[] moviesProperties = line.split(csvDelimiter);
                if (moviesProperties[0].equalsIgnoreCase("year") != true) {
                    Movie movie = new Movie();
                    movie.setId(idMovie);
                    movie.setYearDate(Long.parseLong(moviesProperties[0]));
                    movie.setTitle(moviesProperties[1]);
                    movie.setStudios(moviesProperties[2]);
                    movie.setProducers(moviesProperties[3]);
                    boolean hasWin = hasWin(moviesProperties);
                    saveProducers(moviesProperties, idMovie, hasWin);
                    movie.setWinner(hasWin);

                    movies.add(movie);
                    idMovie++;
                }
            }
            movieRepository.saveAll(movies);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveProducers(String[] moviesProperties, Long idMovie, boolean winner) {
        String[] winnerProducerList = moviesProperties[3].split("\\,|([ ]and[ ])");
        List<MovieProducer> movieProducerList = new ArrayList<>();
        for (String producerName : winnerProducerList) {
            if (!producerName.isBlank()) {
                Producer producer = getProducer(producerName);

                if (producer.getName() == null) {
                    producer.setName(producerName.trim());
                    producerRepository.save(producer);
                }

                MovieProducer movieProducer = new MovieProducer();
                movieProducer.setIdMovie(idMovie);
                movieProducer.setIdProducer(producer.getId());
                movieProducer.setWinner(winner);
                movieProducerList.add(movieProducer);
            }
        }

        movieProducerRepository.saveAll(movieProducerList);
    }

    private Producer getProducer(String name) {
        Producer producerFound = producerRepository.findByName(name.trim());
        if (producerFound == null) {
            producerFound = new Producer();
        }
        return producerFound;
    }

    private Boolean hasWin(String[] moviesProperties) {
        if (moviesProperties.length > 4) {
            return moviesProperties[4].equalsIgnoreCase("yes") ? true : false;
        } else {
            return false;
        }
    }
}

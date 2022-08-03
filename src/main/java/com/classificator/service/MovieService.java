package com.classificator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classificator.domain.Movie;
import com.classificator.repository.MovieRepository;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void saveAll(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

}

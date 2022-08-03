package com.classificator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.classificator.domain.Movie;
import com.classificator.domain.MovieProducer;
import com.classificator.domain.Producer;
import com.classificator.repository.MovieProducerRepository;
import com.classificator.repository.MovieRepository;
import com.classificator.repository.ProducerRepository;
import com.classificator.service.IntervalWinProducerService;
import com.classificator.service.dto.ProducerWinnerIntervalList;
import com.classificator.service.dto.ProducerWinnerSummary;

@IntegrationTest
@AutoConfigureMockMvc
public class ProducerResourceIT {

    private static final String PRODUCER_RESOURCE_URL = "/api/producer/producers";

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private MovieProducerRepository movieProducerRepository;
    
    @Autowired
    private IntervalWinProducerService intervalWinProducerService;
    
    @Autowired
    private MockMvc producerResource;

    @BeforeEach
    public void setup() {

        createMockMovies();
        createMockProducers();
        createMockMovieProducers();
        intervalWinProducerService.setIntervalWinnersByProducer();

    }

    @Test
    @Transactional
    void getProducer_WithMaximumAndMinimumIntervalWins_ExpectCorrectData() throws Exception {

        MockHttpServletResponse response = producerResource
                .perform(get(PRODUCER_RESOURCE_URL + "/winner-successive-summary")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();

        ProducerWinnerIntervalList producerWinnerIntervalList = TestUtil.toObject(response.getContentAsByteArray(),
                ProducerWinnerIntervalList.class);

        List<ProducerWinnerSummary> minList = producerWinnerIntervalList.getMin();
        List<ProducerWinnerSummary> maxList = producerWinnerIntervalList.getMax();
        
        assertThat(minList).hasSize(2);
        assertThat(maxList).hasSize(2);
        
        assertThat(minList)
        .anyMatch(producer -> producer.getProducer().equals("Diego"));
        
        assertThat(maxList)
        .anyMatch(producer -> producer.getProducer().equals("James"));
        
    }

    private void createMockMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, 2000L, true));
        movies.add(new Movie(2L, 2000L, false));
        movies.add(new Movie(3L, 2000L, false));
        movies.add(new Movie(4L, 2001L, true));
        movies.add(new Movie(5L, 2001L, false));
        movies.add(new Movie(6L, 2001L, false));
        movies.add(new Movie(7L, 2003L, true));
        movies.add(new Movie(8L, 2003L, false));
        movies.add(new Movie(9L, 2003L, false));
        movies.add(new Movie(10L, 2006L, true));
        movies.add(new Movie(11L, 2006L, false));
        movies.add(new Movie(12L, 2006L, false));
        movies.add(new Movie(13L, 2007L, true));
        movies.add(new Movie(14L, 2007L, false));
        movies.add(new Movie(15L, 2007L, false));
        movies.add(new Movie(16L, 2008L, true));
        movies.add(new Movie(17L, 2008L, false));
        movies.add(new Movie(18L, 2008L, false));
        movies.add(new Movie(19L, 2009L, true));
        movies.add(new Movie(20L, 2009L, false));
        movies.add(new Movie(21L, 2009L, false));
        movies.add(new Movie(22L, 2010L, true));
        movies.add(new Movie(23L, 2010L, false));
        movies.add(new Movie(24L, 2010L, false));
        movies.add(new Movie(25L, 2011L, true));
        movies.add(new Movie(26L, 2011L, false));
        movies.add(new Movie(27L, 2011L, false));
        movies.add(new Movie(28L, 2012L, true));
        movies.add(new Movie(29L, 2012L, false));
        movies.add(new Movie(30L, 2012L, false));
        movieRepository.saveAll(movies);
    }

    private void createMockProducers() {
        List<Producer> producers = new ArrayList<>();
        producers.add(new Producer("James"));
        producers.add(new Producer("Jackson"));
        producers.add(new Producer("Maria"));
        producers.add(new Producer("Jason"));
        producers.add(new Producer("Fidalgo"));
        producers.add(new Producer("Farias"));
        producers.add(new Producer("Diego"));
        producers.add(new Producer("Joana"));

        producerRepository.saveAll(producers);

    }
    
    private void createMockMovieProducers() {
        List<MovieProducer> movieProducers = new ArrayList<>();
        movieProducers.add(new MovieProducer(1L, 1L,true));
        movieProducers.add(new MovieProducer(2L, 2L,false));
        movieProducers.add(new MovieProducer(3L, 4L,false));
       
        movieProducers.add(new MovieProducer(4L, 3L,true));
        movieProducers.add(new MovieProducer(5L, 1L,false));
        movieProducers.add(new MovieProducer(6L, 2L,false));
        movieProducers.add(new MovieProducer(7L, 3L,true));
       
        movieProducers.add(new MovieProducer(8L, 4L,false));
        movieProducers.add(new MovieProducer(9L, 5L,false));
        movieProducers.add(new MovieProducer(10L, 1L,true));
        movieProducers.add(new MovieProducer(11L, 6L,false));
        movieProducers.add(new MovieProducer(12L, 7L,false));
        movieProducers.add(new MovieProducer(12L, 4L,true));
        movieProducers.add(new MovieProducer(13L, 6L,false));
        movieProducers.add(new MovieProducer(14L, 6L,false));
        movieProducers.add(new MovieProducer(15L, 7L,false));
        movieProducers.add(new MovieProducer(16L, 4L,true));
        movieProducers.add(new MovieProducer(17L, 6L,false));
        movieProducers.add(new MovieProducer(18L, 5L,false));
        movieProducers.add(new MovieProducer(19L, 7L,true));
        movieProducers.add(new MovieProducer(20L, 2L,false));
        movieProducers.add(new MovieProducer(21L, 5L,false));
        movieProducers.add(new MovieProducer(22L, 7L,true));
        movieProducers.add(new MovieProducer(23L, 1L,false));
        movieProducers.add(new MovieProducer(24L, 8L,false));
        movieProducers.add(new MovieProducer(25L, 7L,true));
        movieProducers.add(new MovieProducer(26L, 2L,false));
        movieProducers.add(new MovieProducer(27L, 8L,false));
        movieProducers.add(new MovieProducer(28L, 1L,true));
        movieProducers.add(new MovieProducer(29L, 2L,false));
        movieProducers.add(new MovieProducer(30L, 4L,false));
        

        movieProducerRepository.saveAll(movieProducers);

    }

}

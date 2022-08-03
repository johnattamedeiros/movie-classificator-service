package com.classificator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classificator.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
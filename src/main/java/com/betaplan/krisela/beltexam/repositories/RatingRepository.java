package com.betaplan.krisela.beltexam.repositories;

import com.betaplan.krisela.beltexam.models.Rating;
import com.betaplan.krisela.beltexam.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long>, JpaRepository<Rating,Long> {

    List<Rating> findAll();

    List<Rating> findAllByOrderByRatingValueAsc();

    List<Rating> findAllByShowOrderByRatingValueAsc(Show show);
}

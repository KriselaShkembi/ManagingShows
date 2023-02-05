package com.betaplan.krisela.beltexam.services;

import com.betaplan.krisela.beltexam.models.Rating;
import com.betaplan.krisela.beltexam.models.Show;
import com.betaplan.krisela.beltexam.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getAll(){
        return ratingRepository.findAll();
    }

    public Rating create(Rating rating){
        return ratingRepository.save(rating);
    }


/*
    public List<Rating> findByOrderAsc(){
        return ratingRepository.findAllByOrderByRatingValueAsc();
    }
*/

    public List<Rating> findByShow(Show show){
        return ratingRepository.findAllByShowOrderByRatingValueAsc(show);
    }
}

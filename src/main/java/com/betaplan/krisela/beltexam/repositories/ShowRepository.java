package com.betaplan.krisela.beltexam.repositories;

import com.betaplan.krisela.beltexam.models.Show;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long> {

    List<Show> findAll();


    List<Show> findByTitle(String title);

}

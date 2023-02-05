package com.betaplan.krisela.beltexam.services;

import com.betaplan.krisela.beltexam.models.Show;
import com.betaplan.krisela.beltexam.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getAll(){
        return showRepository.findAll();
    }
    public Show create(Show Show){
        return showRepository.save(Show);
    }
    public Show save(Show Show){
        return showRepository.save(Show);
    }
    public void delete(Long id){
        showRepository.deleteById(id);
    };

    public List<Show> findByTitle(String title){
        return showRepository.findByTitle(title);
    }



    public Show findById(Long id) {
        Optional<Show> optionalProject = showRepository.findById(id);
        if(optionalProject.isPresent()) {
            return optionalProject.get();
        }else {
            return null;
        }
    }

}

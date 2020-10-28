package br.com.nflscrapping.consumer.service;

import br.com.nflscrapping.consumer.entity.Fumble;
import br.com.nflscrapping.consumer.repository.FumbleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FumbleService {

    @Autowired
    FumbleRepository repository;

    public void save(Fumble fumble){

        Optional<Fumble> exist = repository.findByName(fumble.getName());

        if (!exist.isPresent()){
            repository.save(fumble);
            System.out.println("objeto salvo");
        } else {
            System.out.println("objeto ja existe");
        }

    }

}

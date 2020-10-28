package br.com.nflscrapping.consumer.repository;

import br.com.nflscrapping.consumer.entity.Fumble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FumbleRepository extends JpaRepository<Fumble, Long> {

    Optional<Fumble> findByName(String name);

}

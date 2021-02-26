package com.gamingmatcher.demo.Games.Repository;

import com.gamingmatcher.demo.Games.Model.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends JpaRepository<Games, Long> {
}

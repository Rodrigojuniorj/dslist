package com.rodrigodev.dslist.repositories;

import com.rodrigodev.dslist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}

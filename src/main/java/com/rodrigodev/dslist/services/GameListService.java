package com.rodrigodev.dslist.services;

import com.rodrigodev.dslist.dto.GameListDTO;
import com.rodrigodev.dslist.dto.GameMinDTO;
import com.rodrigodev.dslist.entities.GameList;
import com.rodrigodev.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }
}

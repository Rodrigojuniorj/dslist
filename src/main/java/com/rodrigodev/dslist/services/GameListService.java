package com.rodrigodev.dslist.services;

import com.rodrigodev.dslist.dto.GameListDTO;
import com.rodrigodev.dslist.dto.GameMinDTO;
import com.rodrigodev.dslist.entities.GameList;
import com.rodrigodev.dslist.projections.GameMinProjection;
import com.rodrigodev.dslist.repositories.GameListRepository;
import com.rodrigodev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int souceIndex, int destinationIndex){
        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(souceIndex);
        list.add(destinationIndex, obj);

        int min = souceIndex < destinationIndex ? souceIndex : destinationIndex;
        int max = souceIndex < destinationIndex ? destinationIndex : souceIndex;

        for (int i = min; i <= max; i++){
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }
}

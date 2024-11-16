package com.etsai.playerservice.service.impl;

import com.etsai.playerservice.dto.PlayerDTO;
import com.etsai.playerservice.dto.PlayerInfoDTO;
import com.etsai.playerservice.entity.PlayerEntity;
import com.etsai.playerservice.mapper.PlayerMapper;
import com.etsai.playerservice.repository.IntuitDbRepository;
import com.etsai.playerservice.service.PlayerService;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private IntuitDbRepository db;

    private PlayerMapper playerMapper;

    @Autowired
    public PlayerServiceImpl(IntuitDbRepository intuitDbRepository, PlayerMapper playerMapper){
        this.db = intuitDbRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public List<PlayerInfoDTO> getAllPlayers() {
        log.info("Retrieving all players");
        return db.findAll().stream().map(playerMapper::mapToPlayerInfoDTO)
                .collect(Collectors.toList());

        /*
            Db Connection dbconn

            Thread1:
                Method()
            Thread2:
                Method()

           or flag in db row

            Method(){
                Lock dbconn
                write to dbconn
                open up dbconn
            }
         */
    }

    @Override
    public PlayerInfoDTO getPlayerByPlayerId(String playerId) {
        PlayerEntity player = db.getPlayerByPlayerID(playerId);
        return playerMapper.mapToPlayerInfoDTO(player);
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) throws BadRequestException {
        log.info("Creating a Player Entity in the Service");
        PlayerEntity player = playerMapper.mapToPlayerEntity(playerDTO);
        try {
            PlayerEntity saved = db.save(player);
            return playerMapper.mapToPlayerDTO(saved);
        } catch(DataIntegrityViolationException dataIntegrityViolationException){
            if(dataIntegrityViolationException.getMessage().contains("Duplicate entry")) {
                log.error("Error: Duplicate entry for [{}]", playerDTO.getPlayerID());
                throw new DuplicateRequestException(dataIntegrityViolationException.getMessage());
            } else {
                log.error("Error: Missing field [{}]", dataIntegrityViolationException.getMessage());
                throw new BadRequestException(dataIntegrityViolationException.getMessage());
            }
        }

    }
}

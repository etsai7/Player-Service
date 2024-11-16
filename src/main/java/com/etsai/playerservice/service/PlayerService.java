package com.etsai.playerservice.service;

import com.etsai.playerservice.dto.PlayerDTO;
import com.etsai.playerservice.dto.PlayerInfoDTO;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface PlayerService {
    List<PlayerInfoDTO> getAllPlayers();

    PlayerInfoDTO getPlayerByPlayerId(String playerId);

    PlayerDTO createPlayer( PlayerDTO playerDTO) throws BadRequestException;
}

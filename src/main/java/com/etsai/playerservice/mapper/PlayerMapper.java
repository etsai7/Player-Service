package com.etsai.playerservice.mapper;

import com.etsai.playerservice.dto.PlayerDTO;
import com.etsai.playerservice.dto.PlayerInfoDTO;
import com.etsai.playerservice.entity.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface PlayerMapper {
    PlayerInfoDTO mapToPlayerInfoDTO(PlayerEntity playerEntity);
    PlayerEntity mapToPlayerEntity(PlayerDTO playerDTO);

    PlayerDTO mapToPlayerDTO(PlayerEntity playerEntity);

}

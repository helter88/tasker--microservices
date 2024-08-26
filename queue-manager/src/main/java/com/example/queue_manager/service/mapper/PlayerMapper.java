package com.example.queue_manager.service.mapper;

import com.example.queue_manager.model.Player;
import com.example.queue_manager.service.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "uuid", ignore = true)
    Player toEntity(PlayerDto playerDto);
    @Mapping(target = "id", ignore = true)
    PlayerDto toDto(Player task);
}

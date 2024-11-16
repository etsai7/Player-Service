package com.etsai.playerservice.repository;

import com.etsai.playerservice.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IntuitDbRepository extends JpaRepository<PlayerEntity, String> {
    @Query("SELECT p from PlayerEntity p WHERE p.playerID = :playerId")
    PlayerEntity getPlayerByPlayerID(String playerId);
}

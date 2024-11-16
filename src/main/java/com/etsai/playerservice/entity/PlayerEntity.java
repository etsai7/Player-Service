package com.etsai.playerservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player_data")
public class PlayerEntity {

    @Id
    private String playerID;
    @Column(nullable = false)
    private int birthYear;
    @Column(nullable = false)
    private int birthMonth;
    @Column(nullable = false)
    private int birthDay;
    @Column(nullable = false)
    private String birthCountry;
    @Column(nullable = false)
    private String birthState;
    @Column(nullable = false)
    private String birthCity;
    @Column(nullable = true)
    private int deathYear;
    @Column(nullable = true)
    private int deathMonth;
    @Column(nullable = true)
    private int deathDay;
    @Column(nullable = true)
    private String deathCountry;
    @Column(nullable = true)
    private String deathState;
    @Column(nullable = true)
    private String deathCity;
    @Column(nullable = false)
    private String nameFirst;
    @Column(nullable = false)
    private String nameLast;
    @Column(nullable = false)
    private String 	nameGiven;
    @Column(nullable = true)
    private int weight;
    @Column(nullable = true)
    private int height;
    @Column(nullable = true)
    private String bats;
    @Column(nullable = true)
    private String throws_;
    @Column(nullable = true)
    private Date debut;
    @Column(nullable = true)
    private Date finalGame;
    @Column(nullable = true)
    private String retroID;
    @Column(nullable = true)
    private String bbrefID;



}

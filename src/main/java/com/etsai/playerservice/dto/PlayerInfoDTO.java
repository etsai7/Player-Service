package com.etsai.playerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfoDTO {
    private String playerID;
    private int height;
    private int weight;
    private String birthCity;
    private String birthState;
    private String nameGiven;

}

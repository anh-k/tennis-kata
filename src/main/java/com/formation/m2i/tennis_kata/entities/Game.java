package com.formation.m2i.tennis_kata.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game {
    private Player playerOne;
    private Player playerTwo;
}

package com.formation.m2i.tennis_kata.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TennisScoreCounter {

    @Builder.Default
    private Integer scoreOne = 0;

    @Builder.Default
    private Integer scoreTwo = 0;

    @Builder.Default
    private Integer playerOneWin = 0;

    @Builder.Default
    private Integer playerTwoWin = 0;

    @Builder.Default
    private Integer advantageOne = 0;

    @Builder.Default
    private Integer advantageTwo = 0;

    public Game createAGame() {
        return new Game();
    }

    public Game createAGameWithPlayers(Player playerOne, Player playerTwo) {
        return new Game(playerOne, playerTwo);
    }

    public String notifyPlayersPoint(Player player) {
        scoreAPoint(player);
        return player.getName() + " has scored a point";
    }

    public void scoreAPoint(Player player) {
        if (player.getName().equals("PlayerOne")) {
            this.scoreOne = givePointsToPlayer(scoreOne);
        } else {
            this.scoreTwo = givePointsToPlayer(scoreTwo);
        }
    }

    public Integer givePointsToPlayer(Integer score) {
        return switch (score) {
            case 0 -> 15;
            case 15 -> 30;
            case 30 -> 40;
            case 40 -> giveAdvantage();
            default -> 0;
        };
    }

    public Integer giveAdvantage() {
        if (scoreOne.equals(scoreTwo)) {
            return 1;
        } else return 0;
    }

}

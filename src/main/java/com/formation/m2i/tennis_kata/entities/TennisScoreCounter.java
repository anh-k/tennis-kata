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

    public String getScore() {
        if (isDeuce())
            return "Deuce";

        if (isAdvantage()) {
            return "Advantage to " + highestScorePlayer();
        }

        if (isWon()) {
            String result = highestScorePlayer() + " has won";
            this.scoreOne = 0;
            this.scoreTwo = 0;
            return result;
        }

        return "PlayerOne has " + scoreToString(scoreOne) + ", " + "PlayerTwo has " + scoreToString(scoreTwo);
    }

    public String notifyPlayersPoint(Player player) {
        return player.getName() + " has scored a point";
    }

    public void scoreAPoint(Player player) {
        if (player.getName().equals("PlayerOne")) {
            scoreOne++;
        } else {
            scoreTwo++;
        }
    }

    public String scoreToString(Integer score) {
        return switch (score) {
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> "0";
        };
    }

    private boolean isAdvantage() {
        if (scoreOne >= 4 && scoreOne == scoreTwo + 1)
            return true;
        return scoreTwo >= 4 && scoreTwo == scoreOne + 1;
    }

    private boolean isWon() {
        if (scoreOne >= 4 && scoreOne >= scoreTwo + 2)
            return true;
        return scoreTwo >= 4 && scoreTwo >= scoreOne + 2;
    }

    private boolean isDeuce() {
        return scoreOne >= 3 && scoreTwo.equals(scoreOne);
    }

    private String highestScorePlayer() {
        if (scoreOne > scoreTwo) {
            return "PlayerOne";
        } else {
            return "PlayerTwo";
        }
    }

}

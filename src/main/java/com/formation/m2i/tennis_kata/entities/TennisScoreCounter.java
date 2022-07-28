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
    private Player matchWinner;

    @Builder.Default
    private Player playerOne = new Player("PlayerOne");

    @Builder.Default
    private Player playerTwo = new Player("PlayerTwo");

    @Builder.Default
    private Integer scoreOne = 0;

    @Builder.Default
    private Integer scoreTwo = 0;

    @Builder.Default
    private Integer playerOneWinGame = 0;

    @Builder.Default
    private Integer playerTwoWinGame = 0;

    @Builder.Default
    private Integer playerOneSets = 0;

    @Builder.Default
    private Integer playerTwoSets = 0;

    @Builder.Default
    private Integer advantageOne = 0;

    @Builder.Default
    private Integer advantageTwo = 0;

    @Builder.Default
    private boolean isDecisive = false;

    public Game createAGame() {
        return new Game();
    }

    public Game createAGameWithPlayers(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        return new Game(playerOne, playerTwo);
    }

    public String getScore() {
        Player highestScorePlayer = highestScorePlayer();

        if (isDeuce())
            return "Deuce";

        if (isAdvantage()) {
            return "Advantage to " + highestScorePlayer.getName();
        }

        if (isWonGame()) {
            winGame(highestScorePlayer);
            this.scoreOne = 0;
            this.scoreTwo = 0;
            return highestScorePlayer.getName() + " has won the game";
        }

        return playerOne.getName() + " has " + scoreToString(scoreOne) + ", " + playerTwo.getName() + " has " + scoreToString(scoreTwo);
    }

    public String notifyPlayersPoint(Player player) {
        return player.getName() + " has scored a point";
    }

    public void scoreAPoint(Player player) {
        if (player.getName().equals(playerOne.getName())) {
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

    private boolean isWonGame() {
        if (scoreOne >= 4 && scoreOne >= scoreTwo + 2)
            return true;
        return scoreTwo >= 4 && scoreTwo >= scoreOne + 2;
    }

    private boolean isDeuce() {
        return scoreOne >= 3 && scoreTwo.equals(scoreOne);
    }

    private Player highestScorePlayer() {
        if (scoreOne > scoreTwo) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    private void winGame(Player player) {
        if (player.getName().equals(playerOne.getName())) {
            playerOneWinGame++;
            if ((playerOneWinGame >= 6 && playerTwoWinGame <= 4) || winSetWithAdvantage()) {
                winSet(player);
            }
        } else {
            playerTwoWinGame++;
            if ((playerTwoWinGame >= 6 && playerOneWinGame <= 4) || winSetWithAdvantage()) {
                winSet(player);
            }
        }
    }

    private void winSet(Player player) {
        if (player.getName().equals(playerOne.getName())) {
            playerOneSets++;
        } else {
            playerTwoSets++;
        }

        if (playerOneSets == 2 || playerTwoSets == 2)
            winMatch(player);
    }

    private String winMatch(Player player) {
        matchWinner = player;
        return player.getName() + " win match !";
    }

    public boolean winSetWithAdvantage() {
        if (playerOneWinGame >= 5 && playerOneWinGame >= playerTwoWinGame + 2)
            return true;
        return playerTwoWinGame >= 5 && playerTwoWinGame >= playerOneWinGame + 2;
    }
}

package com.formation.m2i.tennis_kata.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tennis counter should")
class TennisScoreCounterTest {

    private TennisScoreCounter counter;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    void setUp() {
        counter = new TennisScoreCounter();
        playerOne = Player.builder().name("PlayerOne").build();
        playerTwo = Player.builder().name("PlayerTwo").build();
    }

    @Test
    @DisplayName("game is not null")
    void verify_game_not_null() {
        Game game = counter.createAGame();
        assertThat(game).isNotNull();
    }

    @Test
    @DisplayName("game is created")
    void create_a_game() {
        Game game = counter.createAGame();
        assertThat(game).isInstanceOf(Game.class);
    }

    @Test
    @DisplayName("game is created with players")
    void create_a_game_with_players() {
        Game game = counter.createAGameWithPlayers(playerOne, playerTwo);
        assertThat(game).isInstanceOf(Game.class);
        assertThat(game.getPlayerOne().getName()).isEqualTo("PlayerOne");
        assertThat(game.getPlayerTwo().getName()).isEqualTo("PlayerTwo");
    }

    @Test
    @DisplayName("players have no points")
    void players_have_no_points() {
        assertThat(counter.getScoreOne()).isEqualTo(0);
        assertThat(counter.getScoreTwo()).isEqualTo(0);
    }

    @Test
    @DisplayName("players have no wins")
    void players_have_no_wins() {
        assertThat(counter.getPlayerOneWinGame()).isEqualTo(0);
        assertThat(counter.getPlayerTwoWinGame()).isEqualTo(0);
    }

    @Test
    @DisplayName("notify when player scored a point")
    void notify_when_player_scored_a_point() {
        assertThat(counter.notifyPlayersPoint(playerOne)).isEqualTo("PlayerOne has scored a point");
        assertThat(counter.notifyPlayersPoint(playerTwo)).isEqualTo("PlayerTwo has scored a point");
    }

    @Test
    @DisplayName("player score a point")
    void player_score_a_point() {
        counter.scoreAPoint(playerOne);
        assertThat(counter.getScoreOne()).isEqualTo(1);
        counter.scoreAPoint(playerOne);
        assertThat(counter.getScoreOne()).isEqualTo(2);
        counter.scoreAPoint(playerOne);
        assertThat(counter.getScoreOne()).isEqualTo(3);
        counter.scoreAPoint(playerTwo);
        assertThat(counter.getScoreTwo()).isEqualTo(1);
        counter.scoreAPoint(playerTwo);
        assertThat(counter.getScoreTwo()).isEqualTo(2);
        counter.scoreAPoint(playerTwo);
        assertThat(counter.getScoreTwo()).isEqualTo(3);
    }

    @Test
    @DisplayName("give advantage to player")
    void give_advantage_to_player() {
        for (int i = 0; i < 3; i++) {
            counter.scoreAPoint(playerOne);
            counter.scoreAPoint(playerTwo);
        }
        assertThat(counter.getScore()).isEqualTo("Deuce");
        counter.scoreAPoint(playerOne);
        assertThat(counter.getScore()).isEqualTo("Advantage to " + playerOne.getName());
        counter.scoreAPoint(playerTwo);
        assertThat(counter.getScore()).isEqualTo("Deuce");
        counter.scoreAPoint(playerTwo);
        assertThat(counter.getScore()).isEqualTo("Advantage to " + playerTwo.getName());
    }

    @Test
    @DisplayName("give win to player")
    void give_win_to_player() {
        for (int i = 0; i < 4; i++) {
            counter.scoreAPoint(playerOne);
        }
        assertThat(counter.getScore()).isEqualTo(playerOne.getName() + " has won the game");
    }

    @Test
    @DisplayName("reset scores")
    void reset_scores() {
        for (int i = 0; i < 4; i++) {
            counter.scoreAPoint(playerOne);
        }
        counter.getScore();
        assertThat(counter.getScoreOne()).isEqualTo(0);
        assertThat(counter.getScoreTwo()).isEqualTo(0);
    }

        @Test
        @DisplayName("player one win set")
        void player_one_win_set() {
            counter.setPlayerOneWinGame(5);
            counter.setPlayerTwoWinGame(4);
            for (int i = 0; i < 4; i++) {
                counter.scoreAPoint(playerOne);
                counter.getScore();
            }
            assertThat(counter.getPlayerOneSets()).isEqualTo(1);
        }

    @Test
    @DisplayName("player one win set with advantage")
    void player_one_win_set_with_advantage() {
        counter.setPlayerOneWinGame(7);
        counter.setPlayerTwoWinGame(5);
        counter.getScore();
        assertThat(counter.getPlayerOneSets()).isEqualTo(1);
    }
}

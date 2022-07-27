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
        assertThat(counter.getPlayerOneWin()).isEqualTo(0);
        assertThat(counter.getPlayerTwoWin()).isEqualTo(0);
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
        counter.notifyPlayersPoint(playerOne);
        assertThat(counter.getScoreOne()).isEqualTo(15);
        counter.notifyPlayersPoint(playerOne);
        assertThat(counter.getScoreOne()).isEqualTo(30);
        counter.notifyPlayersPoint(playerOne);
        assertThat(counter.getScoreOne()).isEqualTo(40);
        counter.notifyPlayersPoint(playerTwo);
        assertThat(counter.getScoreTwo()).isEqualTo(15);
        counter.notifyPlayersPoint(playerTwo);
        assertThat(counter.getScoreTwo()).isEqualTo(30);
        counter.notifyPlayersPoint(playerTwo);
        assertThat(counter.getScoreTwo()).isEqualTo(40);
    }
}

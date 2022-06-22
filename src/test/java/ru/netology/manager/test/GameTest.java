package ru.netology.manager.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exeption.NotRegisteredException;
import ru.netology.manager.Game;

import java.util.ArrayList;
import java.util.Collection;

public class GameTest {

    Player player1 = new Player(1, "Ignat", 100);
    Player player2 = new Player(2, "Trinity", 65);
    Player player3 = new Player(3, "Kirill", 90);
    Player player4 = new Player(4, "Sakura", 65);
    Player player5 = new Player(5, "Mefodii", 50);
    Player player6 = new Player(6, "Yennefer", 95);

    Game game = new Game();

    @Test
    public void findVictoryOfFirstPlayer() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);
        game.findAll();
        game.findByName("Ignat");

        int actual = game.round("Ignat", "Trinity");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void findVictoryOfSecondPlayer() {
        Collection<Player> players = new ArrayList<>();

        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);
        game.registerAll(players);
        game.findAll();
        game.findByName("Mefodii");
        game.round("Mefodii", "Yennefer");

        int actual = game.round("Mefodii", "Yennefer");
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void exclusionFirstPlayer() {
        game.findAll();
        game.findByName("Mefodii");

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Mefodii", "Yennefer");
        });
    }

    @Test
    public void exclusionSecondPlayer() {
        game.register(player1);
        game.findAll();

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Ignat", "Trinity");
        });
    }

    @Test
    public void drawResult() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);
        game.findAll();
        game.findByName("Ignat");

        int actual = game.round("Trinity", "Sakura");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void notRegistered() {
        game.register(player1);
        game.register(player2);
        game.register(player4);
        game.register(player5);
        game.register(player6);
        game.findAll();
        game.findByName("Ignat");

        int actual = game.findByStrength("Kirill");
        int expected = 0;
        assertEquals(expected, actual);
    }
}
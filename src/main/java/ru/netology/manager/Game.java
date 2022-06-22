package ru.netology.manager;

import ru.netology.domain.Player;
import ru.netology.exeption.NotRegisteredException;

import java.util.ArrayList;
import java.util.Collection;

public class Game {

    private Collection<Player> players;

    public Game() {
        this.players = new ArrayList<>();
    }

    public void register(Player player) {
        players.add(player);
    }

    public void registerAll(Collection<Player> players) {
        this.players.addAll(players);
    }

    public Collection<Player> findAll() {
        return players;
    }

    public Player[] findByName(String name) {
        for (Player player : players) {
            if (player.getName() == name) {
                return new Player[]{player};
            }
        }
        return null;
    }

    public int findByStrength(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player.getStrength();
            }
        }
        return 0;
    }

    public int round(String playerName1, String playerName2) {
        if (findByName(playerName1) == null) {
            throw new NotRegisteredException("Element with name: " + playerName1 + " not found");
        }
        if (findByName(playerName2) == null) {
            throw new NotRegisteredException("Element with name: " + playerName2 + " not found");
        }
        if (findByStrength(playerName1) > findByStrength(playerName2)) {
            return 1;
        }
        if (findByStrength(playerName1) < findByStrength(playerName2)) {
            return 2;
        } else {
            return 0;
        }
    }
}
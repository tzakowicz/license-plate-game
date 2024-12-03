package net.gilmor.plate.model;

import net.gilmor.plate.scoring.ScoreSettings;

/**
 * A marshallable class which stores required settings for the start of a game.
 */
public class GameSettings {

    private String[] players;
    private String startingTerritory;
    private ScoreSettings scoreSettings;

    public String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public String getStartingTerritory() {
        return startingTerritory;
    }

    public void setStartingTerritory(String startingTerritory) {
        this.startingTerritory = startingTerritory;
    }

    public ScoreSettings getScoreSettings() {
        return scoreSettings;
    }

    public void setScoreSettings(ScoreSettings scoreSettings) {
        this.scoreSettings = scoreSettings;
    }

}

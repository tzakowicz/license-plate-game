package net.gilmor.plate.model;

public class Scoreboard {

    private PlayerScore[] scores;

    public Scoreboard(PlayerScore... scores) {
        this.scores = scores;
    }

    public PlayerScore[] getScores() {
        return scores;
    }

    public void setScores(PlayerScore[] scores) {
        this.scores = scores;
    }

}

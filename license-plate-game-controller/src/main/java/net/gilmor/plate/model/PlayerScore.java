package net.gilmor.plate.model;

public class PlayerScore {

    private int id;
    private String name;
    private int score;
    
    public PlayerScore(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
}

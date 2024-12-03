package net.gilmor.plate.model;

public class SightingClaim {

    private int id;
    private String code;
    private boolean penalty = false;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }
    
    public boolean isPenalty() {
        return penalty;
    }
}

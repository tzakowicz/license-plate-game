package net.gilmor.plate.scoring;

/**
 * A class to store settings related to scoring
 * 
 * @author Thomas Zakowicz
 */
public class ScoreSettings {

    private int territoryBorderScore = 1;
    private int countryBorderScore = 5;
    private int oceanBorderScore = 10;

    public void setTerritoryBorderScore(int territoryBorderScore) {
        this.territoryBorderScore = territoryBorderScore;
    }

    public int getTerritoryBorderScore() {
        return territoryBorderScore;
    }

    public void setCountryBorderScore(int countryBorderScore) {
        this.countryBorderScore = countryBorderScore;
    }

    public int getCountryBorderScore() {
        return countryBorderScore;
    }

    public void setOceanBorderScore(int oceanBorderScore) {
        this.oceanBorderScore = oceanBorderScore;
    }

    public int getOceanBorderScore() {
        return oceanBorderScore;
    }
}

package net.gilmor.plate.scoring;

import net.gilmor.plate.model.Crossing;
import net.gilmor.plate.model.Path;

public class PathScoreCard implements ScoreCard {

    private ScoreSettings settings;

    public PathScoreCard(ScoreSettings settings) {
        this.settings = settings;
    }

    @Override
    public ScoreSettings getSettings() {
        return settings;
    }

    @Override
    public int calculateScore(Path path) {
        int value = 0;
        for (Crossing xing : path.getCrossings()) {
            value += calculateScore(xing);
        }
        return value;
    }

    @Override
    public int calculateScore(Crossing c) {
        if (c.isOcean())
            return settings.getOceanBorderScore();
        if (c.isCountry())
            return settings.getCountryBorderScore();
        else
            return settings.getTerritoryBorderScore();
    }

}

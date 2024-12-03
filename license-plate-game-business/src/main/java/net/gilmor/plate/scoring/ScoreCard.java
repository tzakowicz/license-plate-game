package net.gilmor.plate.scoring;

import net.gilmor.plate.model.Crossing;
import net.gilmor.plate.model.Path;

public interface ScoreCard {

    ScoreSettings getSettings();

    int calculateScore(Path path);

    int calculateScore(Crossing c);
}

package net.gilmor.plate.scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ScoreSettingsTest {

    @Test
    void testScoreSettings() {
        final int T = 1;
        final int C = 2;
        final int O = 3;
        ScoreSettings settings = new ScoreSettings();
        settings.setTerritoryBorderScore(T);
        settings.setCountryBorderScore(C);
        settings.setOceanBorderScore(O);
        assertEquals(T, settings.getTerritoryBorderScore());
        assertEquals(C, settings.getCountryBorderScore());
        assertEquals(O, settings.getOceanBorderScore());
    }
}

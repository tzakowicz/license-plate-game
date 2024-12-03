package net.gilmor.plate.bean;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.resource.spi.IllegalStateException;
import net.gilmor.plate.model.GameSettings;
import net.gilmor.plate.model.PlayerScore;
import net.gilmor.plate.model.SightingClaim;
import net.gilmor.plate.scoring.ScoreSettings;

public class GameBeanTest {

    static String[] players;
    static ScoreSettings scoreSettings;

    GameBean bean;

    @BeforeAll
    static void setupClass() {
        players = new String[] { "Player 1", "Player 2", "Player 3" };
        scoreSettings = new ScoreSettings();
        scoreSettings.setTerritoryBorderScore(1);
        scoreSettings.setCountryBorderScore(5);
        scoreSettings.setOceanBorderScore(10);
    }

    @BeforeEach
    void setupTest() {
        bean = new GameBean();
    }

    @Test
    void testGameBean() throws IllegalStateException {
        GameSettings settings = new GameSettings();
        settings.setPlayers(players);
        settings.setStartingTerritory("CT");
        settings.setScoreSettings(scoreSettings);
        assertThat(bean.initialize(settings), equalTo(true));
        bean.start();
        addSighting(bean, 0, "WA");
        addSighting(bean, 1, "GA");
        addSighting(bean, 2, "FL");
        for (PlayerScore score : bean.scoreboard().getScores()) {
            assertThat(score.getScore(), greaterThan(0));
        }
        addSighting(bean, 0, "WA");
        String[] path = bean.lastSighting().getPath();
        assertThat(path[0], equalTo("CT"));
        assertThat(path[path.length - 1], equalTo("WA"));
        assertThat(bean.lastSighting().getPoints(), lessThan(0));
        addSighting(bean, 1, "GA");
        addSighting(bean, 2, "FL");
        for (PlayerScore score : bean.scoreboard().getScores()) {
            assertThat(score.getScore(), equalTo(0));
        }
        bean.crossBorder("MA");
        bean.end();
    }

    void addSighting(GameBean bean, int userId, String code) {
        SightingClaim claim = new SightingClaim();
        claim.setId(userId);
        claim.setCode(code);
        bean.addSighting(claim);
    }
}

package net.gilmor.plate.maps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TerritoryMapTest {

    @Test
    void test_buildMap() {
        TerritoryMap map = TerritoryMap.buildMap();
        assertTrue(map.getCountries().size() > 0);
    }

    @Test
    void test_getTerritories() {
        TerritoryMap map = TerritoryMap.buildMap();
        assertTrue(map.territoryMap().size() > 0);
        assertTrue(map.territoryMap().keySet().size() > 0);
    }

    @Test
	void testNoResult() {
        TerritoryMap map = TerritoryMap.buildMap();
        assertFalse(map.getTerritory("XE").isPresent());
	}
}

package net.gilmor.plate.maps;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.gilmor.plate.model.Country;
import net.gilmor.plate.model.Territory;

public interface GameMap {

    List<Country> getCountries();

    Map<String, Territory> territoryMap();

    Optional<Territory> getTerritory(String code);

    Optional<Country> getCountry(String code);
}

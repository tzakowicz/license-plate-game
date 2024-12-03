package net.gilmor.plate.maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;

import net.gilmor.plate.model.Country;
import net.gilmor.plate.model.Territory;

public class TerritoryMap implements GameMap {

    private List<Country> countries;
    private Map<String, Territory> territoryMap;

    protected TerritoryMap() {
    }

    public static TerritoryMap buildMap() {
        return buildMap("territories.json");
    }

    public static TerritoryMap buildMap(String fileName) {
        TerritoryMap map = null;
        try (InputStream jsonStream = TerritoryMap.class.getResource(fileName).openStream();) {
            Mapper mapper = new MapperBuilder().build();
            map = mapper.readObject(jsonStream, TerritoryMap.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Territories file cannot be read");
        }
        return map;
    }

    /**
     * @return the countries
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    private Stream<Territory> streamTerritories() {
        return countries.parallelStream().<Territory>flatMap((country) -> {
            return country.getTerritories().stream();
        });
    }

    @Override
    public Map<String, Territory> territoryMap() {
        if (territoryMap == null) {
            territoryMap = streamTerritories().collect(Collectors.toMap(Territory::getCode, Function.identity()));
        }
        return territoryMap;
    }

    @Override
    public Optional<Territory> getTerritory(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Territory code must not be null");
        }
        return streamTerritories().filter(area -> area.getCode().equals(code)).findFirst();
    }

    @Override
    public Optional<Country> getCountry(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Country code must not be null");
        }
        return countries.parallelStream().filter(area -> area.getCode().equals(code)).findFirst();
    }

}

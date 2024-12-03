package net.gilmor.plate.model;

public class MapSummary {

    private CountrySummary[] countries;

    public MapSummary(CountrySummary... countries) {
        this.countries = countries;
    }

    public CountrySummary[] countries() {
        return countries;
    }

}

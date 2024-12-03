package net.gilmor.plate.model;

import java.util.ArrayList;
import java.util.List;

public class CountrySummary {

    private String code;
    private List<TerritorySummary> territories;

    public CountrySummary(String code) {
        this.code = code;
        territories = new ArrayList<>(20);
    }

    public String code() {
        return code;
    }

    public void addState(TerritorySummary state) {
        territories.add(state);
    }

    public List<TerritorySummary> territories() {
        return territories;
    }
}

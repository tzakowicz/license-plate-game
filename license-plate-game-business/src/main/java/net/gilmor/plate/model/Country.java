package net.gilmor.plate.model;

import java.util.List;

public class Country extends Area {

    private List<Territory> territories;

    /**
     * @return the territories
     */
    public List<Territory> getTerritories() {
        return territories;
    }

    /**
     * @param territories the territories to set
     */
    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }
}

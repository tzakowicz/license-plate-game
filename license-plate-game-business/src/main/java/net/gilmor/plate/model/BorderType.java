package net.gilmor.plate.model;

public class BorderType {

    private boolean country;
    private boolean ocean;

    /**
     * @return the country
     */
    public boolean isCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(boolean country) {
        this.country = country;
    }

    /**
     * @return the ocean
     */
    public boolean isOcean() {
        return ocean;
    }

    /**
     * @param ocean the ocean to set
     */
    public void setOcean(boolean ocean) {
        this.ocean = ocean;
    }

}

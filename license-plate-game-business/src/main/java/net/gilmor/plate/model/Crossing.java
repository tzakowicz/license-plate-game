package net.gilmor.plate.model;

public class Crossing {

    private Area from;
    private Area to;
    private boolean country;
    private boolean ocean;

    public Crossing(Area from, Area to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        if (from.equals(to)) {
            throw new IllegalArgumentException("Arguments cannot be the same");
        }
        BorderType type = from.getBorders().get(to.getCode());
        if (type == null) {
            throw new IllegalArgumentException(String.format("%s does not border %s", to.getName(), from.getName()));
        }
        this.from = from;
        this.to = to;
        this.country = type.isCountry();
        this.ocean = type.isOcean();
    }

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

    public Area getStart() {
        return from;
    }

    /**
     * @param start the first to set
     */
    public void setStart(Area start) {
        this.from = start;
    }

    public Area getEnd() {
        return to;
    }

    /**
     * @param end the second to set
     */
    public void setEnd(Area end) {
        this.to = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj.getClass() != this.getClass())
            return false;

        Crossing comp = (Crossing) obj;
        if (!getStart().equals(comp.getStart()) && !getStart().equals(comp.getEnd())) {
            return false;
        }
        if (getStart().equals(comp.getStart())) {
            if (!getEnd().equals(comp.getEnd())) {
                return false;
            }
        } else if (getStart().equals(comp.getEnd())) {
            if (!getEnd().equals(comp.getStart())) {
                return false;
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return from + " -> " + to;
    }

}

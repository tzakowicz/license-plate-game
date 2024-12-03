package net.gilmor.plate.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Area {

    private String code;
    private String name;

    private Map<String, BorderType> borders = new HashMap<>();

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the borders
     */
    public Map<String, BorderType> getBorders() {
        return borders;
    }

    /**
     * @return the codes for the bordering states
     */
    public Set<String> getBorderCodes() {
        return getBorders().keySet();
    }

    /**
     * @param borders the borders to set
     */
    public void setBorders(Map<String, BorderType> borders) {
        this.borders = borders;
    }

    public void addBorder(String code, boolean isCountry, boolean isOcean) {
        BorderType type = new BorderType();
        type.setCountry(isCountry);
        type.setOcean(isOcean);
        this.borders.put(code, type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Area other = (Area) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return code + " (" + name + ")";
    }

}

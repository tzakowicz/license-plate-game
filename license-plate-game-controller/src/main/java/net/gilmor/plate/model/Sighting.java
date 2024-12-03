package net.gilmor.plate.model;

public class Sighting {

    private String name;
    private int points;
    private String[] path;

    public Sighting(String name, int points, String[] path) {
        this.name = name;
        this.points = points;
        this.path = path;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return the path
     */
    public String[] getPath() {
        return path;
    }

}

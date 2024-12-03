package net.gilmor.plate.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Crossing> crossings;

    public Path() {
        crossings = new ArrayList<>();
    }

    public Path(List<Crossing> crossings) {
        this.crossings = crossings;
    }

    public List<Crossing> getCrossings() {
        return crossings;
    }

    public void setCrossings(List<Crossing> crossings) {
        this.crossings = crossings;
    }

    public void addStart(Crossing crossing) {
        if (crossings.size() > 0 && (!getStart().getCode().equals(crossing.getEnd().getCode()))) {
            throw new InvalidParameterException("Crossing " + crossing.toString()
                    + " cannot be added to the start of the path " + getStart().toString());
        }
        crossings.add(0, crossing);
    }

    public Area getStart() {
        return crossings.get(0).getStart();
    }

    public Path addEnd(Crossing crossing) {
        if (crossings.size() > 0 && (!getEnd().getCode().equals(crossing.getStart().getCode()))) {
            throw new InvalidParameterException("Crossing " + crossing.toString()
                    + " cannot be added to the end of the path " + getEnd().toString());
        }
        crossings.add(crossing);
        return this;
    }

    public Path addEnd(Area area) {
        if (area.getBorderCodes().contains(getEnd().getCode())) {
            crossings.add(new Crossing(getEnd(), area));
        }
        return this;
    }

    public Area getEnd() {
        return crossings.get(crossings.size() - 1).getEnd();
    }

    public Path mergePath(Path toMerge) {
        for (Crossing crossing : toMerge.crossings) {
            addEnd(crossing);
        }
        return this;
    }

    public boolean hasExplored(Area area) {
        for (Crossing crossing : crossings) {
            if (crossing.getStart().equals(area) || crossing.getEnd().equals(area)) {
                return true;
            }
        }
        return false;
    }
}

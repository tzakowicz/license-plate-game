package net.gilmor.plate.pathfinder;

import net.gilmor.plate.model.Path;

public interface WayFinder {

    Path findWay(String from, String to);
}

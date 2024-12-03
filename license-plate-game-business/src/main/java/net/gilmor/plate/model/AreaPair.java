package net.gilmor.plate.model;

public class AreaPair extends Pair<String> {

    public AreaPair(String a, String b) {
        super(a, b);
    }

    public AreaPair(Area a, Area b) {
        super(a.getCode(), b.getCode());
    }

}

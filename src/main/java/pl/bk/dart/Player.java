package pl.bk.dart;

class Player {

    private int points;
    private final String name;
    private final static int POINTS_ADDED_AT_START = 501;

    public Player(String name) {
        this.name = name;
        points = POINTS_ADDED_AT_START;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPoints() {
        return points;
    }

    public void subtractPoints(int pointToSubtract){
        points -= pointToSubtract;
    }
}

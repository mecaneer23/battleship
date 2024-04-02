public class Ship {
    enum Direction {
        UNSET,
        HORIZONTAL,
        VERTICAL,
    }

    private int row;
    private int col;
    private int length;
    private Direction direction;

    public Ship(int length) {
        this.length = length;
    }

    public boolean isLocationSet() {
        return this.row > -1 && this.col > -1;
    }

    public boolean isDirectionSet() {
        return this.direction != Direction.UNSET;
    }

    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getLength() {
        return this.length;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    private String directionToString() {
        return this.direction.toString();
    }

    private String locationToString() {
        return "(" + this.row + ", " + this.col + ")";
    }

    public String toString() {
        return "Ship with length " + this.length + " at " + this.locationToString() + " with direction " + this.directionToString();
    }

}
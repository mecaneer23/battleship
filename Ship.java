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

    public Ship(int row, int col, int length, Direction direction) {
        this.row = row;
        this.col = col;
        this.length = length;
        this.direction = direction;
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

    public Direction getDirection() {
        return this.direction;
    }
}
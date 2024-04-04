public class Grid {
    private Location[][] grid;

    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;

    public Grid() {
        this.grid = new Location[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                this.grid[i][j] = new Location();
            }
        }
    }

    public void markHit(int row, int col) {
        this.grid[row][col].markHit();
    }

    public void markMiss(int row, int col) {
        this.grid[row][col].markMiss();
    }

    public Location.Status getStatus(int row, int col) {
        return this.grid[row][col].getStatus();
    }

    public boolean hasShip(int row, int col) {
        return this.grid[row][col].hasShip();
    }

    enum PrintType {
        STATUS,
        SHIPS,
    }

    private void printBoard(PrintType type) {
        System.out.print(" ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int row = 0; row < NUM_ROWS; row++) {
            System.out.print((char) (row + 65));
            for (int col = 0; col < NUM_COLS; col++) {
                System.out.print(" ");
                switch (type) {
                    case STATUS:
                        switch (getStatus(row, col)) {
                            case UNGUESSED:
                                System.out.print("-");
                                break;
                            case HIT:
                                System.out.print("X");
                                break;
                            case MISSED:
                                System.out.print("O");
                                break;
                        }
                        break;
                    case SHIPS:
                        System.out.print(hasShip(row, col) ? "X" : "-");
                        break;
                }
            }
            System.out.println();
        }
    }

    public void printStatus() {
        printBoard(PrintType.STATUS);
    }

    public void printShips() {
        printBoard(PrintType.SHIPS);
    }

    public void addShip(Ship ship) {
        for (int i = 0; i < ship.getLength(); i++) {
            if (ship.getDirection() == Ship.Direction.HORIZONTAL) {
                this.grid[ship.getRow()][ship.getCol() + i].setShip(true);
            } else if (ship.getDirection() == Ship.Direction.VERTICAL) {
                this.grid[ship.getRow() + i][ship.getCol()].setShip(true);
            }
        }
    }
}

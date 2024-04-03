public class Player {
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};

    private Grid grid;

    public Player() {
        this.grid = new Grid();
    }

    public void addShip(Ship ship) {
        this.grid.addShip(ship);
    }

    public void printMyShips() {
        this.grid.printShips();
    }

    public void printOpponentGuesses() {

    }

    public void printMyGuesses() {
        this.grid.printStatus();
    }

    public boolean recordOpponentGuess(int row, int col) {
        return false;
    }
}
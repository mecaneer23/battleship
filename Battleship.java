import java.util.Scanner;

public class Battleship {
    public static final int[] SHIP_LENGTHS = { 2, 3, 3, 4, 5 };
    public static int TOTAL_LENGTH;
    private static Scanner scan = new Scanner(System.in);

    private static void setTotalLength() {
        int sum = 0;
        for (int length : SHIP_LENGTHS) {
            sum += length;
        }
        TOTAL_LENGTH = sum;
    }

    public static void main(String[] args) {
        setTotalLength();
        Grid player = new Grid();
        Grid computer = new Grid();
        System.out.println("======================");
        System.out.println("Welcome to Battleship!");
        System.out.println("======================");

        placeUserShips(player);
        placeComputerShips(computer);

        int playerHits = 0, computerHits = 0;
        while (playerHits < TOTAL_LENGTH && computerHits < TOTAL_LENGTH) {
            if (playerGuess(computer)) {
                playerHits++;
            }
            System.out.print("Press enter for the computer to guess...");
            scan.nextLine();
            if (computerGuess(player)) {
                computerHits++;
            }
            System.out.println("Your total hits: " + playerHits + "/" + TOTAL_LENGTH);
            System.out.println("Computer's total hits: " + computerHits + "/" + TOTAL_LENGTH);
            System.out.print("Press enter to guess...");
            scan.nextLine();
        }
        System.out.println("You win!");
        System.out.println("Thanks for playing.");
        scan.close();
    }

    private static void placeUserShips(Grid player) {
        // TODO: add input validation/ask again for invalid input and allow lowercase input
        System.out.println("Choose the location for your ships...");
        System.out.print("Your ships are the following lengths: { ");
        for (int len : SHIP_LENGTHS) {
            System.out.print(len + " ");
        }
        System.out.println("}");
        int row, col;
        Ship.Direction direction;

        for (int len : SHIP_LENGTHS) {
            System.out.println("Your current grid:");
            player.printShips();
            System.out.println("\nPlace a ship with a length of " + len);
            System.out.print("Which row? (A-J): ");
            row = (int) scan.nextLine().charAt(0) - 65;
            System.out.print("Which column? (1-10): ");
            col = scan.nextInt() - 1;
            scan.nextLine();
            System.out.print("Horizontal or vertical? (h/v): ");
            switch (scan.nextLine()) {
                case "h":
                    direction = Ship.Direction.HORIZONTAL;
                    break;
                case "v":
                    direction = Ship.Direction.VERTICAL;
                    break;
                default:
                    direction = Ship.Direction.UNSET;
                    break;
            }
            player.addShip(new Ship(row, col, len, direction));
        }
        System.out.println("Your current grid:");
        player.printShips();
    }

    private static boolean isValidShipLocation(Grid grid, int row, int col, int len, Ship.Direction direction) {
        for (int i = 0; i < len; i++) {
            if (
                (direction == Ship.Direction.HORIZONTAL && grid.hasShip(row, col + i))
                || (direction == Ship.Direction.VERTICAL && grid.hasShip(row + i, col))
            ) {
                return false;
            }
        }
        return true;
    }

    private static void placeComputerShips(Grid computer) {
        System.out.println("Computer is placing their ships...");

        int row, col;
        Ship.Direction direction;

        for (int len : SHIP_LENGTHS) {
            while (true) {
                if (random(0, 2) == 0) {
                    direction = Ship.Direction.HORIZONTAL;
                    row = random(0, 10);
                    col = random(0, 10 - len);
                } else {
                    direction = Ship.Direction.VERTICAL;
                    row = random(0, 10 - len);
                    col = random(0, 10);
                }
                if (isValidShipLocation(computer, row, col, len, direction)) {
                    break;
                }
            }
            computer.addShip(new Ship(row, col, len, direction));
        }
    }

    /**
     * Min is exclusive, max is inclusive.
     */
    private static int random(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    private static boolean computerGuess(Grid player) {
        int row = random(0, 10);
        int col = random(0, 10);
        boolean output;
        System.out.format("Computer guessed (%c, %d)\n", (char) (row + 65), col + 1);
        if (player.hasShip(row, col)) {
            System.out.println("Computer hit.");
            player.markHit(row, col);
            output = true;
        } else {
            System.out.println("Computer missed.");
            player.markMiss(row, col);
            output = false;
        }
        System.out.print("Press enter to view the computer's guesses...");
        scan.nextLine();
        System.out.println("Here are the computer's current guesses:");
        player.printStatus();
        return output;
    }

    private static boolean playerGuess(Grid computer) {
        System.out.println("You have guessed the following:");
        computer.printStatus();
        int row, col;
        while (true) {
            System.out.println("Please enter your guess:");
            System.out.print("Which row? (A-J): ");
            row = (int) scan.nextLine().charAt(0) - 65;
            if (row < 0 || row > 10) {
                System.out.println("Invalid row: make sure you input a letter between A and J");
                continue;
            }
            System.out.print("Which column? (1-10): ");
            col = scan.nextInt() - 1;
            scan.nextLine();
            if (col < 0 || col > 10) {
                System.out.println("Invalid column: make sure you input a number between 0 and 10");
                continue;
            }
            break;
        }
        boolean output;
        if (computer.hasShip(row, col)) {
            System.out.println("Nice! You got a hit!");
            computer.markHit(row, col);
            output = true;
        } else {
            System.out.println("Nope, that was a miss.");
            computer.markMiss(row, col);
            output = false;
        }
        System.out.print("Press enter to view your guesses...");
        scan.nextLine();
        System.out.println("Here are your current guesses:");
        computer.printStatus();
        return output;
    }
}
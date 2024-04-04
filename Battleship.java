import java.util.Scanner;

public class Battleship {
    public static final int[] SHIP_LENGTHS = { 2, 3, 3, 4, 5 };
    public static int TOTAL_LENGTH;

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
            System.out.println("Total hits: " + playerHits + "/" + TOTAL_LENGTH);
            if (playerGuess(computer)) {
                playerHits++;
            }
            if (computerGuess(player)) {
                computerHits++;
            }
        }
        System.out.println("You win!");
        System.out.println("Thanks for playing.");
    }

    private static void placeUserShips(Grid player) {
        // TODO: add input validation
        System.out.println("Choose the location for your ships...");
        System.out.print("Your ships are the following lengths: { ");
        for (int len : SHIP_LENGTHS) {
            System.out.print(len + " ");
        }
        System.out.println("}");
        Scanner scan = new Scanner(System.in);
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
        scan.close();
    }

    private static void placeComputerShips(Grid computer) {
        System.out.println("placing computer ships is not yet implemented");
    }

    /**
     * Min is exclusive, max is inclusive.
     */
    private static int random(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    private static boolean computerGuess(Grid player) {
        int row = random(1, 10);
        int col = random(1, 10);
        boolean output;
        if (player.hasShip(row, col)) {
            System.out.println("Computer hit.");
            player.markHit(row, col);
            output = true;
        } else {
            System.out.println("Computer missed.");
            player.markMiss(row, col);
            output = false;
        }
        player.printStatus();
        return output;
    }

    private static boolean playerGuess(Grid computer) {
        System.out.println("You have guessed the following:");
        computer.printStatus();
        Scanner scan = new Scanner(System.in);
        int row, col;
        while (true) {
            System.out.print("Please enter your guess:\nRow: ");
            row = (int) scan.nextLine().charAt(0) - 65;
            if (row < 0 || row > 10) {
                System.out.println("Invalid row: make sure you input a letter between A and J");
                continue;
            }
            System.out.print("Column: ");
            col = scan.nextInt();
            scan.nextLine();
            if (col < 1 || col > 10) {
                System.out.println("Invalid column: make sure you input a number between 0 and 10");
                continue;
            }
            scan.close();
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
        computer.printStatus();
        return output;
    }
}
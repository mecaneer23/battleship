import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        Player user = new Player();
        Player computer = new Player();
        System.out.println("======================");
        System.out.println("Welcome to Battleship!");
        System.out.println("======================");

        placeUserShips(user);
        placeComputerShips(computer);
    }

    private static void placeUserShips(Player user) {
        System.out.println("Choose the location for your ships...");
        System.out.print("Your ships are the following lengths: { ");
        for (int len : Player.SHIP_LENGTHS) {
            System.out.print(len + " ");
        }
        System.out.println("}");
        Scanner scan = new Scanner(System.in);
        int row, col;
        Ship.Direction direction;

        for (int len : Player.SHIP_LENGTHS) {
            System.out.println("Your current grid:");
            user.printMyShips();
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
            user.addShip(new Ship(row, col, len, direction));
        }
        scan.close();
    }

    private static void placeComputerShips(Player computer) {
        throw new Exception("Not implemented");
    }

    private static void askForGuess(Player user, Player computer) {
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
        
    }
}
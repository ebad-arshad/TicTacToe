import java.util.Scanner;

public class TicTacToe {
    Scanner sc = new Scanner(System.in);
    final char[][] possibilities = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    char[] playerPicks = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int counter = 0;
    char XorO = 'X';
    String p1;
    String p2;
    boolean isGameEnd = false;

    TicTacToe() {
        this.p1 = p1Name();
        this.p2 = p2Name();
        gameStart();
    }

    void gameStart() {
        for (int i = 0; i < playerPicks.length; i++) {
            int XorO_pick = playerTurn();
            while (playerPicks[XorO_pick - 1] == 'X' || playerPicks[XorO_pick - 1] == 'O') {
                System.out.println(XorO_pick + " is already taken by " + playerPicks[XorO_pick - 1]);
                XorO_pick = playerTurn();
            }
            playerPicks[XorO_pick - 1] = XorO;
            counter++;
            if (counter >= 5) {
                checkPossibilities();
            }
            if (isGameEnd) {
                gameEnd();
                return;
            }
            ;
            XorO = XorO == 'X' ? 'O' : 'X';
        }
        System.out.println("Player 1 (X): " + p1 + " & Player 2 (O): " + p2 + "\nGood luck you both!");
    }

    void gameEnd() {
        showGame();
        String winner = XorO == 'X' ? p1 : p2;
        System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
    }

    String p1Name() {
        System.out.print("Please enter your name (X): ");
        String p1 = sc.nextLine();
        while (p1.isEmpty()) {
            System.out.print("Something went wrong. Please use a valid name: ");
            p1 = sc.nextLine();
        }
        return p1;
    }

    String p2Name() {
        System.out.print("Please enter your name (O): ");
        String p2 = sc.nextLine();
        while (p2.isEmpty()) {
            System.out.print("Something went wrong. Please use a valid name: ");
            p2 = sc.nextLine();
        }
        return p2;
    }

    int playerTurn() {
        showGame();
        System.out.println("Pick a number.");
        System.out.print(XorO + " turn: ");
        return sc.nextInt();
    }

    void checkPossibilities() {
        for (char[] possibility : possibilities) {
            if (
                    playerPicks[possibility[0] - 1] == XorO &&
                            playerPicks[possibility[1] - 1] == XorO &&
                            playerPicks[possibility[2] - 1] == XorO
            ) {
                isGameEnd = true;
                break;
            }
        }
    }

    void showGame() {
        System.out.println("|---|---|---|");
        System.out.println("| " + playerPicks[0] + " | " + playerPicks[1] + " | " + playerPicks[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + playerPicks[3] + " | " + playerPicks[4] + " | " + playerPicks[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + playerPicks[6] + " | " + playerPicks[7] + " | " + playerPicks[8] + " |");
        System.out.println("|---|---|---|");
    }
}
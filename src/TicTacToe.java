import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    Scanner sc = new Scanner(System.in);
    final char[][] possibilities = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    char[] playerPicks = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    ArrayList<Integer> previousTurn = new ArrayList<>();
    int counter = 0;
    char XorO = 'X';
    String p1;
    String p2;
    boolean isGameEnd = false;
    boolean isContinue = false;

    TicTacToe() {
        this.p1 = p1Name();
        this.p2 = p2Name();
        gameStart();
    }

    void gameStart() {
        for (int i = 0; i < playerPicks.length; i++) {
            isContinue = false;
            int XorO_pick = playerTurn();
            while (XorO_pick == -1 && isGameEmpty()) {
                System.out.println("There is no value undo.");
                XorO_pick = playerTurn();
                i = -1;
                XorO = 'X';
            }
            if (XorO_pick == -1 && !isGameEmpty()) {
                undoMove();
                i -= 1;
                continue;
            }
            while (playerPicks[XorO_pick - 1] == 'X' || playerPicks[XorO_pick - 1] == 'O') {
                System.out.println(XorO_pick + " is already taken by " + playerPicks[XorO_pick - 1]);
                XorO_pick = playerTurn();
                if (XorO_pick == -1) {
                    undoMove();
                    i -= 1;
                    isContinue = true;
                    break;
                }
            }
            if (isContinue) continue;
            previousTurn.add(XorO_pick - 1);
            playerPicks[XorO_pick - 1] = XorO;
            counter++;
            if (counter >= 5) checkPossibilities();
            if (isGameEnd) {
                gameEnd();
                return;
            }
            XorO = XorO == 'X' ? 'O' : 'X';
        }
        showGame();
        System.out.println("Game drawn.");
    }

    void undoMove() {
        playerPicks[previousTurn.get(previousTurn.size() - 1)] = ' ';
        previousTurn.remove(previousTurn.size() - 1);
        XorO = XorO == 'X' ? 'O' : 'X';
    }

    boolean isGameEmpty() {
        for (char playerPick : playerPicks) {
            if (playerPick != ' ') {
                return false;
            }
        }
        return true;
    }

    void gameEnd() {
        showGame();
        String winner = XorO == 'X' ? p1 : p2;
        System.out.println("Congratulations! " + winner + " have won! Thanks for playing.");
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
        System.out.println("Pick a number between 1-9");
        System.out.print("Enter " + XorO + " turn or type (-1) if you want to undo a move : ");
        int guess = sc.nextInt();
        while ((guess > 9 || guess < 1) && guess != -1) {
            System.out.println("Please select numbers between 1-9");
            System.out.print("Enter " + XorO + " turn or type (-1) if you want to undo a move : ");
            guess = sc.nextInt();
        }
        return guess;
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
        System.out.println(" " + playerPicks[0] + " | " + playerPicks[1] + " | " + playerPicks[2]);
        System.out.println("---+---+---");
        System.out.println(" " + playerPicks[3] + " | " + playerPicks[4] + " | " + playerPicks[5]);
        System.out.println("---+---+---");
        System.out.println(" " + playerPicks[6] + " | " + playerPicks[7] + " | " + playerPicks[8]);
    }
}
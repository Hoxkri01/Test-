
import java.util.Random;
import java.util.Scanner;

public class TicTacTok {

        public static final char[][]board =new char[][] {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        private static char playerSymbol;
        private static char computerSymbol;
        private static boolean isPlayerTurn;
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            setupGame();
            playGame();
        }

        private static void setupGame() {
            System.out.print("Choose your symbol (X or O): ");
            playerSymbol = scanner.next().toUpperCase().charAt(0);
            computerSymbol = (playerSymbol == 'X') ? 'O' : 'X';
            System.out.println("You are: " + playerSymbol + ", Computer is: " + computerSymbol);
            isPlayerTurn = playerSymbol == 'X';  // Player goes first if they chose 'X'
        }

        private static void playGame() {
            while (true) {
                displayBoard();
                if (isPlayerTurn) {
                    playerMove();
                    if (checkWin(playerSymbol)) {
                        displayBoard();
                        System.out.println("Congratulations! You win.");
                        break;
                    }
                } else {
                    computerMove();
                    if (checkWin(computerSymbol)) {
                        displayBoard();
                        System.out.println("Computer wins.");
                        break;
                    }
                }

                if (isBoardFull()) {
                    displayBoard();
                    System.out.println("The game is a draw.");
                    break;
                }

                // Switch turns
                isPlayerTurn = !isPlayerTurn;
            }
        }

        private static void displayBoard() {
            System.out.println("  0 1 2");
            for (int i = 0; i < 3; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j]);
                    if (j < 2) System.out.print("|");
                }
                System.out.println();
                if (i < 2) System.out.println("  -----");
            }
        }

        private static void playerMove() {
            while (true) {
                System.out.print("Enter row and column (e.g., 1 1): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                    board[row][col] = playerSymbol;
                    break;
                } else {
                    System.out.println("This position is taken or invalid. Try again.");
                }
            }
        }

        private static void computerMove() {
            Random rand = new Random();
            int row, col;
            do {
                row = rand.nextInt(3);
                col = rand.nextInt(3);
            } while (board[row][col] != ' ');
            board[row][col] = computerSymbol;
            System.out.println("Computer placed on (" + row + ", " + col + ")");
        }

        private static boolean checkWin(char symbol) {
            // Check rows and columns
            for (int i = 0; i < 3; i++) {
                if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                        (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                    return true;
                }
            }
            // Check diagonals
            return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                    (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
        }

        private static boolean isBoardFull() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        return false;
                    }
                }
            }
            return true;
        }
}

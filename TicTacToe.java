
import java.util.Scanner;
import java.util.Random;

    public class TicTacToe {
        private char[][] board;
        private char playerSymbol;
        private char computerSymbol;
        private boolean isPlayerTurn;
        private Scanner scanner;

        public TicTacToe() {
            board = new char[3][3];
            scanner = new Scanner(System.in);
            setupGame();
        }

        public static void main(String[] args) {
            TicTacToe game = new TicTacToe();
            game.playGame();
        }

        private void setupGame() {
            // Initialize the board with empty spaces
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                }
            }

            // Allow the player to choose their symbol
            System.out.print("Do you want to be X or O? ");
            playerSymbol = scanner.next().charAt(0);
            computerSymbol = (playerSymbol == 'X') ? 'O' : 'X';

            // Decide who goes first
            System.out.print("Do you want to go first? (yes/no): ");
            isPlayerTurn = scanner.next().equalsIgnoreCase("yes");

            System.out.println("You are " + playerSymbol + " and the computer is " + computerSymbol + ".");
        }

        private void playGame() {
            boolean gameEnded = false;

            while (!gameEnded) {
                displayBoard();

                if (isPlayerTurn) {
                    playerMove();
                    gameEnded = checkWin(playerSymbol) || isBoardFull();
                    isPlayerTurn = false;
                } else {
                    computerMove();
                    gameEnded = checkWin(computerSymbol) || isBoardFull();
                    isPlayerTurn = true;
                }
            }

            displayBoard();

            if (checkWin(playerSymbol)) {
                System.out.println("Congratulations! You won!");
            } else if (checkWin(computerSymbol)) {
                System.out.println("The computer won. Better luck next time!");
            } else {
                System.out.println("It's a tie!");
            }
        }

        private void displayBoard() {
            System.out.println("Board:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(" " + board[i][j]);
                    if (j < 2) System.out.print(" |");
                }
                System.out.println();
                if (i < 2) System.out.println("-----------");
            }
        }

        private void playerMove() {
            int row, col;

            while (true) {
                System.out.print("Enter your move (row and column): ");
                row = scanner.nextInt();
                col = scanner.nextInt();

                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                    board[row][col] = playerSymbol;
                    break;
                } else {
                    System.out.println("This move is not valid.");
                }
            }
        }

        private void computerMove() {
            // Simple AI - random move (could be improved with smarter strategy)
            Random random = new Random();
            int row, col;

            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (board[row][col] != ' ');

            board[row][col] = computerSymbol;
            System.out.println("Computer placed " + computerSymbol + " at (" + row + ", " + col + ")");
        }

        private boolean checkWin(char symbol) {
            // Check rows, columns, and diagonals for a win
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
                if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
            }
            if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
            if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;

            return false;
        }

        private boolean isBoardFull() {
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



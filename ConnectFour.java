import java.util.Scanner;
import java.util.ArrayList;

class ConnectFour {

    String title;
    int[][] board;
    ArrayList<Integer> l;
    int plays;
    int player;
    boolean won;
    int rows;
    int columns;

    public ConnectFour(int r, int c, String s) {
        this.l = new ArrayList<Integer>();
        this.rows = r;
        this.columns = c;
        this.board = new int[r][c];
        this.plays = 0;
        this.won = false;
        this.title = s;
    }

    public void play(int value) {
        System.out.println("You chose column " + value + ".");

        // VALIDATION
        if (!checkGuess(this.board, value)) {
            System.out.println("Guess is out of range. Must be between 0 and " + Integer.toString(columns - 1) + ".");
            return;
        }
        System.out.println("List: " + this.l);
        if (l.contains((Integer) value)) {
            System.out.println("Row " + value + " is full.");
            return;
        }

        // PLAY COUNT / SET PLAYER
        this.plays++;
        int player = this.plays % 2 == 0 ? 2 : 1;
        int count = 0;
        int i = rows - 1;

        // Check bottom of row, move up if full
        while (i >= 0) {
            if (this.board[i][value] == 0) {
                this.board[i][value] = player;
                if (validateBoard(i, value, player)) {
                    System.out.println("Win validation. If i see this, something has gone seriously wrong.");
                    this.won = true;
                }
                return;
            } else {
                count++;
                i--;
            }
            // if row if full, add to full list
            if (count == rows - 1) {
                this.l.add((Integer) value);
            }
        }
    }

    public boolean validateBoard(int r, int c, int player) {
        int horizontal = checkHorizontal(r, c, player);
        int vertical = checkVertical(r, c, player);
        int leftDiagonal = 0;
        int rightDiagonal = 0;
        System.out.println("H: " + horizontal);
        System.out.println("V: " + vertical);
        System.out.println("LD: " + leftDiagonal);
        System.out.println("RD: " + rightDiagonal);
        if (horizontal >= 4 || vertical >= 4)
            return true;
        return false;
    }

    public int checkHorizontal(int r, int c, int player) {
        int count = 0;
        for (int i = c; i < columns; i++) {
            if (this.board[r][i] == player) {
                count++;
            } else {
                break;
            }
        }
        for (int i = c - 1; i >= 0; i--) {
            if (this.board[r][i] == player) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public int checkVertical(int r, int c, int player) {
        int count = 0;
        for (int i = r; i < rows; i++) {
            if (this.board[i][c] == player) {
                count++;
            } else {
                break;
            }
        }
        for (int i = c - 1; i >= 0; i--) {
            if (this.board[i][c] == player) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public int leftDiagonal(int r, int c, int player) {
        int count = 0;
        return count;
    }

    public int rightDiagonal(int r, int c, int player) {
        int count = 0;
        return count;
    }

    public boolean checkGuess(int[][] b, int guess) {
        if (guess < 0 || guess >= b[0].length) {
            return false;
        }
        return true;
    }

    public void printBoard() {
        System.out.println("-----------------------");
        System.out.println("Game: " + this.title);
        for (int[] x : this.board) {
            System.out.print("|");
            for (int y : x) {
                System.out.print(" " + y + " |");
            }
            System.out.println();
        }
        System.out.println("-----------------------");

    }

    public static void main(String[] args) {
        System.out.println("Connect Four");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name your game:");
        String name = scanner.nextLine();
        ConnectFour c4 = new ConnectFour(6, 7, name);
        c4.printBoard();
        while (c4.won == false) {
            System.out.println("Choose a column.");
            Scanner s = new Scanner(System.in);
            int p = s.nextInt();
            c4.play(p);
            c4.printBoard();
        }
        System.out.println("Game over.");
        scanner.close();
    }
}
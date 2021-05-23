import java.util.Scanner;
import java.util.ArrayList;

class ConnectFour {

    int[][] board;
    ArrayList<Integer> l;
    int plays;
    int player;
    boolean won;
    int rows;
    int columns;

    public ConnectFour(int r, int c) {
        this.l = new ArrayList<Integer>();
        this.rows = r;
        this.columns = c;
        this.board = new int[r][c];
        this.plays = 0;
        this.won = false;
    }

    public void play(int value) {
        if (!checkGuess(this.board, value)) {
            System.out.println("Invalid Guess");
            return;
        }
        System.out.println("List: " + this.l);
        if (l.contains((Integer) value)) {
            System.out.println("This row is full.");
            return;
        }
        this.plays++;
        int player = this.plays % 2 == 0 ? 2 : 1;
        System.out.println("Drop it like it's hot.");
        System.out.println("You chose " + value);
        int count = 0;
        int i = rows - 1;
        while (i >= 0) {
            if (this.board[i][value] == 0) {
                this.board[i][value] = player;
                if (validateBoard(i, value)) {
                    System.out.println("Win validation. If i see this, something has gone seriously wrong.");
                    this.won = true;
                }
                return;
            } else {
                count++;
                i--;
            }
            if (count == rows - 1) {
                this.l.add((Integer) value);
            }
        }
    }

    public boolean validateBoard(int r, int c) {
        System.out.println("Validate board.");
        System.out.println(this.board[r][c]);
        return false;
    }

    public boolean checkGuess(int[][] b, int guess) {
        if (guess < 0 || guess > b[0].length || guess == 7) {
            return false;
        }
        return true;
    }

    public void printBoard() {
        for (int[] x : this.board) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Connect Four");
        ConnectFour c4 = new ConnectFour(6, 7);
        c4.printBoard();
        while (c4.won == false) {
            System.out.println("Choose a column.");
            Scanner s = new Scanner(System.in);
            int p = s.nextInt();
            c4.play(p);
            c4.printBoard();
        }
        System.out.println("Game over.");
    }
}
import java.util.Scanner;

class ConnectFour {

    int[][] board;
    int plays;
    int player;
    boolean won;
    int rows;
    int columns;

    public ConnectFour(int r, int c) {
        this.rows = r;
        this.columns = c;
        this.board = new int[r][c];
        this.plays = 0;
        this.won = false;
    }

    public void play(int value) {
        int count = 0;
        this.plays++;
        int player = this.plays % 2 == 0 ? 2 : 1;
        System.out.println("Drop it like it's hot.");
        System.out.println("Plays: " + this.plays);
        System.out.println("You chose " + value);
        System.out.println("Board value: " + this.board[rows - 1][value]);
        for (int i = rows - 1; i >= 0; i--) {
            if (this.board[i][value] == 0) {
                this.board[i][value] = player;
                break;
            } else {
                count++;
            }
            if (count == rows - 1)
                System.out.println("This row is full.");
        }
    }

    public boolean checkGuess(int[][] b, int guess) {
        if (guess < 0 || guess > b[0].length) {
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
            System.out.println("Choose a column beotch.");
            Scanner s = new Scanner(System.in);
            int p = s.nextInt();
            c4.play(p);
            c4.printBoard();
        }
    }
}
import java.util.Scanner;

class ConnectFour {

    int[][] board;
    int plays;
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

    public void play(int player) {
        int choice = Integer.MAX_VALUE;
        player = 1;
        if (player == 2)
            player = 2;
        System.out.println("Drop it like it's hot.");
        System.out.println("Please choose a column to drop your token into.");
        Scanner s = new Scanner(System.in);
        choice = s.nextInt();
        s.close();
        System.out.println("You chose " + choice);
        System.out.println(this.board[rows - 1][choice]);
        System.out.println(player);
        if (this.board[rows - 1][choice] == 0)
            this.board[rows - 1][choice] = player;
        // else {
        // // go up until you find an empty spot
        // }
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
        c4.play(1);
        c4.printBoard();
    }
}
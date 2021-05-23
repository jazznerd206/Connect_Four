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
    String player1;
    String player2;

    public ConnectFour(int r, int c, String p1, String p2) {
        this.l = new ArrayList<Integer>();
        this.board = new int[r][c];
        this.rows = r;
        this.columns = c;
        this.plays = 0;
        this.won = false;
        this.player1 = p1;
        this.player2 = p2;
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
        String name = this.plays % 2 == 0 ? this.player2 : this.player1;
        System.out.println("Up next: " + name);
    }

    public boolean validateBoard(int r, int c, int player) {
        int horizontal = checkHorizontal(r, c, player);
        int vertical = checkVertical(r, c, player);
        int toBottomRightDiagonal = toBottomRight(r, c, player);
        int toTopRightDiagonal = toTopRight(r, c, player);
        // System.out.println("H: " + horizontal);
        // System.out.println("V: " + vertical);
        // System.out.println("LD: " + toBottomRightDiagonal);
        // System.out.println("RD: " + toTopRightDiagonal);
        if (horizontal >= 4 || vertical >= 4 || toBottomRightDiagonal >= 4 || toTopRightDiagonal >= 4)
            return true;
        return false;
    }

    public int checkHorizontal(int r, int c, int player) {
        int count = 0;
        for (int i = c; i < columns; i++) {
            // same row, col++
            if (this.board[r][i] == player) {
                count++;
            } else {
                break;
            }
        }
        for (int i = c - 1; i >= 0; i--) {
            // move over one column so we don't double count, col--
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
            // same col, row++
            if (this.board[i][c] == player) {
                count++;
            } else {
                break;
            }
        }
        for (int i = r - 1; i >= 0; i--) {
            // move over one row so we don't double count, row--
            if (this.board[i][c] == player) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public int toBottomRight(int r, int c, int player) {
        int count = 0;
        int i = r;
        int j = c;
        // to get all matching indices up and left: decrement rows, decrement columns
        while (i >= 0 && j >= 0) {
            if (this.board[i][j] == player) {
                count++;
                i--;
                j--;
            } else {
                break;
            }
        }
        // to get all matching indices up and right: increment rows, increment columns
        // move the pointer right and down one so we don't double count
        int m = r + 1;
        int n = c + 1;
        while (m < rows && i < columns) {
            if (this.board[m][n] == player) {
                count++;
                m++;
                n++;
            } else {
                break;
            }
        }
        return count;
    }

    public int toTopRight(int r, int c, int player) {
        int count = 0;
        // to get all matching indices below and left: increment rows, decrement columns
        int i = r;
        int j = c;
        while (i < rows && j >= 0) {
            if (this.board[i][j] == player) {
                count++;
                i++;
                j--;
            } else {
                break;
            }
        }
        // to get all matching indices up and right: decrement rows, increment columns
        // move the pointers up and right so we don't double count
        int m = r - 1;
        int n = c + 1;
        while (m >= 0 && n < columns) {
            if (this.board[m][n] == player) {
                count++;
                m--;
                n++;
            } else {
                break;
            }
        }
        return count;
    }

    public boolean checkGuess(int[][] b, int guess) {
        if (guess < 0 || guess >= b[0].length) {
            return false;
        }
        return true;
    }

    public void printBoard() {
        System.out.println("================================");
        System.out.println(this.player1 + " vs. " + this.player2);
        for (int[] x : this.board) {
            System.out.print("|");
            for (int y : x) {
                System.out.print(" " + y + " |");
            }
            System.out.println();
        }
        System.out.println("================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        System.out.println("Connect Four");
        System.out.println("Player 1:");
        String p1 = scanner.nextLine();
        System.out.println("Player 2:");
        String p2 = scanner.nextLine();
        ConnectFour c4 = new ConnectFour(6, 7, p1, p2);
        c4.printBoard();
        while (c4.won == false) {
            System.out.println("Choose a column.");
            int p = s.nextInt();
            c4.play(p);
            c4.printBoard();
        }
        System.out.println("Game over.");
        s.close();
        scanner.close();
    }
}
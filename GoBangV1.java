import java.util.Scanner;
public class GoBangV1 {
private static final int SIZE = 19;
private static String black = " ● ";
private static String white = " ○ ";
private static String empty = " ＋ ";
private static String[][] board = new String[SIZE][SIZE];
private static boolean currentPlayer = false; // false = black, true = white
private static Scanner sc = new Scanner(System.in);

//4. show result
public static void main(String[] args) {
initializeBoard();
while (true) {
    printBoard();
    System.out.println((currentPlayer ? "White" : "Black") + " player's turn.");//modified bu AI

    int[] move = getInput();
    int row = move[0];
    int col = move[1];

    board[row][col] = currentPlayer ? white : black;

    if (checkWin()) {
        printBoard();
        System.out.println((currentPlayer ? "White" : "Black") + " wins!");
        break;
    }

    if (isBoardFull()) {
        printBoard();
        System.out.println("Draw! Board is full.");
        break;
    }

    currentPlayer = !currentPlayer; 
}
}


//1. print chessboard
public static void initializeBoard() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            board[i][j] = empty;
        }
    }
}

public static void printBoard() {
    System.out.print("      ");
    for (int i = 0; i < SIZE; i++) {
        System.out.printf("%1d  ",i);
    }
    System.out.println();

    for (int i = 0; i < SIZE; i++) {
        System.out.printf("%2d |",i);
        for (int j = 0; j < SIZE; j++) {
            System.out.print(board[i][j]);
        }
    System.out.println();
    }
}//modifeid by AI


//2. input and make move
public static int[] getInput() {
    while (true) {
        try {
            System.out.print("Enter row and column. Please only enter two numbers in the range 0-18: \n");
            int r = sc.nextInt();
            int c = sc.nextInt();
            if (isValid(r, c)) {
                return new int[]{r, c};
            }
        } catch (Exception e) {
            System.out.println("Please enter valid integers.");
            sc.nextLine();
        }
    }
}

public static boolean isValid(int r, int c) {
    if (r < 0 || r >= SIZE || c < 0 || c >= SIZE) {
        System.out.println("Row and column must be between 0 and 18.");
        return false;
    }
    if (!board[r][c].equals(empty)) {
        System.out.println("This position is already occupied.");
        return false;
    }
    return true;
}


//3. check win
    public static boolean checkWin() {
    for (int row = 0; row < SIZE; row++) {
        int count = 1; 
        for (int i = 1;i<SIZE;i++) {
            if (board[row][i].equals("empty") || board[row][i-1].equals("empty")) {
                count = 1;
                continue;
            }
            if (board[row][i].equals(board[row][i-1])&&!board[row][i].equals(empty)) {
                count++;
                if (count >= 5) {
                    return true;
                }
            } 
            else {count = 1;}
        }
    }
    for (int col = 0; col < SIZE; col++) {
        int count = 1;
        for (int row = 1; row < SIZE; row++) {
            if (board[row][col].equals(empty)) {
                count = 1;
                continue;
            }
            if (board[row][col].equals(board[row-1][col])&&!board[row][col].equals(empty)) {
                count++;
                if (count >= 5) {
                    return true;
                }
            } 
            else {
                count = 1;
            }
        }
    }
    for (int row = 0; row < SIZE-4; row++) {
        for (int col = 0; col < SIZE-4; col++) {
        String first = board[row][col];
        if (first.equals(empty)){continue;}
        int count = 1;
        for (int k = 1; k < 5; k++) {
            if (board[row + k][col + k].equals(first)) {
                count++;
            } else {
                break;
            }
        }
        if (count == 5){return true;}
        }
    }
    for (int row = 4; row < SIZE; row++) {
       for (int col = 0; col < SIZE-4; col++) {
        String first = board[row][col];
        if (first.equals(empty)){continue;}
        int count = 1;
        for (int k = 1; k < 5; k++) {
            if (board[row - k][col + k].equals(first)) {
                count++;
            } else {
                break;
            }
        }
        if (count == 5){return true;}
       }
    }
    return false;
}  

public static boolean isBoardFull() {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (board[i][j].equals(empty)) {
                return false;
            }
        }
    }
    return true;
}
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GoBangGUI {

    
    private static final int SIZE = 19;
    private static String black = " ● ";
    private static String white = " ○ ";
    private static String empty = " ＋ ";
    private static String[][] board = new String[SIZE][SIZE];
    private static boolean currentPlayer = false; 
    private static final int CELLSIZE = 30;
    private static final int margin = 30;

    public static void main(String[] args) {
        initializeBoard(); 

        JFrame frame = new JFrame("Gobang_V2");
        frame.setSize(700, 750);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        JPanel chessboard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(240, 220, 180));

                
                g.setColor(Color.BLACK);
                for (int i = 0; i < 19; i++) {
                    int y = margin + i * CELLSIZE;
                    g.drawLine(margin, y, margin + 18 * CELLSIZE, y);
                    int x = margin + i * CELLSIZE;
                    g.drawLine(x, margin, x, margin + 18 * CELLSIZE);
                }

                g.drawOval(115, 115, 10, 10);
                g.setColor(Color.BLACK);
                g.fillOval(115, 115, 10, 10);

                g.drawOval(475, 115, 10, 10);
                g.setColor(Color.BLACK);
                g.fillOval(475, 115, 10, 10);

                g.drawOval(115, 475, 10, 10);
                g.setColor(Color.BLACK);
                g.fillOval(115, 475, 10, 10);

                g.drawOval(475, 475, 10, 10);
                g.setColor(Color.BLACK);
                g.fillOval(475, 475, 10, 10);

                g.drawOval(295, 295, 10, 10);
                g.setColor(Color.BLACK);
                g.fillOval(295, 295, 10, 10);

                
                for (int r = 0; r < 19; r++) {
                    for (int c = 0; c < 19; c++) {
                        if (board[r][c].equals(black)) {
                            int drawX = margin + c * CELLSIZE - CELLSIZE / 2;
                            int drawY = margin + r * CELLSIZE - CELLSIZE / 2;
                            g.setColor(Color.BLACK);
                            g.fillOval(drawX, drawY, CELLSIZE, CELLSIZE);
                        } else if (board[r][c].equals(white)) {
                            int drawX = margin + c * CELLSIZE - CELLSIZE / 2;
                            int drawY = margin + r * CELLSIZE - CELLSIZE / 2;
                            g.setColor(Color.WHITE);
                            g.fillOval(drawX, drawY, CELLSIZE, CELLSIZE);
                        }
                    }
                }
            }
        };

        chessboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                
                int col = (x - margin + CELLSIZE / 2) / CELLSIZE;
                int row = (y - margin + CELLSIZE / 2) / CELLSIZE;

                if (row >= 0 && row < 19 && col >= 0 && col < 19) {
                    if (board[row][col].equals(empty)) {

                        
                        board[row][col] = currentPlayer ? white : black;

                        
                        if (checkWin()) {
                            printBoard();
                            JOptionPane.showMessageDialog(null, (currentPlayer ? "White" : "Black") + " wins!");
                            initializeBoard();
                            currentPlayer = false;
                        } else if (isBoardFull()) {
                            JOptionPane.showMessageDialog(null, "Draw! Board is full.");
                            initializeBoard();
                            currentPlayer = false;
                        } else {
                            currentPlayer = !currentPlayer;
                        }
                        chessboard.repaint();
                    }
                }
            }
        });

        frame.add(chessboard, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    

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
            System.out.printf("%1d  ", i);
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d |", i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
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

    public static boolean checkWin() {
        for (int row = 0; row < SIZE; row++) {
            int count = 1;
            for (int i = 1; i < SIZE; i++) {
                if (board[row][i].equals("empty") || board[row][i - 1].equals("empty")) {
                    count = 1;
                    continue;
                }
                if (board[row][i].equals(board[row][i - 1]) && !board[row][i].equals(empty)) {
                    count++;
                    if (count >= 5) {
                        return true;
                    }
                } else {
                    count = 1;
                }
            }
        }
        for (int col = 0; col < SIZE; col++) {
            int count = 1;
            for (int row = 1; row < SIZE; row++) {
                if (board[row][col].equals(empty)) {
                    count = 1;
                    continue;
                }
                if (board[row][col].equals(board[row - 1][col]) && !board[row][col].equals(empty)) {
                    count++;
                    if (count >= 5) {
                        return true;
                    }
                } else {
                    count = 1;
                }
            }
        }
        for (int row = 0; row < SIZE - 4; row++) {
            for (int col = 0; col < SIZE - 4; col++) {
                String first = board[row][col];
                if (first.equals(empty)) {
                    continue;
                }
                int count = 1;
                for (int k = 1; k < 5; k++) {
                    if (board[row + k][col + k].equals(first)) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == 5) {
                    return true;
                }
            }
        }
        for (int row = 4; row < SIZE; row++) {
            for (int col = 0; col < SIZE - 4; col++) {
                String first = board[row][col];
                if (first.equals(empty)) {
                    continue;
                }
                int count = 1;
                for (int k = 1; k < 5; k++) {
                    if (board[row - k][col + k].equals(first)) {
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == 5) {
                    return true;
                }
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
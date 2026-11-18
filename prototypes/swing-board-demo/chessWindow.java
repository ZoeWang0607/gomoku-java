import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class chessWindow
{
public static void main(String[] args){
JFrame frame = new JFrame("Gobang_V2");
frame.setSize(700,750);
frame.setLocationRelativeTo(null);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new BorderLayout());
ChessBoardPanel chessboard = new ChessBoardPanel();
frame.add(chessboard,BorderLayout.CENTER);
frame.pack();
frame.setVisible(true);
}
}

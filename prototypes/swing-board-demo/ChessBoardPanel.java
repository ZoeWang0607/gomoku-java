import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ChessBoardPanel extends JPanel{
private static final int cellsize = 30;
private static final int margin = 30;
public ChessBoardPanel(){
    int panelSize = cellsize*18+margin*2;
    setPreferredSize(new Dimension(panelSize,panelSize));
    setBackground(new Color(240,220,180));
}
@Override
protected void paintComponent(Graphics g){
super.paintComponent(g);
for(int i = 0;i < 19;i++){
    int y = margin + i*cellsize;
    g.drawLine(margin,y,margin+18*cellsize,y);
    int x = margin + i*cellsize;
    g.drawLine(x,margin,x,margin+18*cellsize);
}

g.drawOval(45,45,30,30);
g.setColor(Color.BLACK);
g.fillOval(45,45,30,30);

g.drawOval(75,45,30,30);
g.setColor(Color.WHITE);
g.fillOval(75,45,30,30);

g.drawOval(115,115,10,10);
g.setColor(Color.BLACK);
g.fillOval(115,115,10,10);
g.drawOval(475,115,10,10);
g.setColor(Color.BLACK);
g.fillOval(475,115,10,10);
g.drawOval(115,475,10,10);
g.setColor(Color.BLACK);
g.fillOval(115,475,10,10);
g.drawOval(475,475,10,10);
g.setColor(Color.BLACK);
g.fillOval(475,475,10,10);
g.drawOval(295,295,10,10);
g.setColor(Color.BLACK);
g.fillOval(295,295,10,10);
}
}

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PackMan extends Frame {
   public PackMan (){
      setLayout(null);
      setBounds(100,100,800,600);
      setBackground(Color.black);
      
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
             System.exit(0);
         }
     });
     
      setVisible(true);
   }
   
   public void paintGhost(Graphics g, Color c, int x, int y){
      //GOSTH
      g.setColor(c);
      g.fillArc(x,y,150,150,0,180);
      g.fillRect(x,y + 75,150,60);
      for (int i = 0; i < 150; i += 30){
         g.fillArc(x + i,y + 120,30,30,180,180);
      }
      
      //GHOST EYES
      g.setColor(Color.white);
      g.fillOval(x + 30,y + 50, 30,30);
      g.fillOval(x + 90,y + 50, 30,30);
      g.setColor(Color.black);
      g.fillOval(x + 40,y + 60, 10,10);
      g.fillOval(x + 100,y + 60, 10,10);

   }
   
   public void paint (Graphics g){
      super.paint(g);
      
      //PACKMAN
      g.setColor(Color.yellow);
      g.fillArc(100,100,150,150,30,300); 
      //PACKMAN EYES
      g.setColor(Color.black);
      g.fillOval(150,130,30,30);
      
      //PACKMAN COOKIES
      g.setColor(Color.orange);
      for (int i = 0; i < 5; i++){
         g.fillOval(250 + i * 60,160,30,30);
      }
      
      //GOSTH
      g.setColor(Color.orange);
      g.fillArc(600,100,150,150,0,180);
      g.fillRect(600,175,150,60);
      for (int i = 0; i < 150; i += 30){
         g.fillArc(600 + i,220,30,30,180,180);
      }
      
      //GHOST EYES
      g.setColor(Color.white);
      g.fillOval(630,150, 30,30);
      g.fillOval(690,150, 30,30);
      g.setColor(Color.black);
      g.fillOval(640,160, 10,10);
      g.fillOval(700,160, 10,10);
      
      paintGhost(g,Color.cyan, 600,300);
      
   }
   
   public static void main (String args[]){
      new PackMan();
   }
}
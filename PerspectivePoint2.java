import java.awt.*;
import javax.swing.*;
import draw_primitives.*;

public class PerspectivePoint2 extends JFrame{
   private int xPoint;
   private int yPoint;
   private Primitives3D d;
   Cartesiano c;
   
   public PerspectivePoint2(){
      //setBounds(100,100,800,600);
      setExtendedState(JFrame.MAXIMIZED_BOTH); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
      setVisible(true);
   }
      
   public void paint(Graphics g){
      super.paint(g);
      c = new Cartesiano(g);      
      this.xPoint = getWidth() / 2;
      this.yPoint = getHeight() / 2;
      c.setX0(xPoint);
      c.setY0(yPoint);
      c.setCuadrante(0);
      
      d = new Primitives3D(c); 
      
      double [][] cuadro = {
         {-150,-50,-50,-150},
         {0,0,100,100},
         {1,1,1,1}
      };
                
      d.drawCube(cuadro, 0.5);
      d.drawCone(100,100,200,100);
      d.drawSphera(-500,00,150, 5);
      d.drawCylinder(100,-200,200,100,5);
      //d.drawPyramid(-250,-300,10,100,300);
      d.drawPyramid(-200,300,4,100,-200, 0.9 * Math.PI, 3);
      d.drawPyramid(300,300,5,100,-300);
      d.drawPrism(-200,-300,4,100,200);
      g.setColor(Color.blue);
      d.drawPrism(550,200,3,100,100,0.9*Math.PI,2);
      g.setColor(Color.black);
      d.drawOctahedron(500,-300,100,3,0.4 * Math.PI);
      d.drawTetrahedron(500,0,100,0.9 * Math.PI, 3);
   }
   
   public static void main (String[] args){
      new PerspectivePoint2();
   }           
}
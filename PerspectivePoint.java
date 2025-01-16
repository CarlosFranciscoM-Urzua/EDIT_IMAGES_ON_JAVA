import java.awt.*;
import javax.swing.*;
import draw_primitives.*;

public class PerspectivePoint extends JFrame{
   private int xPoint;
   private int yPoint;
   
   public PerspectivePoint(){
      setBounds(100,100,800,600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      setVisible(true);
   }
   
   public double[][] perspective(double [][] m){
      double[][] m1 = new double[3][m[0].length];
      double x = 0, y = 0;
      
      for (int i = 0; i < m[0].length; i++){
         
         x = m[0][i];
         y = m[1][i];
         
         m1[0][i] = ((xPoint - x)/((yPoint - y) * Math.sqrt(2))) * y + x;
         m1[1][i] = (yPoint - y)/Math.sqrt(2) + y;
         m1[2][i] = 1;
      }
      
      return m1;
   }
   
   public int[] perspective(int[] point){
      int p[] = new int[2];
      int x = point[0], y = point[1];
      
      p[0] = (int)((xPoint - x)/((yPoint - y) * Math.sqrt(2))) * y + x;
      p[1] = (int)((yPoint - y)/Math.sqrt(2)) + y;

      
      return p;
   }

   
   public void paint(Graphics g){
      super.paint(g);
      
      TransformacionesMatriciales t = new TransformacionesMatriciales();
      Cartesiano c = new Cartesiano(g);
      RectLine r = new RectLine(c);
      draw_primitives.Polygon p = new draw_primitives.Polygon(r);
      
      this.xPoint = getWidth() / 2;
      this.yPoint = getHeight() / 2;
      c.setX0(xPoint);
      c.setY0(yPoint);
      c.setCuadrante(0);
      
      g.drawLine(0,yPoint,getWidth(),yPoint);
      
      for (int i = 0; i < 10; i++){
         g.drawLine(xPoint, yPoint, (int)(xPoint * (1 + 2 * Math.cos(Math.PI * i / -10))), (int)(yPoint * (1 - 2 * Math.sin(Math.PI * i / -10))));
      }
      
      double [][] cuadro = {
         {-150,-50,-50,-150},
         {-100,-100,0,0},
         {1,1,1,1}
      };
      
      int[] x = t.actualizarPuntos(cuadro,0);
      int[] y = t.actualizarPuntos(cuadro,1);
      //g.drawPolygon(x,y,4);
      p.drawPolygon(x,y);
      
      /*double[][] p1 = perspective (cuadro);
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y);*/
      
      double rad = 0;
      int point[] = new int[2];
      for(int theta = 0; theta < 360; theta++){
         rad = Math.toRadians(theta);
         point[0] = (int)(Math.cos(rad) * 100) + 0;
         point[1] = (int)(Math.sin(rad) * 100) + 0;
         point = perspective(point);
         c.drawPixel(point[0], point[1], 2);
      }
   }
   
   public static void main (String[] args){
      new PerspectivePoint();
   }           
}
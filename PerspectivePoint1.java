import java.awt.*;
import javax.swing.*;
import draw_primitives.*;

public class PerspectivePoint1 extends JFrame{
   private int xPoint;
   private int yPoint;
   TransformacionesMatriciales t;
   Cartesiano c;
   RectLine r;
   draw_primitives.Polygon p;
   
   public PerspectivePoint1(){
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
   
   public void drawEllipse(int xp, int yp, double a, double b){
      int x = 0;
      int y = 0;
      
      for (int i = 0; i < 360; i++){
         x = xp + (int)(a * Math.cos(i));
         y = yp + (int)(b * Math.sin(i));
         
         c.drawPixel(x,y,2);
      }
   }
   
   public void drawCylinder(Graphics g, int xp, int yp, int h, int a, int partitions){
      int x = 0;
      int y = 0;
      double b = a / Math.sqrt(8);
      
      for (int i = 0; i < 360; i++){
         x = xp + (int)(a * Math.cos(Math.toRadians(i)));
         y = yp + (int)(b * Math.sin(Math.toRadians(i)));
         if(partitions < 1) partitions = 2;
         
         c.drawPixel(x,y,2);
         c.drawPixel(x,y + h,2);
         
         if(i % (360 / partitions) == 0 || i % 45 == 0){
            r.generalLine(x,y,x,h + y, g.getColor());
         }
      }
   }
   
   public void drawSphera(Graphics g, int xp, int yp, int radius, int partitions){
      int x = 0;
      int y = 0;
      int d = radius / partitions;
      
      c.setCuadrante(0);
      
      for (int i = 0; i < 360; i++){
         x = xp + (int)(radius * Math.cos(i));
         y = yp + (int)(radius * Math.sin(i));
         
         c.drawPixel(x,y,2);
         
         for(int j = d; j <= radius; j += d){
            x = xp + (int)((radius - j) * Math.cos(i));
            y = yp + (int)(radius * Math.sin(i));
            
            c.drawPixel(x,y,2);            
            c.drawPixel(y,x,2);
         }         
      }
   }
   
   public void drawCone(Graphics g, int xp, int yp, int h, int a){
      int x = 0;
      int y = 0;
      double b = a / Math.sqrt(8);
      
      for (int i = 0; i < 360; i++){
         x = xp + (int)(a * Math.cos(i));
         y = yp + (int)(b * Math.sin(i));
         
         c.drawPixel(x,y,2);
         
         if(i % 10 == 0 && i < 180){
            r.generalLine(x,y,xp,h + yp, g.getColor());
         }
      }
   }
   
   public void drawPyramid(Graphics g, int xp, int yp, int facesOfBase, int apothema, int h){
      drawPyramid(g,xp,yp,facesOfBase,apothema,h,Math.PI/2);
   }
   
   public void drawPyramid(Graphics g, int xp, int yp, int facesOfBase, int apothema, int h, double phase){
      int[] x = new int[facesOfBase];
      int[] y = new int[facesOfBase];
      double b = apothema / 3;
      double theta = (2 * Math.PI) / facesOfBase;
      
      for (int i = 0; i < facesOfBase; i++){
         x[i] = xp + (int)(apothema * Math.cos(i * theta + phase));
         y[i] = yp + (int)(b * Math.sin(i * theta + phase));
         
         r.generalLine(x[i],y[i],xp,h + yp, g.getColor());         
      }
      
      //g.setColor(Color.green);
      p.drawPolygon(x,y);
   }
   
   public void drawTetrahedron(Graphics g, int xp, int yp, int apothema){
      int h = (int)(apothema * Math.sqrt(13) / 4);
      drawPyramid(g,xp,yp,3,apothema, h);
   }
   
   public void drawOctahedron(Graphics g, int xp, int yp, int apothema){
      int h = (int)(apothema / Math.sqrt(2));
      /*drawPyramid(g,xp,yp,4,apothema, h);
      drawPyramid(g,xp,yp,4,apothema, -h);*/
      
      int[] x = new int[4];
      int[] y = new int[4];
      double b = apothema / 4;
      double theta = Math.PI / 2;
      double phase = Math.PI/7;
      
      for (int i = 0; i < 4; i++){
         x[i] = xp + (int)(apothema * Math.cos(i * theta + phase));
         y[i] = yp + (int)(b * Math.sin(i * theta + phase));
         
         r.generalLine(x[i],y[i],xp,yp + h, g.getColor());    
         r.generalLine(x[i],y[i],xp,yp - h, g.getColor());       
      }
      
      //g.setColor(Color.green);
      p.drawPolygon(x,y);

   }
   
   public void drawPrism(Graphics g, int xp, int yp, int facesOfBase, int apothema, int h){
      int[] x = new int[facesOfBase];
      int[] y = new int[facesOfBase];
      int[] y1 = new int[facesOfBase];
      double dephase = Math.PI/3;
      
      double b = apothema / 3;
      double theta = (2 * Math.PI) / facesOfBase;
      
      for (int i = 0; i < facesOfBase; i++){
         x[i] = xp + (int)(apothema * Math.cos(i * theta + dephase));
         y[i] = yp + (int)(b * Math.sin(i * theta + Math.PI/3));
         y1[i] = y[i] + h;
         
         r.generalLine(x[i],y[i],x[i],h + y[i], g.getColor());         
      }
      
      p.drawPolygon(x,y);
      p.drawPolygon(x,y1);
   }

   
   public void drawCube (Graphics g, double [][] cuadro, double a){
      //cuadro = t.multiplicar(t.S(1.5,1.5), cuadro);
      int[] x = t.actualizarPuntos(cuadro,0);
      int[] y = t.actualizarPuntos(cuadro,1);
      p.drawPolygon(x,y);
      
      //double a = 0.3;
      
      double[][] p1 = t.multiplicar(t.CX(a),cuadro);
      double[][] cx = t.multiplicar(t.CX(a),cuadro);
      p1 = t.multiplicar(t.S(1,a),p1);
      double[][] T1 = t.T((int)(cuadro[0][2] - p1[0][2]),(int)(cuadro[1][2] - p1[1][2]));
      p1 = t.multiplicar(T1,p1);  
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y); 
      
      //g.setColor(Color.red);
      T1 = t.T((int)(cuadro[0][1] - p1[0][1]),(int)(cuadro[1][1] - p1[1][1]));
      p1 = t.multiplicar(T1,p1); 
      double xp = cuadro[0][0] - p1[0][0], yp = cuadro[1][0] - p1[1][0];
      p1 = t.multiplicar(t.T(-xp, -yp),p1);
      p1 = t.multiplicar(t.R(180),p1);
      p1 = t.multiplicar(t.T(cuadro[0][0] - p1[0][1], cuadro[1][0] - p1[1][1]),p1);
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y); 
      
      g.setColor(Color.black);
      p1 = t.multiplicar(t.CY(a),cuadro);  
      p1 = t.multiplicar(t.S(a,1),p1);
      double[][] T2 = t.T((int)(cuadro[0][2] - p1[0][2]),(int)(cuadro[1][2] - p1[1][2]));
      p1 = t.multiplicar(T2,p1);
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y); 
      
      //g.setColor(Color.blue);
      p1 = t.multiplicar(t.T((int)(1 * (cuadro[0][0] - cx[0][3])),(int)(a * (cuadro[1][0] - cx[1][3]))), cuadro );
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y); 
   }

   
   public void paint(Graphics g){
      super.paint(g);
      
      t = new TransformacionesMatriciales();
      c = new Cartesiano(g);
      r = new RectLine(c);
      p = new draw_primitives.Polygon(r);
      
      this.xPoint = getWidth() / 2;
      this.yPoint = getHeight() / 2;
      c.setX0(xPoint);
      c.setY0(yPoint);
      c.setCuadrante(0);
      
      drawScenario(g);  
      
      double [][] cuadro = {
         {-150,-50,-50,-150},
         {0,0,100,100},
         {1,1,1,1}
      };
                
      drawCube(g, cuadro, 0.5);
      //drawEllipse(100,100,100,30);
      drawCone(g,100,100,200,100);
      drawSphera(g,-100,-100,150, 5);
      drawCylinder(g,100,-200,200,100,10);
      /*drawPyramid(g,-200,-200,10,100,300);
      drawPyramid(g,-200,200,4,100,-300);
      drawPyramid(g,300,200,5,100,-300);
      drawPrism(g,-300,00,5,100,100);
      drawPrism(g,100,200,3,100,100);
      drawOctahedron(g,0,0,100);
      drawTetrahedron(g,100,0,100);*/
   }
   
   public void drawScenario(Graphics g){
      g.drawLine(0,yPoint,getWidth(),yPoint);
      
      for (int i = 0; i < 10; i++){
         g.drawLine(xPoint, yPoint, (int)(xPoint * (1 + 2 * Math.cos(Math.PI * i / -10))), (int)(yPoint * (1 - 2 * Math.sin(Math.PI * i / -10))));
      }
   }
   
   public static void main (String[] args){
      new PerspectivePoint1();
   }           
}
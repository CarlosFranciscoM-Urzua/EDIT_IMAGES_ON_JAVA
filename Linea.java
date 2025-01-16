import java.awt.*;

public class Linea {
   Graphics g;
   Pixel px;
   
   public Linea (Graphics g){
      this.g = g;
   }
   
   //MÉTODOS PARA DIBUJAR LÍNEAS
   //CASO 1
   public void drawLine1(int x1, int y1, int x2, int y2){
      int x = x1;
      for (int y = y1; y <= y2; y++){
         px.dibujarPixel(x, y, g);
      }   
   }
   
   //CASO 2
   public void drawLine2(int x1, int y1, int x2, int y2) {
      int y = y1;
      for (int x = x1; x <= x2; x++) {
         px.dibujarPixel(x, y, g);
      }
   }
   
   //CASO 3
   public void drawLine3(int x1, int y1, int x2, int y2) {
      for (int x = x1, y = y1; x <= x2; x++, y++) {
         px.dibujarPixel(x, y, g);
      }
   }
   
   //CASO 4
   public void drawLine4(int x1, int y1, int x2, int y2) {
      for (int x = x1, y = y1; x <= x2; x++, y--) {
         px.dibujarPixel(x, y, g);
      }
   }
   
   //CASO 5
   public void drawLine5(int x1, int y1, int x2, int y2, int m) {
      double y = y1;
      for (int x = x1; x <= x2; x++) {
         px.dibujarPixel(x, (int)Math.round(y), g);
         y = m * (x - x1) + y1;
      }
   }
   
   //CASO 6
   public void drawLine6(int x1, int y1, int x2, int y2, int m) {
      double x = x1;
      for (int y = y1; y <= y2; y++) {
         px.dibujarPixel((int)Math.round(x), y, g);
         x = (y - y1) / m + x1;
      }
   }
   
   //CASO 7
   public void drawLine7(int x1, int y1, int x2, int y2) {
      
   }
   
   //CASO 8
   public void drawLine8(int x1, int y1, int x2, int y2) {
      
   }
}
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MostrarImagen {
   public static void main(String[] args) throws IOException {
      BufferedImage imagen = ImageIO.read(new File("kick.jpg"));
      BufferedImage imagen2 = ImageIO.read(new File("kick2.jpg"));
      JLabel label = new JLabel(new ImageIcon(imagen));
      JLabel label2 = new JLabel(new ImageIcon(imagen2));
      JFrame frame = new JFrame();
      frame.setLayout(new FlowLayout());
      System.out.println(imagen);
      System.out.println();
      System.out.println(imagen2);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(label);
      frame.add(label2);
      frame.pack();
      frame.setVisible(true);
   } 
}
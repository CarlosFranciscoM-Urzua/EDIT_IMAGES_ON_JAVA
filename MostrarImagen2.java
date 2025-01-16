import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class MostrarImagen2 {

    public static void main(String[] args) throws IOException {
       BufferedImage imagen = ImageIO.read(new File("IMAGEN_ORIGINAL2.jpg"));
   
       BufferedImage imagenGris = convertirAGrises(imagen);
       guardarImagen(imagenGris, "jpg", "FILTRO_GRIS.jpg");
   
       BufferedImage imagenRecortada = recortarImagen(imagen, 50, 50, 70,70);
       guardarImagen(imagenRecortada, "jpg", "RECORTE_DE_IMAGEN.jpg");
   
       BufferedImage imagenDesenfocada = aplicarDesenfoque(imagen);
       guardarImagen(imagenDesenfocada, "jpg", "EFECTO_DESENFOQUE.jpg");
       
       BufferedImage imagenResaltada = resaltarBorde(imagen);
       guardarImagen(imagenResaltada, "jpg", "IMAGEN_RESALTADA.jpg");
   
       
       BufferedImage dibujarSobreImagen = dibujarSobreImagen(imagen,50, 50, 100, 100);
       guardarImagen(dibujarSobreImagen, "jpg", "DIBUJO_SOBRE_IMAGEN.jpg");
       
       System.out.println("IMAGENES MODIFICACION DE LA IMAGEN ORIGINAL GENERADAS Y EN DISCO."); 
    }
    
    public static BufferedImage redimensionar(BufferedImage imagen, int ancho, int alto) {
       Image scaledImage = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
       BufferedImage nuevaImagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
       Graphics2D g = nuevaImagen.createGraphics();
       g.drawImage(scaledImage, 0, 0, null);
       g.dispose();
       
       return nuevaImagen;
   }

   public static BufferedImage convertirAGrises(BufferedImage imagen) {
       BufferedImage imagenGris = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
       Graphics g = imagenGris.getGraphics();
       g.drawImage(imagen, 0, 0, null);
       g.dispose();
       
       return imagenGris;
   }

   public static BufferedImage recortarImagen(BufferedImage imagen, int x, int y, int ancho, int alto) {
       return imagen.getSubimage(x, y, ancho, alto);
   }


   public static BufferedImage aplicarDesenfoque(BufferedImage imagen) {
       float[] matriz = {
           1f / 9f, 1f / 9f, 1f / 9f,
           1f / 9f, 1f / 9f, 1f / 9f,
           1f / 9f, 1f / 9f, 1f / 9f
       };
       
       float[] matriz2 = {
           0.0f,-1.0f,0.0f,
           -1.0f,4.0f,-1.0f,
           0.0f,-1.0f,0.0f
       };
   
       BufferedImage imagenDesenfocada = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
       ConvolveOp convolve = new ConvolveOp(new Kernel(3, 3, matriz));
       convolve.filter(imagen, imagenDesenfocada);
       
       return imagenDesenfocada;
   }
   
   public static BufferedImage resaltarBorde(BufferedImage imagen) {
       float[] matriz = {
           0.0f,-1.0f,0.0f,
           -1.0f,4.0f,-1.0f,
           0.0f,-1.0f,0.0f
       };
   
       BufferedImage imagenResaltada = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
       ConvolveOp convolve = new ConvolveOp(new Kernel(3, 3, matriz));
       convolve.filter(imagen, imagenResaltada);
       
       return imagenResaltada;
   }

   public static BufferedImage dibujarSobreImagen(BufferedImage imagen,int x,int y,int ancho,int alto) {
       Graphics2D g = imagen.createGraphics();
       g.setColor(Color.RED);
       g.fillRect(x, y, ancho, alto);
       g.dispose();
       
       return imagen;
   }

   public static void guardarImagen(BufferedImage imagen, String formato, String ruta) throws IOException {
       ImageIO.write(imagen, formato, new File(ruta));
   }
}
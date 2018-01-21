import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import javax.swing.ImageIcon;

public class colormodel{
  
ColorModel findcolrmodel(Image image) throws Exception{
  Image image1 =image;
  ColorModel cm ;
  if (image1 instanceof BufferedImage) {
    BufferedImage bimage = (BufferedImage) image1;
    cm=bimage.getColorModel();
    return cm;
  }

  PixelGrabber pg = new PixelGrabber(image1, 0, 0, 1, 1, false);
  try {
    pg.grabPixels();
  } catch (InterruptedException e) {
  }
 cm = pg.getColorModel();
  System.out.println(cm.getNumColorComponents());
  return cm;
}
}




import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Referenced classes of package imageaccess:
//            ImageAccess

public class LoaderImage
{


    public ImageAccess loadImageAccessFile(String path, String Filename, int nx, int ny, int type) throws IOException
    {
        Image image = null;
        image = loadImageFile(path + "/" + Filename,nx,ny);
        ImageAccess ia = new ImageAccess(nx, ny, type);
        if(image != null)
        {
            ia.copy(image);
        } else
        {
            double col[] = new double[ny];
            for(int i = 0; i < ny; i++)
            {
                col[i] = 255D;
            }

            for(int i = 0; i < nx; i += 4)
            {
                ia.putColumn(i, col);
            }

        }
        return ia;
    }

    private Image loadImageFile(String string,int nx,int ny)throws IOException {

          int width = nx; //width of the image
          int height = ny; //height of the image

          // For storing image in RAM
          BufferedImage image = null;     
          
          // READ IMAGE
          try
          {
              File input_file = new File(string); 

              
              image = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_GRAY);

              // Reading input file
              image = ImageIO.read(input_file);

              System.out.println("Reading complete.");
              
              
          }
          catch(IOException e)
          {
              System.out.println("Error: "+e);
          }
          return image;
    }

 /*   public ImageAccess loadImageAccessFile(String Filename, int nx, int ny, int type) throws IOException
    {
        Image image = null;
        image = loadImageFile(Filename);
        ImageAccess ia = new ImageAccess(nx, ny, type);
        if(image != null)
        {
            ia.copy(image);
        } else
        {
            double col[] = new double[ny];
            for(int i = 0; i < ny; i++)
            {
                col[i] = 255D;
            }

            for(int i = 0; i < nx; i += 4)
            {
                ia.putColumn(i, col);
            }

        }
        return ia;
    }*/

    
}

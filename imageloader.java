import java.applet.Applet;
import java.awt.Image;
import java.awt.MediaTracker;



public class imageloader {
	
	private Applet applet;
	
	 public Image loadImageFile(String Filename)
	    {
	        Image image = null;
	        MediaTracker mtracker = new MediaTracker(applet);
	        image = applet.getImage(applet.getDocumentBase(), Filename);
	        mtracker.addImage(image, 0);
	        try
	        {
	            mtracker.waitForAll();
	        }
	        catch(InterruptedException ie)
	        {
	            System.out.println("Bad loading of an image.");
	        }
	        return image;
	    }

	
	 public ImageAccess loadImageAccessFile(String path, String Filename, int nx, int ny, int type)
	    {
	        Image image = null;
	        image = loadImageFile(path + "/" + Filename);
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

}

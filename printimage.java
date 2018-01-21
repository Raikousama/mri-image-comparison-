import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class printimage {

	public static BufferedImage createImageFromArray(Raster imgdata,ColorModel cm)
	{
		int width = 963; //width of the image
		int height = 640; //height of the image
	    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
	   Raster newData=imgdata;
	   WritableRaster wr = newData.createCompatibleWritableRaster();
	   wr.setDataElements(0, 0, imgdata);
	   ColorModel cmodel = cm;
	  // boolean cb =cmodel.isAlphaPremultiplied();
	  //  int size = height+width;
	   // for (int i = 0; i < size; i++)
	    //{
	      //  newData = imgdata;
	    //}
	    BufferedImage image = new BufferedImage(cm, wr, false, null);
	    return image;
	}
}

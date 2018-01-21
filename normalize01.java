
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.IOException;


public class normalize01  {

	public WritableRaster greyscalenor(BufferedImage Image) throws IOException
	{
		// byte [] clr = ((DataBufferByte)Image.getRaster().getDataBuffer()).getData();
	        final BufferedImage image = Image;
	        WritableRaster wr = Image.getRaster();
	        //int i=0;
	        for (int x = 0; x < image.getWidth(); x++) 
	        {
	            for (int y = 0; y < image.getHeight(); y++) 
	            {
	            	 int grayLevelPixel = wr.getSample(x, y, 0);
	            	 wr.setSample(x, y, 0, grayLevelPixel);
					 //=  (byte) (image.getRGB(x,y) );
					  //i++;
	//                if (clr[i]  <= 0)
	  //              	clr[i]  = (byte) -clr[i] ;
	              
	                     
	            }
	        }
	  return wr;
	   
}
}

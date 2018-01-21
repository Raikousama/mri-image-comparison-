// Java program to demonstrate read and write of image
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class imageinput
{
	
	public BufferedImage readimage()throws IOException
	{
		int width = 963; //width of the image
		int height = 640; //height of the image

		// For storing image in RAM
		BufferedImage image = null;		
		
		// READ IMAGE
		try
		{
			File input_file = new File("G:\\myBrain_axial.bmp"); 

			
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

	}//main() ends here
}//class ends here

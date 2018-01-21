
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import javax.imageio.ImageIO;

public class Writeimage  {
	
	
public void createimage1(BufferedImage image) throws IOException{ 

	try
    {
		
        // Output file path
        File output_file = new File("G:\\Out.jpg");

        // Writing to file taking type and path as
        ImageIO.write((RenderedImage) image, "jpg", output_file);

        System.out.println("Writing complete.");
    }
    catch(IOException e)
    {
        System.out.println("Error: "+e);
    }
}
}

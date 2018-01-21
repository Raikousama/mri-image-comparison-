import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;

public class mainproj {

	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		imageinput imginpm = new imageinput();
		BufferedImage imgin = null,imgout = null;
		try {
			imgin = imginpm.readimage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*segmentmet smet = new segmentmet();
		smet.transform(imgin);*/
		
		normalize01 nor = new normalize01();
		WritableRaster imgdata=(WritableRaster) nor.greyscalenor(imgin);
		
		//give the color model of the iputimage which can be used 
		//by the printimage in the producing of bufferedimage
		colormodel cmm = new colormodel();
		ColorModel cml =cmm.findcolrmodel(imgin);
		
		//produces output image
		imgout=printimage.createImageFromArray(imgdata, cml);
		
		//writes image to g\\out.jpeg
		Writeimage writ = new Writeimage();
		writ.createimage1(imgout);

	}

}

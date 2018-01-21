import java.awt.image.BufferedImage;


public class segmentmet {

	int A =255;
	int width = 963; //width of the image
	int height = 640; //height of the image
	BufferedImage imgbuff;
	
	public void transform(BufferedImage Image) {
		normalize01 nor = new normalize01();
		
		imgbuff = Image;
		
		}
}

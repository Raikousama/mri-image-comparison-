
public class ExtractBorder {

  private int width;
  private int height;
  private double thres;
  private double minValue;
  private double maxValue=255D;
  private ImageAccess inputimage;
  private ImageAccess outputimage;
  
  public void setInputImage(ImageAccess input)
  {
    height= input.getHeigth();
    width= input.getWidth();
    inputimage =input;
    outputimage= new ImageAccess(width,height);
  }
  public void setThresholdValue(int val)
  {
    thres=val;
  }
  
  public void SetMinValue(double val)
  {
      minValue = val;
  }

  public void SetMaxValue(double val)
  {
      maxValue = val;
  }

  public ImageAccess getOutput()
  {
    return outputimage;
  }
  
  public void update()
  {
    GetBorder();
  }
  
  public void GetBorder()
  {
      double neigh[][] = new double[3][3];
      
      for(int x=0;x<width;x++)
      {
        for(int y=0;y<height;y++)
        {
          outputimage.putPixel(x,y,minValue);
          double value = inputimage.getPixel(x,y);
          
          if(value>= thres)
          {
            
            inputimage.getNeighborhood(x,y,neigh);
            int i=0;
          }
        }
      }
  }
  
  
  
}

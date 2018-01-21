import java.awt.Image;
import java.awt.image.PixelGrabber;

public class ImageAccess {

	  public static final int NOTDEFINED = -1;
	    public static final int BYTE = 0;
	    public static final int SHORT = 1;
	    public static final int FLOAT = 2;
	    public static final int DOUBLE = 6;
	    public static final int CONNECTIVITY_4 = 0;
	    public static final int CONNECTIVITY_8 = 1;
	    private int nx;
	    private int ny;
	    private int type;
	    private int size;
	    public byte bytePixels[];
	    public short shortPixels[];
	    public float floatPixels[];
	    public double doublePixels[];
	    private int mark;

	  public ImageAccess(int nx, int ny, int type)
	    {
	        this.type = -1;
	        size = 0;
	        mark = 0;
	        if(nx < 1 || ny < 1)
	        {
	            throw new ArrayStoreException("Unexpected image size (" + nx + "," + ny + ").");
	        }
	        this.nx = nx;
	        this.ny = ny;
	        this.type = type;
	        size = nx * ny;
	        switch(type)
	        {
	        case 0: // '\0'
	            bytePixels = new byte[nx * ny];
	            break;

	        case 1: // '\001'
	            shortPixels = new short[nx * ny];
	            break;

	        case 2: // '\002'
	            floatPixels = new float[nx * ny];
	            break;

	        case 6: // '\006'
	            doublePixels = new double[nx * ny];
	            break;

	        case 3: // '\003'
	        case 4: // '\004'
	        case 5: // '\005'
	        default:
	            throw new ArrayStoreException("Unexpected image type " + type);
	        }
	    }

	public ImageAccess(int nx, int ny) {
        if(nx<=0||ny<=0)
        {
          throw new ArrayStoreException("Unexpected image size (" + nx + "," + ny + ").");
        }
        else
        {
          this.nx=nx;
          this.ny=ny;
          size=nx*ny;
          floatPixels = new float[nx*ny];
          return;
        }
      }

  public void copy(Image image)
    {
        int arr[];
        try
        {
            arr = new int[size];
            PixelGrabber pg = new PixelGrabber(image, 0, 0, nx, ny, arr, 0, nx);
            pg.grabPixels();
        }
        catch(InterruptedException e)
        {
            throw new ArrayStoreException("Unable to read the pixels array");
        }
        switch(type)
        {
        case 0: // '\0'
            byte c = 3;
            //& with 0xff is for normalizing the image
            for(int i = 0; i < size; i++)
            {
             
                bytePixels[i] = (byte)((byte)((arr[i] & 0xff) / c) + (byte)(((arr[i] & 0xff00) >> 8) / c) + (byte)(((arr[i] & 0xff0000) >> 16) / c));
            }

            break;

        case 1: // '\001'
            for(int i = 0; i < size; i++)
            {
                shortPixels[i] = (short)((short)(arr[i] & 0xff) / 3 + (short)((arr[i] & 0xff00) >> 8) / 3 + (short)((arr[i] & 0xff0000) >> 16) / 3);
            }

            break;

        case 2: // '\002'
            float cf = 3F;
            for(int i = 0; i < size; i++)
            {
                floatPixels[i] = ((float)(arr[i] & 0xff) + (float)((arr[i] & 0xff00) >> 8) + (float)((arr[i] & 0xff0000) >> 16)) / cf;
            }

            break;

        case 6: // '\006'
            for(int i = 0; i < size; i++)
            {
                doublePixels[i] = ((double)(arr[i] & 0xff) + (double)((arr[i] & 0xff00) >> 8) + (double)((arr[i] & 0xff0000) >> 16)) / 3D;
            }

            break;

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        default:
            throw new ArrayStoreException("Unexpected image type");
        }
    }

	 public void putColumn(int x, double column[])
	    {
	        if(x < 0)
	        {
	            return;
	        }
	        if(x >= nx)
	        {
	            return;
	        }
	        switch(type)
	        {
	        case 0: // '\0'
	        {
	            for(int i = 0; i < ny; i++)
	            {
	                bytePixels[x] = (byte)(int)column[i];
	                x += nx;
	            }

	            break;
	        }

	        case 1: // '\001'
	        {
	            for(int i = 0; i < ny; i++)
	            {
	                shortPixels[x] = (short)(int)column[i];
	                x += nx;
	            }

	            // fall through
	        }

	        case 2: // '\002'
	        {
	            for(int i = 0; i < ny; i++)
	            {
	                floatPixels[x] = (float)column[i];
	                x += nx;
	            }

	            break;
	        }

	        case 6: // '\006'
	        {
	            for(int i = 0; i < ny; i++)
	            {
	                doublePixels[x] = column[i];
	                x += nx;
	            }

	            break;
	        }

	        case 3: // '\003'
	        case 4: // '\004'
	        case 5: // '\005'
	        default:
	        {
	            throw new ArrayStoreException("Unexpected image type");
	        }
	        }
	    }

  public int getHeigth() {
   return ny;
  }

  public int getWidth() {
    // TODO Auto-generated method stub
    return nx;
  }

  public double getPixel(int x, int y) {
    int periodx=2*nx-2;
    int periody=2*ny-2;
    if(x<0)
    {
      for(;x<0;x=+periodx) {}
      if(x>=nx)
      {
        x=periodx-x;
      }//if(x>=nx)
    }//if(x<0)
    
    else 
        if(x>=nx)
        {
          for(;x<0;x-=periodx) {}
          if(x<0){
            x=-x;
          }
        }//if(x>=nx)
    
    if(y<0)
    {
        for(; y<0 ; y=+periody) {}
        if(y>=ny)
        {
          y=periody-y;
        }
        else
          if(y>=ny) {
            for(;y>=ny;y-=periody) {}
            if(y<0)
            {
              y=-y;
            }
          }
    }//if(y<0)
        switch(type)
        {
          case 0:
            double v = bytePixels[x +y *nx];
            return v >= 0.0D ? v:v + 256D;
            
          case 1:
           return (double)shortPixels[x +y *nx];
           
          case 3:
          case 4:
          case 5:
            
          case 6:
            return doublePixels[x+y*nx];
            
            default:
              throw new ArrayStoreException("Unexpected Image type");   
        }//switch
    }//getpixel
  
  public void putPixel(int x, int y, double value) {
   if(x<0||y<0)
   {
     return;
   }
   if(x>=nx||y>=ny)
   {
     return;
   }
   
   switch(type)
   {
     case 0:
      bytePixels[x+y*nx]=(byte)(int)value;
      break; 
   
     case 1:
       shortPixels[x+y*nx]=(short)(int)value;
       break;
       
     case 2:
       floatPixels[x+y*nx]=(float)value;
       break;
       
     case 6:
       doublePixels[x+y*nx]=value;
       break;
       
     case 3: // '\003'
     case 4: // '\004'
     case 5: // '\005'
       
     default:
         throw new ArrayStoreException("Unexpected image type");
       
       
   }
   
  }

  public void getNeighborhood(int x, int y, double[][] neigh) {
      int n =neigh.length;
      getNeighborhoodNxN(x,y,n,neigh);
    
  }

  
  public void getNeighborhoodNxN(int x,int y,int n,double neigh[][])
  {
    if(x < 0)
    {
        int periodx = 2 * nx - 2;
        for(; x < 0; x += periodx) { }
        if(x >= nx)
        {
            x = periodx - x;
        }
    } else
    if(x >= nx)
    {
        int periodx = 2 * nx - 2;
        for(; x >= nx; x -= periodx) { }
        if(x < 0)
        {
            x = -x;
        }
    }
    if(y < 0)
    {
        int periody = 2 * ny - 2;
        for(; y < 0; y += periody) { }
        if(y >= ny)
        {
            y = periody - y;
        }
    } else
    if(y >= ny)
    {
        int periody = 2 * ny - 2;
        for(; y >= ny; y -= periody) { }
        if(y < 0)
        {
            y = -y;
        }
    }
    int n2 = (n-1)/2;
    
  }
}

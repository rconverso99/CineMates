import java.io.File;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.filechooser.FileFilter;



public class ImageFileFiltrer extends FileFilter{
	public boolean accept(File file) {
	    if (file.isDirectory()) return true;
	    String fname = file.getName().toLowerCase();
	    return fname.endsWith("jpg");
	  }

	  public String getDescription() {
	    return "File jpeg";
	  }
	  public Image getScaledImage(Image srcImg, int w, int h){
		    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = resizedImg.createGraphics();

		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(srcImg, 0, 0, w, h, null);
		    g2.dispose();

		    return resizedImg;
		}	  
	  public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
		    int w = image.getWidth();
		    int h = image.getHeight();
		    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		    Graphics2D g2 = output.createGraphics();
		    
		    // This is what we want, but it only does hard-clipping, i.e. aliasing
		    // g2.setClip(new RoundRectangle2D ...)

		    // so instead fake soft-clipping by first drawing the desired clip shape
		    // in fully opaque white with antialiasing enabled...
		    g2.setComposite(AlphaComposite.Src);
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    g2.setColor(Color.WHITE);
		    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
		    
		    // ... then compositing the image on top,
		    // using the white shape from above as alpha source
		    g2.setComposite(AlphaComposite.SrcAtop);
		    g2.drawImage(image, 0, 0, null);
		    
		    g2.dispose();
		    
		    return output;
		}
	  public static BufferedImage toBufferedImage(Image img)
	  {
	      if (img instanceof BufferedImage)
	      {
	          return (BufferedImage) img;
	      }

	      // Create a buffered image with transparency
	      BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	      // Draw the image on to the buffered image
	      Graphics2D bGr = bimage.createGraphics();
	      bGr.drawImage(img, 0, 0, null);
	      bGr.dispose();

	      // Return the buffered image
	      return bimage;
	  }
	
	  	
	  	
	  }
	


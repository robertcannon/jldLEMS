package org.lemsml.jlems.graphics;

import javax.imageio.*;
 
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.*;
import java.io.*;

public class Logo {


	 
	// call with color, file name, destination folder
	public static void main(String[] argv) {
	 
		Logo nc = new Logo();
		
		nc.makeLogo();
	}
	
	
	public void makeLogo() {
		
		BufferedImage bim = makeImage(82, 82);
		 
		File outdir = new File("build");
		String fnm = "lemslogo.png";
		File fout = new File(outdir, fnm);
		writeImage(bim, fout);
	}


	public BufferedImage makeImage(int w, int h) {
	 
		 
		BufferedImage bim = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		 
		Graphics2D g = bim.createGraphics();
		GeneralPath gp = new GeneralPath();

		 g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	
		 Ellipse2D.Double disc = new Ellipse2D.Double(0, 0, w-2, h-2);
		 gp.append(disc, false);
		 g.setColor(new Color(0, 0, 0));
		 g.fill(gp);
		 
		 
		 g.setFont(new Font("Courier", Font.PLAIN, 32));
		 g.setColor(new Color(255, 255, 255));
		 
		 g.drawString("l", (int)(0.25 * w), (int) (0.44 * h));
		 g.drawString("e", (int)(0.55 * w), (int) (0.44* h));

		 g.drawString("m",  (int)(0.25 * w), (int) (0.81 * h));
		 g.drawString("s",  (int)(0.55 * w), (int) (0.81 * h));
	 
 
		g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		g.draw(gp);

		return bim;
	 

	}

  

 


	private void writeImage(BufferedImage bim, File fout) {
  	  
		try {
  	         ImageIO.write(bim, "png", fout);
  	         System.out.println("written " + fout);
  	     } catch(Exception ex) {
  	    	 System.out.println("ERROR - can't write file " + fout.getAbsolutePath());
  	    	 ex.printStackTrace();
  	     }

    }


    // Make a color from a hex string. "aabbcc"
    private static Color hexColor(String str) {
       return new Color(
        Integer.parseInt(str.substring(0,2), 16),
        Integer.parseInt(str.substring(2,4), 16),
        Integer.parseInt(str.substring(4,6), 16));

    }




}

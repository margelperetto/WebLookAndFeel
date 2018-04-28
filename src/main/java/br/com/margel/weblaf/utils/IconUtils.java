package br.com.margel.weblaf.utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class IconUtils implements SwingConstants{

	private static double getThetaRotate(int orientation){
		switch (orientation) {
		case NORTH:{	return Math.PI/2;
		}case SOUTH:{ 	return Math.PI*1.5;
		}case EAST:{ 	return Math.PI;
		}case WEST:{ 	return 0;//left
		}default: 		
			return 0;
		}
	}
	
	public static final String IMAGES_DIR = "images/";
	
	public static ImageIcon getImageIcon(String name){
		try {
			return new ImageIcon(IconUtils.class.getClassLoader().getResource(IMAGES_DIR+name));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static BufferedImage getBufferedImage(String name){
		try {
			return ImageIO.read(IconUtils.class.getClassLoader().getResource(IMAGES_DIR+name));
		} catch (Exception e) {
			return null;
		}
	}

	public static ImageIcon arrowIcon(Dimension d, int direction, int gap, Color color){
		BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)bi.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.rotate(getThetaRotate(direction),d.width/2,d.height/2);

		//LEFT
		GeneralPath gp = new GeneralPath();
		gp.moveTo((d.width*0.75) - gap, gap);
		gp.lineTo(gap, (d.height/2));
		gp.lineTo((d.width*0.75)-gap, d.height-gap);
		gp.lineTo((d.width*0.75)-gap, gap);
		gp.closePath();

		g2d.setColor(color);
		g2d.fill(gp);
		g2d.dispose();
		return new ImageIcon(bi);
	}
	
	public static ImageIcon checkIcon(Dimension d, boolean select, float alpha, Color bg, Color b, Color s){
		BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)bi.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha*0.75f));

		g2d.setColor(bg);
		g2d.fillRoundRect(0, 0, d.width-1, d.height-1, 4, 4);
		g2d.setColor(b);
		g2d.drawRoundRect(0, 0, d.width-1, d.height-1, 4, 4);

		if(select){
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
			g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

			GeneralPath gp = new GeneralPath();
			gp.moveTo(d.width*0.3, d.height*0.5);
			gp.lineTo(d.width*0.4, d.height*0.7);
			gp.lineTo(d.width*0.7, d.height*0.3);

			g2d.setColor(s);
			g2d.draw(gp);
		}
		g2d.dispose();
		return new ImageIcon(bi);
	}

	public static ImageIcon radioIcon(Dimension d, boolean select, float alpha, 
			Color bg, Color b, Color sel){
		BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)bi.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha*0.75f));

		Rectangle r = new Rectangle(0, 0, d.width-1, d.height-1);
		
		g2d.setColor(bg);
		g2d.fillOval(r.x, r.y, r.width, r.height);
		g2d.setColor(b);
		g2d.drawOval(r.x, r.y, r.width, r.height);

		if(select){
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
			Rectangle r2 = new Rectangle(
					r.x+3, 
					r.y+3, 
					r.width-5, 
					r.height-5);
			
			g2d.setColor(sel);
			g2d.fillOval(r2.x, r2.y, r2.width, r2.height);
		}
		g2d.dispose();
		return new ImageIcon(bi);
	}

}
package br.com.margel.weblaf.uis;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalProgressBarUI;

import br.com.margel.weblaf.WebTheme;
import sun.swing.SwingUtilities2;

@SuppressWarnings("restriction")
public class UIWebProgressBar extends MetalProgressBarUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebProgressBar();
	}

	private Rectangle rBox = new Rectangle();
	private RoundRectangle2D rr2d = new RoundRectangle2D.Double();
	private RoundRectangle2D rr2dValue = new RoundRectangle2D.Double();

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		c.setOpaque(false);
		c.setBorder(new EmptyBorder(1, 1, 1, 1));
		c.setBackground(WebTheme.PROGRESS_BG);
		c.setForeground(WebTheme.PROGRESS_FG);
	}

	@Override
	public void paintDeterminate(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		paintBackground(g2d, c);

		int amountFull = getAmountFull(c.getInsets(), c.getWidth(), c.getHeight());
		if(amountFull>0){
			g2d.setPaint(new GradientPaint(
					(int)(rr2d.getWidth()/2), 0, progressBar.getForeground(), 
					(int)(rr2d.getWidth()/2), (int)rr2d.getHeight(), progressBar.getForeground().darker())
					);
			boolean h = progressBar.getOrientation() == JProgressBar.HORIZONTAL;
			rr2dValue.setRoundRect(1, 1, h?amountFull:c.getWidth()-3,h?c.getHeight()-3:amountFull, 6, 6);
			g2d.fill(rr2dValue);
			g2d.setColor(progressBar.getForeground().darker());
			g2d.draw(rr2dValue);
		}
		if (progressBar.isStringPainted()) {
			Rectangle boxRect = progressBar.getBounds();
			Insets b = progressBar.getInsets();
			int barRectWidth = progressBar.getWidth() - (b.right + b.left);
			int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);
			if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
				paintString(g2d, b.left, b.top,
						barRectWidth, barRectHeight,
						boxRect.x, boxRect.width, b);
			}else {
				paintString(g2d, b.left, b.top,
						barRectWidth, barRectHeight,
						boxRect.y, boxRect.height, b);
			}
		}
		g2d.dispose();
	}

	private void paintString(Graphics g, int x, int y, int width, int height,int fillStart, int amountFull, Insets b) {
		try {
			Graphics2D g2 = (Graphics2D)g;
			String progressString = progressBar.getString();
			g2.setFont(progressBar.getFont());
			Point renderLocation = getStringPlacement(g2, progressString,x, y, width, height);
			Rectangle oldClip = g2.getClipBounds();

			g2.setColor(getSelectionForeground().darker());
			if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
				SwingUtilities2.drawString(progressBar, g2, progressString,renderLocation.x, renderLocation.y);
				g2.clipRect(fillStart, y, amountFull, height);
				SwingUtilities2.drawString(progressBar, g2, progressString,renderLocation.x, renderLocation.y);
			} else { // VERTICAL
				AffineTransform rotate =
						AffineTransform.getRotateInstance(Math.PI/2);
				g2.setFont(progressBar.getFont().deriveFont(rotate));
				renderLocation = getStringPlacement(g2, progressString,x, y, width, height);
				SwingUtilities2.drawString(progressBar, g2, progressString,renderLocation.x, renderLocation.y);
				g2.clipRect(x, fillStart, width, amountFull);
				SwingUtilities2.drawString(progressBar, g2, progressString,renderLocation.x, renderLocation.y);
			}
			g2.setClip(oldClip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintIndeterminate(Graphics g, JComponent c) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		paintBackground(g2d, c);

		try{
			rBox = getBox(rBox);
		}catch(Exception e){/*nothing to do here ;D*/}

		if(rBox!=null){
			g2d.setColor(progressBar.getForeground());
			
			if(progressBar.getOrientation() == JProgressBar.HORIZONTAL){
				rr2dValue.setRoundRect(rBox.x, rBox.y+rBox.height*0.25,rBox.width-1, rBox.height/2, 6, 6);
			}else{
				rr2dValue.setRoundRect(rBox.x+rBox.width*0.25, rBox.y,rBox.width/2+1, rBox.height-1, 6, 6);
			}
			g2d.fill(rr2dValue);
		}
		g2d.dispose();
	}

	private void paintBackground(Graphics2D g2d, JComponent c){
		if(progressBar.isIndeterminate()){
			if(progressBar.getOrientation() == JProgressBar.HORIZONTAL){
				rr2d.setRoundRect(0, c.getHeight()*0.25, c.getWidth()-1, c.getHeight()/2, 6, 6);
			}else{
				rr2d.setRoundRect(c.getWidth()*0.25, 0, c.getWidth()/2, c.getHeight()-1, 6, 6);
			}
		}else{
			rr2d.setRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 6, 6);
		}
		g2d.setColor(c.getBackground());
		g2d.fill(rr2d);
		g2d.setColor(c.getForeground());
		g2d.draw(rr2d);
	}
}
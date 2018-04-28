package br.com.margel.weblaf.uis;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalSliderUI;

import br.com.margel.weblaf.WebTheme;

public class UIWebSlider extends MetalSliderUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebSlider();
	}

	private Ellipse2D oval = new Ellipse2D.Double();

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		slider.setOpaque(false);
		slider.setBackground(WebTheme.SLIDER_BUTTON_BG);
		slider.setForeground(WebTheme.SLIDER_BUTTON_FG);
	}

	@Override
	public void paintTrack(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(!slider.isEnabled()){
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}

		Line2D line = null;
		if(slider.getOrientation() == JSlider.HORIZONTAL){
			int h = slider.getHeight()/2 -1;
			line = new Line2D.Double(0, h, slider.getWidth(), h);
		}else{
			int w = slider.getWidth()/2 -1;
			line = new Line2D.Double(w, 0, w, slider.getHeight());
		}
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(slider.isFocusOwner()?WebTheme.SLIDER_FOCO:slider.getForeground());
		g2d.draw(line);

		g2d.dispose();
	}

	@Override
	public void paintThumb(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Color color = slider.isFocusOwner()?WebTheme.SLIDER_FOCO:slider.getBackground();
		if(!slider.isEnabled()){
			color = color.brighter().brighter();
		}
		
		double size = slider.getOrientation()==JSlider.HORIZONTAL?
				thumbRect.height-2:thumbRect.width-2;
		oval.setFrame(thumbRect.x,thumbRect.y,size,size);

		g2d.setColor(color);
		g2d.fill(oval);
		
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(color);
		g2d.draw(oval);

		g2d.dispose();
	}
}
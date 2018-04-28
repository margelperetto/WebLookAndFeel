package br.com.margel.weblaf.uis;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalLabelUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.borders.WebBorder;

public class UIWebLabel extends MetalLabelUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebLabel();
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		c.setFont(WebTheme.LABEL_FONT);
		c.setForeground(WebTheme.LABEL_FG);
	}
	
	@Override
	public void update(Graphics g, JComponent c) {
		if(c.getBorder() instanceof WebBorder){
			if (c.isOpaque()) {
				WebBorder b = (WebBorder) c.getBorder();
				paintCustomBackgroud(g, c, b);
			}
			paint(g, c);
		}else{
			super.update(g, c);
		}
    }
	
	private void paintCustomBackgroud(Graphics g, JComponent c, WebBorder b) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(c.getBackground());
		g2d.fillRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, b.getArcWidth(), b.getArcHeight());
		g2d.dispose();
	}
}
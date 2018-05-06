package br.com.margel.weblaf.uis;

import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.borders.WebBorder;
import br.com.margel.weblaf.utils.ComponentUtils;
import br.com.margel.weblaf.utils.RepaintUtils;

public class UIWebButton extends MetalButtonUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebButton();
	}

	private float alpha = 1f;//mouse entered can modify

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		AbstractButton b = (AbstractButton)c;
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b.setBackground(WebTheme.BUTTON_BG);
		b.setForeground(WebTheme.BUTTON_FG);
		b.setFont(WebTheme.BUTTON_FONT);
		b.addMouseListener(new MouseEnteredListener());
		RepaintUtils.installListeners(c);
		
		WebBorder webBorder = new WebBorder();
		webBorder.setArcHeight(6);
		webBorder.setArcWidth(6);
		b.setBorder(webBorder);
	}
	
	@Override
	public void update(Graphics g, JComponent c) {
		if (!usingWebBorder(c)) {
			super.update(g, c);
		} else {
			WebBorder b = (WebBorder)c.getBorder();
			b.setColor(c.getBackground());
			if(paintBackgroundAllowed(c)){
				paintCustomBackgroud(g, c, b, alpha);
			} 
			super.paint(g, c);
		}
	}

	protected boolean paintBackgroundAllowed(JComponent c) {
		return c.isOpaque();
	}

	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton c) {
		if(!usingWebBorder(c)){
			super.paintButtonPressed(g, c);
		} else if(c.isContentAreaFilled()){
			WebBorder b = (WebBorder)c.getBorder();
			paintCustomBackgroud(g, c, b, 0.7f);
		}
	}
	
	protected void paintCustomBackgroud(Graphics g, JComponent c, WebBorder b, float alpha) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(ComponentUtils.getParentBg(c));
		g2d.fillRect(0, 0, c.getWidth(), c.getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.setColor(c.getBackground());
		g2d.fillRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, b.getArcWidth(), b.getArcHeight());
		g2d.dispose();
	}

	@Override
	protected void paintFocus(Graphics g, AbstractButton c, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
		if(!usingWebBorder(c)){
			super.paintFocus(g, c, viewRect, textRect, iconRect);
		}else{
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(c.getForeground());
			g2d.drawLine(textRect.x, textRect.y+textRect.height, textRect.x + textRect.width, textRect.y+textRect.height);
			g2d.dispose();
		}
	}
	
	private boolean usingWebBorder(JComponent c){
		return c.getBorder() instanceof WebBorder;
	}
	
	private class MouseEnteredListener extends MouseAdapter{
		@Override
		public void mouseEntered(MouseEvent e) {
			alpha = 0.85f;
			e.getComponent().repaint();
		}
		@Override
		public void mouseExited(MouseEvent e) {
			alpha = 1f;
			e.getComponent().repaint();
		}
		@Override
		public void mousePressed(MouseEvent e) {
			alpha = 1f;
			e.getComponent().repaint();
		}
	}
}
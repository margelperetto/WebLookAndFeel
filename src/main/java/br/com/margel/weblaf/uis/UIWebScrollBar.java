package br.com.margel.weblaf.uis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalScrollBarUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.utils.IconUtils;

public class UIWebScrollBar extends MetalScrollBarUI {

	public static ComponentUI createUI(JComponent c){
		return new UIWebScrollBar();
	}
	
	private static final Color buttonArrow = WebTheme.SCROLLBAR_ARROW;
	private static final ImageIcon NORTH_ICON = IconUtils.arrowIcon(new Dimension(10, 10), TOP,0, buttonArrow);
	private static final ImageIcon SOUTH_ICON = IconUtils.arrowIcon(new Dimension(10, 10), SOUTH,0, buttonArrow);
	private static final ImageIcon WEST_ICON = IconUtils.arrowIcon(new Dimension(10, 10), WEST,0, buttonArrow);
	private static final ImageIcon EAST_ICON = IconUtils.arrowIcon(new Dimension(10, 10), EAST,0, buttonArrow);

	private RoundRectangle2D rr2d = new RoundRectangle2D.Double();

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		scrollbar.setOpaque(true);
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle tb) {
		if(c.isOpaque()){
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(WebTheme.SCROLLBAR_TRACK);
			g2d.fillRect(tb.x, tb.y, tb.width-1, tb.height-1);
			g2d.dispose();
		}
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle oriRec) {
		Rectangle r = new Rectangle(oriRec.x, oriRec.y, oriRec.width-1, oriRec.height-1);
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(WebTheme.SCROLLBAR_THUMB);
		rr2d.setRoundRect(r.x, r.y, r.width, r.height, 4, 4);
		g2d.fill(rr2d);

		g2d.dispose();
	}

	@Override
	protected JButton createDecreaseButton(int orientation) {
		return createButton(orientation);
	}
	
	@Override
	protected JButton createIncreaseButton(int orientation) {
		return createButton(orientation);
	}

	private JButton createButton(int orientation){
		JButton b = new JButton();
		b.setFocusable(false);
		b.setOpaque(false);
		int s = 3;
		b.setBorder(new EmptyBorder(s, s, s, s));
		switch (orientation) {
		case NORTH:
			b.setIcon(NORTH_ICON);
			break;
		case SOUTH:
			b.setIcon(SOUTH_ICON);
			break;
		case EAST:
			b.setIcon(EAST_ICON);
			break;
		case WEST:
			b.setIcon(WEST_ICON);
			break;
		default:
			break;
		}
		return b;
	}
}
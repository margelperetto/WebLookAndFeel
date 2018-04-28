package br.com.margel.weblaf.uis;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

import br.com.margel.weblaf.WebTheme;

public class UIWebTabbedPane extends MetalTabbedPaneUI{

	private boolean tabsOverlapBorder;

	public static ComponentUI createUI(JComponent c){
		return new UIWebTabbedPane();
	}
	
	private Color unselColor = WebTheme.TABBED_UNSELECT;
	private Color selColor = WebTheme.TABBED_SELECT;
	
	private Color selBorder = WebTheme.TABBED_SELECT_BORDER;
	private Color unselBorder = WebTheme.TABBED_UNSELECT_BORDER;
	
	private Color focus = WebTheme.TABBED_FOCUS;
	
	private Color contentBorder = WebTheme.TABBED_CONTENT_BORDER;
	private Color selBorderUnderline = WebTheme.TABBED_SEL_BORDER_UNDERLINE;

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		tabsOverlapBorder = UIManager.getBoolean("TabbedPane.tabsOverlapBorder");
		LookAndFeel.installProperty(tabPane, "opaque", Boolean.FALSE);
		c.setFocusable(false);
		c.setForeground(WebTheme.TABBED_FG);
	}
	
	@Override
	protected void paintContentBorder(Graphics g, int tabPlacement,int selectedIndex) {
		Rectangle r = getContentRectangle(tabPlacement);
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(contentBorder);
		g2d.drawRoundRect(r.x,r.y,r.width-1,r.height-1,1,1);

		Rectangle rs = getTabBounds(selectedIndex, calcRect);
		g2d.setStroke(new BasicStroke(2f));
		g2d.setColor(selBorderUnderline);
		
		switch (tabPlacement) {
		case TOP:
			g2d.drawLine(rs.x, rs.y+rs.height-1, rs.x+rs.width, rs.y+rs.height-1);
			break;
		case BOTTOM:
			g2d.drawLine(rs.x, rs.y, rs.x+rs.width, rs.y);
			break;
		case LEFT:
			g2d.drawLine(rs.x+rs.width-1, rs.y, rs.x+rs.width-1, rs.y+rs.height);
			break;
		case RIGHT:
			g2d.drawLine(rs.x, rs.y, rs.x, rs.y+rs.height);
			break;
		}

		g2d.dispose();
	}

	@Override
	protected void paintFocusIndicator(Graphics g, int tabPlacement,Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
		if ( tabPane.hasFocus() && isSelected ) {
			Graphics2D g2d = (Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(focus);
			g2d.drawRoundRect(textRect.x-3, textRect.y, textRect.width+6, textRect.height-1, 10, 10);
			g2d.dispose();
		}
	}

	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement,int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		Rectangle r = new Rectangle(x,y,w,h);

		Graphics2D g2d = (Graphics2D)g.create();

		if(tabPlacement == BOTTOM){
			g2d.rotate(Math.PI, r.x + (r.width/2), r.y+(r.height/2));
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Color color = isSelected?selColor:unselColor;
		g2d.setColor(color);
		g2d.fill(r);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g2d.setColor(isSelected?selBorder:unselBorder);
		g2d.draw(r);

		g2d.dispose();
	}

	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		/*NO BORDER*/
	}

	//COPIADO DO METALUI
	private Rectangle getContentRectangle(int tabPlacement){
		int width = tabPane.getWidth();
		int height = tabPane.getHeight();
		Insets insets = tabPane.getInsets();
		Insets tabAreaInsets = getTabAreaInsets(tabPlacement);

		int x = insets.left;
		int y = insets.top;
		int w = width - insets.right - insets.left;
		int h = height - insets.top - insets.bottom;

		switch(tabPlacement) {
		case LEFT:
			x += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
			if (tabsOverlapBorder) {
				x -= tabAreaInsets.right;
			}
			w -= (x - insets.left);
			break;
		case RIGHT:
			w -= calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
			if (tabsOverlapBorder) {
				w += tabAreaInsets.left;
			}
			break;            
		case BOTTOM: 
			h -= calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
			if (tabsOverlapBorder) {
				h += tabAreaInsets.top;
			}
			break;
		case TOP:
		default:
			y += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
			if (tabsOverlapBorder) {
				y -= tabAreaInsets.bottom;
			}
			h -= (y - insets.top);
		} 

		return new Rectangle(x, y, w, h);
	}
}
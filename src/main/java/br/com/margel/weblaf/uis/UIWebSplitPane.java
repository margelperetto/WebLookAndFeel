package br.com.margel.weblaf.uis;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.metal.MetalSplitPaneUI;

import br.com.margel.weblaf.WebTheme;

public class UIWebSplitPane extends MetalSplitPaneUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebSplitPane();
	}
	
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		c.setOpaque(false);
		c.setBorder(new EmptyBorder(1,1,1,1));
		c.setForeground(WebTheme.SEPARATOR_COLOR);
		if(c instanceof JSplitPane){
			((JSplitPane)c).setContinuousLayout(true);
		}
	}
	
	@Override
	public BasicSplitPaneDivider createDefaultDivider() {
		return new SplitPaneDividerDark(this);
	}
	
	@SuppressWarnings("serial")
	private class SplitPaneDividerDark extends BasicSplitPaneDivider{

		public SplitPaneDividerDark(BasicSplitPaneUI ui) {
			super(ui);
			setBorder(new EmptyBorder(1, 1, 1, 1));
		}
		
		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(splitPane.getForeground());
			g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			if(orientation == JSplitPane.HORIZONTAL_SPLIT){
				g2d.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
			}else{
				g2d.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
			}
			g2d.dispose();
		}
	}
}
package br.com.margel.weblaf.uis;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.CellRendererPane;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;

import br.com.margel.weblaf.WebTheme;

public class UIWebTable extends BasicTableUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebTable();
	}
	
	private Color focusColor;
	
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		table.setShowGrid(true);
		table.setRowHeight(table.getFontMetrics(table.getFont()).getHeight()+10);
		rendererPane = new CellRendererPaneDark();
		table.add(rendererPane);
		focusColor = WebTheme.TABLE_CEL_FOCUS;
		table.addPropertyChangeListener("Table.focusCellColor", (evt)->{
			focusColor = (Color) evt.getNewValue();
		}); 
	}

	@SuppressWarnings("serial")
	private class CellRendererPaneDark extends CellRendererPane{
		@Override
		public void paintComponent(Graphics g, Component c, Container p, int x, int y, int w, int h, boolean shouldValidate) {
			boolean foco = false;
			if(c instanceof JComponent){
				JComponent u = (JComponent)c;
				if(u.getBorder()!=null && !(u.getBorder() instanceof EmptyBorder)){
					foco = true;
				}
				u.setOpaque(false);
				u.setBorder(new EmptyBorder(5, 5, 5, 5));
			}
			Graphics2D g2d = (Graphics2D)g.create();
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(c.getBackground());
			g2d.fillRect(x, y, w, h);
			if(foco){
				g2d.setColor(focusColor);
				g2d.drawRect(x, y, w-1, h-1);
			}
			g2d.dispose();

			super.paintComponent(g, c, p, x, y, w, h, shouldValidate);
		}
	}
}

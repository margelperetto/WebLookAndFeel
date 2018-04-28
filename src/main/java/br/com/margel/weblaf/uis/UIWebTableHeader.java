package br.com.margel.weblaf.uis;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.CellRendererPane;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

public class UIWebTableHeader extends BasicTableHeaderUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebTableHeader();
	}
	
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		header.remove(rendererPane);
		this.rendererPane = new WebCellRendererPane();
		header.add(rendererPane);
	}
	
	@SuppressWarnings("serial")
	private class WebCellRendererPane extends CellRendererPane{
		
		@Override
		public void paintComponent(Graphics g, Component c, Container p, int x, int y, int w, int h, boolean shouldValidate) {
			JComponent comp = (JComponent)c;
			if(needReplaceBorder(comp)) {
				comp.setBorder(new WebTableHeaderBorder());
			}
			super.paintComponent(g, c, p, x, y, w, h, shouldValidate);
		}

		private boolean needReplaceBorder(JComponent comp) {
			Border b = comp.getBorder();
			return b!=null && !(b instanceof WebTableHeaderBorder) && (b instanceof TableHeaderBorder);
		}
		
	}
	
	@SuppressWarnings("serial")
	private class WebTableHeaderBorder extends TableHeaderBorder{
		
		public WebTableHeaderBorder() {
			editorBorderInsets.set(2, 5, 2, 5);
		}
		
		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(2, 5, 2, 5);
            return insets;
        }
	}
}

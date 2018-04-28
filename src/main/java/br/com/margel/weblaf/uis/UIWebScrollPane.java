package br.com.margel.weblaf.uis;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.LookAndFeel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalScrollPaneUI;

import br.com.margel.weblaf.WebTheme;

public class UIWebScrollPane extends MetalScrollPaneUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebScrollPane();
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);
		LookAndFeel.installProperty(scrollpane, "opaque", Boolean.FALSE);
		scrollpane.setColumnHeader(new Header());
		scrollpane.setBorder(new LineBorder(WebTheme.SCROLL_BORDER));
	}

	@SuppressWarnings("serial")
	private class Header extends JViewport{
		public Header() {
			super();
			setOpaque(false);
		}
		@Override 
		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			if(d!=null && d.height>0){
				d.height = d.height+5;
			}
			return d;
		}
	}
}	
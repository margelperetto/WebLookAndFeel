package br.com.margel.weblaf.uis;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalSeparatorUI;

import br.com.margel.weblaf.WebTheme;

public class UIWebSeparator extends MetalSeparatorUI {

	public static ComponentUI createUI(JComponent c) {
		return new UIWebSeparator();
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		c.setForeground(WebTheme.SEPARATOR_FOREGROUND);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Dimension s = c.getSize();
		if (((JSeparator) c).getOrientation() == JSeparator.VERTICAL) {
			g.setColor(c.getForeground());
			g.drawLine(0, 0, 0, s.height);
		} else { // HORIZONTAL
			g.setColor(c.getForeground());
			g.drawLine(0, 0, s.width, 0);
		}
	}
}

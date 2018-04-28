package br.com.margel.weblaf.uis;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.JTextComponent;

import br.com.margel.weblaf.WebTheme;

public class UIWebTextArea extends BasicTextAreaUI {

	public static ComponentUI createUI(JComponent ta) {
		return new UIWebTextArea();
	}

	@Override
	public void installUI(final JComponent c) {
		super.installUI(c);
		if (c instanceof JTextComponent) {
			c.addPropertyChangeListener(evt -> {
				if ("foreground".equals(evt.getPropertyName())) {
					((JTextComponent) c).setCaretColor(c.getForeground());
				}
			});
			((JTextComponent) c).setMargin(WebTheme.TEXTAREA_MARGIN);
		}
		c.setForeground(WebTheme.TEXTAREA_FG);
		c.setBackground(WebTheme.TEXTAREA_BG);
		c.setFont(WebTheme.TEXTAREA_FONT);
	}
}

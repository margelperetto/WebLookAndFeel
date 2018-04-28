package br.com.margel.weblaf.uis;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

public class UIWebFormattedTextField extends UIWebTextField{

	public static ComponentUI createUI(JComponent c){
		return new UIWebFormattedTextField();
	}

	@Override
	protected String getPropertyPrefix() {
		return "FormattedTextField";
	}
}

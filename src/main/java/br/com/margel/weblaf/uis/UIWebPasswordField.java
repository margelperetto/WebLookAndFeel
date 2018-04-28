package br.com.margel.weblaf.uis;

import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.Element;
import javax.swing.text.PasswordView;
import javax.swing.text.View;

public class UIWebPasswordField extends UIWebTextField{

	public static ComponentUI createUI(JComponent c){
		return new UIWebPasswordField();
	}

	@Override
	protected String getPropertyPrefix() {
		return "PasswordField";
	}

	@Override
	protected void installDefaults() {
		super.installDefaults();
		String prefix = getPropertyPrefix();
		Character echoChar = (Character)UIManager.getDefaults().get(prefix + ".echoChar");
		if(echoChar != null) {
			LookAndFeel.installProperty(getComponent(), "echoChar", echoChar);
		}
	}

	@Override
	public View create(Element elem) {
		return new PasswordView(elem);
	}
}
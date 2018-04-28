package br.com.margel.weblaf.uis;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalCheckBoxUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.utils.IconUtils;

public class UIWebRadioButton extends MetalCheckBoxUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebRadioButton();
	}

	private JRadioButton radio;

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		radio = (JRadioButton) c;
		radio.setOpaque(false);
		radio.setBackground(WebTheme.RADIO_BG);
		radio.setForeground(WebTheme.RADIO_FG);
		radio.setFont(WebTheme.RADIO_FONT);
	}

	@Override
	public Icon getDefaultIcon() {
		int h = 15;
		return IconUtils.radioIcon(new Dimension(h, h), radio.isSelected(), radio.isEnabled()?1f:0.45f, 
				radio.getBackground(), radio.getForeground(), radio.getForeground());
	}
}
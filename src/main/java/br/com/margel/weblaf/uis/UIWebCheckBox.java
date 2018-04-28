package br.com.margel.weblaf.uis;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalCheckBoxUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.utils.IconUtils;

public class UIWebCheckBox extends MetalCheckBoxUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebCheckBox();
	}

	private JCheckBox ckb;

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		ckb = (JCheckBox) c;
		ckb.setOpaque(false);
		ckb.setBackground(WebTheme.CHECK_BG);
		ckb.setForeground(WebTheme.CHECK_FG);
		ckb.setFont(WebTheme.CHECK_FONT);
	}

	@Override
	public Icon getDefaultIcon() {
		int h = 15;
		return IconUtils.checkIcon(new Dimension(h, h), ckb.isSelected(), ckb.isEnabled()?1f:0.45f,
				ckb.getBackground(), ckb.getForeground(), ckb.getForeground());
	}
}
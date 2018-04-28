package br.com.margel.weblaf.uis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.utils.IconUtils;

public class UIWebRadioButtonMenuItem extends BasicCheckBoxMenuItemUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebRadioButtonMenuItem();
	}
	private final int SIZE = 15;
	private JRadioButtonMenuItem radio;
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		radio = (JRadioButtonMenuItem)c;
		this.checkIcon = IconUtils.radioIcon(new Dimension(SIZE, SIZE), radio.isSelected(), radio.isEnabled()?1f:0.45f, 
				radio.getBackground(), radio.getForeground(), radio.getForeground());
	}

	@Override
	protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon,Icon arrowIcon, Color background, Color foreground,int defaultTextIconGap) {
		this.checkIcon = checkIcon = IconUtils.radioIcon(new Dimension(SIZE, SIZE), radio.isSelected(), radio.isEnabled()?1f:0.45f,
				radio.getBackground(), radio.getForeground(), WebTheme.CHECKBOX_MENUITEM_SELECT);
		super.paintMenuItem(g, c, checkIcon, arrowIcon, background, foreground,defaultTextIconGap);
	}
}

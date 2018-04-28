package br.com.margel.weblaf.uis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.utils.IconUtils;

public class UIWebCheckBoxMenuItem extends BasicCheckBoxMenuItemUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebCheckBoxMenuItem();
	}
	private final int SIZE = 15;
	private JCheckBoxMenuItem ckb;
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		ckb = (JCheckBoxMenuItem)c;
		this.checkIcon = IconUtils.checkIcon(new Dimension(SIZE, SIZE), ckb.isSelected(), ckb.isEnabled()?1f:0.45f,
				ckb.getBackground(), ckb.getForeground(), ckb.getForeground());
	}

	@Override
	protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon,Icon arrowIcon, Color background, Color foreground,int defaultTextIconGap) {
		this.checkIcon = checkIcon = IconUtils.checkIcon(new Dimension(SIZE, SIZE), ckb.isSelected(), ckb.isEnabled()?1f:0.45f,
				ckb.getBackground(), ckb.getForeground(), WebTheme.CHECKBOX_MENUITEM_SELECT);
		super.paintMenuItem(g, c, checkIcon, arrowIcon, background, foreground,defaultTextIconGap);
	}
}

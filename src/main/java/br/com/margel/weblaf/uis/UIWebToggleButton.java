package br.com.margel.weblaf.uis;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.plaf.ComponentUI;

import br.com.margel.weblaf.borders.WebBorder;

public class UIWebToggleButton extends UIWebButton{

	public static ComponentUI createUI(JComponent c){
		return new UIWebToggleButton();
	}
	
	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
	}
	
	@Override
	protected boolean paintBackgroundAllowed(JComponent c) {
		return c.isOpaque() || ((JToggleButton)c).isSelected();
	}
	
	@Override
	protected void paintCustomBackgroud(Graphics g, JComponent c, WebBorder b, float alpha) {
		boolean sel = ((JToggleButton)c).isSelected();
		if(sel){
			alpha = 1f;
		} else {
			alpha = 0.65f;
		}
		super.paintCustomBackgroud(g, c, b, alpha);
	}
}
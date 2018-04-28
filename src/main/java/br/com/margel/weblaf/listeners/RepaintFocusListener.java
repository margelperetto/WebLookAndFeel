package br.com.margel.weblaf.listeners;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import br.com.margel.weblaf.utils.RepaintUtils;

public class RepaintFocusListener extends FocusAdapter {

	@Override
	public void focusGained(FocusEvent e) {
		RepaintUtils.runDeepRepaint(e.getComponent());
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		RepaintUtils.runDeepRepaint(e.getComponent());
	}
	
}

package br.com.margel.weblaf.listeners;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import br.com.margel.weblaf.utils.RepaintUtils;

public class RepaintComponentListener extends ComponentAdapter {

	@Override
	public void componentResized(ComponentEvent e) {
		RepaintUtils.runDeepRepaint(e.getComponent());
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
		RepaintUtils.runDeepRepaint(e.getComponent());
	}
}

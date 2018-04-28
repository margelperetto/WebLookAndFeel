package br.com.margel.weblaf.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import javax.swing.JComponent;
import br.com.margel.weblaf.listeners.RepaintComponentListener;
import br.com.margel.weblaf.listeners.RepaintFocusListener;

public class RepaintUtils {
	
	private RepaintUtils() {}

	public static void runDeepRepaint(Component comp){
		repaintToFront(getDeep(comp));
	}
	
	private static Component getDeep(Component comp){
		if(comp==null || comp.getParent()==null || comp instanceof Window){
			return comp;
		}
		return getDeep(comp.getParent());
	}
	
	private static void repaintToFront(Component comp){
		if(comp == null){
			return;
		}
		comp.validate();
		comp.repaint();
		if(comp instanceof Container){
			for(Component comp2 : ((Container)comp).getComponents()){
				repaintToFront(comp2);
			}
		}
	}

	public static void installListeners(JComponent c) {
		c.addFocusListener(new RepaintFocusListener());
		c.addComponentListener(new RepaintComponentListener());
		c.addPropertyChangeListener(e->{
			//XXX not run deep repaint for all properties
			RepaintUtils.runDeepRepaint(c);
		});
	}
}

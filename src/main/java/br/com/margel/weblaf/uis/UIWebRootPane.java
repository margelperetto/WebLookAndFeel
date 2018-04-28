package br.com.margel.weblaf.uis;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalRootPaneUI;

public class UIWebRootPane extends MetalRootPaneUI{
	
	public static ComponentUI createUI(JComponent c){
		return new UIWebRootPane();
	}

}

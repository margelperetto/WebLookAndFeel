package br.com.margel.weblaf.uis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicOptionPaneUI;

public class UIWebOptionPane extends BasicOptionPaneUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebOptionPane();
	}

	@Override
	protected void addButtonComponents(Container container, Object[] buttons, int initialIndex) {
		super.addButtonComponents(container, buttons, initialIndex);
		try {
			for(Component c : container.getComponents()){
				if(c instanceof JButton){
					JButton btn = (JButton) c;
					btn.setOpaque(false);
					btn.setForeground(Color.BLACK);
					btn.setBackground(Color.GRAY);
				}
			}
		} catch (Exception e) {}
	}
	
	@Override
	protected Container createMessageArea() {
		Container c = super.createMessageArea();
		updateOpaque(c);
		return c;
	}
	
	@Override
	protected Container createButtonArea() {
		Container c = super.createButtonArea();
		((JPanel)c).setBorder(new EmptyBorder(30, 30, 5, 30));
		updateOpaque(c);
		return c;
	}
	
	private void updateOpaque(Component component){
		try {
			if(component == null)
				return;
			if(component instanceof JPanel){
				((JPanel)component).setOpaque(false);
			}
			if(component instanceof Container){
				for(Component comp : ((Container)component).getComponents()){
					updateOpaque(comp);
				}
			}
		} catch (Exception e) {/*nothing to do here*/}
	}
}
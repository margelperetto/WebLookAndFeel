package br.com.margel.weblaf.uis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.borders.WebBorder;
import br.com.margel.weblaf.utils.IconUtils;

@SuppressWarnings({"serial","rawtypes"})
public class UIWebComboBox extends MetalComboBoxUI{

	public static ComponentUI createUI(JComponent c){
		return new UIWebComboBox();
	}

	private Color selForeground;
	private Color selBackground;
	private Color focusColor;
	private WebBorder webBorder;

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		comboBox.setFont(WebTheme.COMBO_FONT);
		comboBox.setForeground(WebTheme.COMBO_FG);
		comboBox.setBackground(WebTheme.COMBO_BG);
		listBox.setSelectionForeground(comboBox.getForeground());
		
		selForeground = WebTheme.COMBO_SELECT_FG;
		selBackground = WebTheme.COMBO_SELECT_BG;
		focusColor = WebTheme.COMBO_FOCO;
		
		webBorder = new WebBorder(new Insets(0, 0, 0, 0));
		webBorder.setColor(WebTheme.COMBO_BORDER);
		webBorder.arcSize(WebTheme.COMBO_BORDER_ROUND);
		
		comboBox.setBorder(webBorder);
		comboBox.addFocusListener(new BorderPaintFocusAdapter());
	}

	@Override
	protected ComboPopup createPopup() {
		return new ComboPopup(comboBox);
	}

	@Override
	protected ComboBoxEditor createEditor() {
		ComboBoxEditor editor = super.createEditor();
		Component c = editor.getEditorComponent();
		c.addFocusListener(new BorderPaintFocusAdapter());
		if(c instanceof JComponent){
			JComponent jc = (JComponent)c;
			jc.setBorder(new EmptyBorder(6, leftBorder(), 6, 1));
			jc.setOpaque(false);
		}
		return editor;
	}

	@Override
	protected ListCellRenderer createRenderer() {
		return (JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) -> {
			String text = value==null?"":value.toString();
			JLabel t = new JLabel(text);
			if(comboBox!=null && comboBox.getFont()!=null){
				t.setFont(comboBox.getFont());
			}
			t.setOpaque(index>=0);
			if(comboBox!=null){
				t.setBackground(isSelected?selBackground:comboBox.getBackground());
				t.setForeground(isSelected?selForeground:comboBox.getForeground());
			}
			int spc = index>=0?6:0;
			t.setBorder(new EmptyBorder(spc, leftBorder(), spc, 3));
			return t;
		};
	}
	
	private int leftBorder() {
		return WebTheme.TEXT_FIELD_BORDER_INSETS.left;
	}

	@Override
	public void paintCurrentValueBackground(Graphics g, Rectangle b, boolean hasFocus) {
		/*Nothing to do here*/
	}

	@Override
	protected JButton createArrowButton() {
		final ImageIcon icon = IconUtils.arrowIcon(new Dimension(15, 15), SwingConstants.SOUTH,2, WebTheme.COMBO_ARROW);

		final JButton b = new JButton(){
			@Override
			@Deprecated
			public boolean isFocusTraversable() {
				return false;
			}
		};
		
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setFocusable(false);
		b.setBackground(WebTheme.COMBO_BUTTON_BG);
		b.setIcon(icon);
		return b;
	}

	private class ComboPopup extends BasicComboPopup{
		public ComboPopup(JComboBox combo) {
			super(combo);
			setBorder(new WebBorder(1).arcSize(1).color(WebTheme.COMBO_BORDER));
		}
		@Override
		protected JScrollPane createScroller() {
			JScrollPane sp = super.createScroller();
			JScrollBar sb = sp.getVerticalScrollBar();
			if(sb!=null){
				sb.setOpaque(true);
				sb.setBackground(comboBox.getBackground());
			}
			return sp;
		}
	}
	
	private class BorderPaintFocusAdapter extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			webBorder.setColor(focusColor);
		}
		@Override
		public void focusLost(FocusEvent e) {
			webBorder.setColor(WebTheme.COMBO_BORDER);
		}
	}
	
}
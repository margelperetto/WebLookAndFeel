package br.com.margel.weblaf.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class LB extends JLabel{
	private static final long serialVersionUID = 1L;

	public LB() {}
	
	public LB(String text) {
		super(text);
	}
	
	public LB html(String html){
		this.setText("<html>"+html+"</html>");
		return this;
	}
	
	public LB fg(Color fg){
		this.setForeground(fg);
		return this;
	}
	
	public LB fg(String fg){
		return fg(Color.decode(fg));
	}
	
	public LB bg(String bg){
		return bg(Color.decode(bg));
	}
	
	public LB bg(Color bg){
		this.setBackground(bg);
		this.setOpaque(true);
		return this;
	}
	
	public LB font(float size){
		this.setFont(this.getFont().deriveFont(size));
		return this;
	}
	
	public LB font(int style){
		this.setFont(this.getFont().deriveFont(style));
		return this;
	}
	
	public LB font(int style, float size){
		this.setFont(this.getFont().deriveFont(style, size));
		return this;
	}
	
	public LB font(String name){
		return this.font(name, this.getFont().getStyle(), this.getFont().getSize());
	}
	
	public LB font(String name, int style, float size){
		this.setFont(new Font(name, style, (int)size));
		return this;
	}
	
	public LB icon(Icon icon){
		this.setIcon(icon);
		return this;
	}
	
	public LB hTextPos(int horizontalTextPosition){
		this.setHorizontalTextPosition(horizontalTextPosition);
		return this;
	}
	
	public LB hAlign(int horizontalAlign){
		this.setHorizontalAlignment(horizontalAlign);
		return this;
	}
	
	public LB vAlign(int verticalAlign){
		this.setVerticalAlignment(verticalAlign);
		return this;
	}
	
	public LB vTextPos(int verticalTextPosition){
		this.setVerticalTextPosition(verticalTextPosition);
		return this;
	}
	
	public LB textPos(int horizontalTextPosition, int verticalTextPosition){
		this.setHorizontalTextPosition(horizontalTextPosition);
		this.setVerticalTextPosition(verticalTextPosition);
		return this;
	}

	public LB border(Border border) {
		this.setBorder(border);
		return this;
	}
	
	public LB cursor(int type) {
		this.setCursor(new Cursor(type));
		return this;
	}
	
	public LB toolTip(String text) {
		this.setToolTipText(text);
		return this;
	}
	
	public LB mouseListener(MouseListener listener) {
		this.addMouseListener(listener);
		return this;
	}
	
}

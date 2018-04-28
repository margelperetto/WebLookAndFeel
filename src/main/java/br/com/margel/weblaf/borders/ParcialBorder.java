package br.com.margel.weblaf.borders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.UIManager;
import javax.swing.border.Border;

public class ParcialBorder implements Border {
	private boolean top;
	private boolean bottom;
	private boolean left;
	private boolean right;
	private Color color = Color.black;
	private Insets insets = new Insets(1, 1, 1, 1);
	private BasicStroke stroke = new BasicStroke(1);
	
	public ParcialBorder() {
		
	}
	public ParcialBorder(boolean top,boolean botton,boolean left,boolean right){
		this(top, botton, left, right, UIManager.getColor("TitledBorder.titleColor"));
	}
	public ParcialBorder(boolean top,boolean bottom,boolean left,boolean right,Color color) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		this.color = color;
	}
	
	public ParcialBorder top(){
		this.top = true;
		return this;
	}

	public ParcialBorder bottom(){
		this.bottom = true;
		return this;
	}
	
	public ParcialBorder left(){
		this.left = true;
		return this;
	}
	
	public ParcialBorder right(){
		this.right = true;
		return this;
	}
	
	public ParcialBorder color(Color color){
		this.color = color;
		return this;
	}
	
	public ParcialBorder stroke(int stroke) {
		this.stroke = new BasicStroke(stroke);
		return this;
	}
	
	public ParcialBorder insets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width,int height) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(color);
		g2d.setStroke(stroke);
		
		if(bottom)g2d.drawLine(x, y+c.getHeight()-1, x+c.getWidth(), y+c.getHeight()-1);
		if(top)g2d.drawLine(x, y+1, x+c.getWidth(), y+1);
		if(left)g2d.drawLine(x+1, y, x+1, y+c.getHeight());
		if(right)g2d.drawLine(x-1+c.getWidth(), y, x-1+c.getWidth(), y+c.getHeight());
		
		g2d.dispose();
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setTop(boolean top) {
		this.top = top;
	}
	
	public void setBotton(boolean botton) {
		this.bottom = botton;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}

	public void setStroke(int stroke) {
		this.stroke = new BasicStroke(stroke);
	}

	public void setInsets(Insets insets) {
		this.insets = insets;
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
		return insets;
	}
	
	@Override
	public boolean isBorderOpaque() {
		return false;
	}

}
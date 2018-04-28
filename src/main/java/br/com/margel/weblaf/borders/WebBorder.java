package br.com.margel.weblaf.borders;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WebBorder extends EmptyBorder{
	
	private Color color = Color.decode("#ced4da");
	private Color focusColor = Color.decode("#80bdff");
	private int arcWidth = 10;
	private int arcHeight = 10;
	private boolean paintFocus;
	private Color fixedColor;

	public WebBorder(Insets borderInsets) {
		super(borderInsets);
	}
	
	public WebBorder(int top, int left, int bottom, int right) {
		this(new Insets(top, left, bottom, right));
	}
	
	public WebBorder() {
		this(10, 12, 10, 12);
	}
	
	public WebBorder(int insetsAll) {
		this(insetsAll,insetsAll,insetsAll,insetsAll);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(paintFocus && c.isFocusOwner()){
			g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.setColor(focusColor);
		} else {
			g2d.setColor(color);
		}
		g2d.drawRoundRect(x, y, width-1, height-1, arcWidth, arcHeight);
		g2d.dispose();
	}

	public WebBorder arcSize(int arcSize) {
		this.arcWidth = arcSize;
		this.arcHeight = arcSize;
		return this;
	}
	
	public WebBorder color(Color color) {
		this.color = color;
		return this;
	}
	
	public WebBorder focusColor(Color focusColor) {
		this.focusColor = focusColor;
		return this;
	}
	
	public WebBorder paintFocus(boolean paintFocus) {
		this.paintFocus = paintFocus;
		return this;
	}
	
	public WebBorder insets(int top, int left, int bottom, int right) {
		this.left = left; 
		this.right = right; 
		this.top = top; 
		this.bottom = bottom;
		return this;
	}
	
	public int getArcWidth() {
		return arcWidth;
	}

	public void setArcWidth(int arcWidth) {
		this.arcWidth = arcWidth;
	}

	public int getArcHeight() {
		return arcHeight;
	}

	public void setArcHeight(int arcHeight) {
		this.arcHeight = arcHeight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = fixedColor!=null?fixedColor:color;
	}

	public Color getFocusColor() {
		return focusColor;
	}

	public void setFocusColor(Color focusColor) {
		this.focusColor = focusColor;
	}

	public boolean isPaintFocus() {
		return paintFocus;
	}

	public void setPaintFocus(boolean paintFocus) {
		this.paintFocus = paintFocus;
	}

	public Color getFixedColor() {
		return fixedColor;
	}

	public void setFixedColor(Color fixedColor) {
		this.fixedColor = fixedColor;
		this.color = fixedColor;
	}
	
}

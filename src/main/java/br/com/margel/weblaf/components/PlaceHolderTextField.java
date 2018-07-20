package br.com.margel.weblaf.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlaceHolderTextField extends JTextField {

	private String placeHolderText = "";
	private Color placeHolderForeground = Color.LIGHT_GRAY;

	public PlaceHolderTextField(String placeHolderText) {
		this.placeHolderText = placeHolderText;
	}

	public Color getPlaceHolderForeground() {
		return placeHolderForeground;
	}

	public void setPlaceHolderForeground(Color placeHolderForeground) {
		this.placeHolderForeground = placeHolderForeground;
	}
	
	public String getPlaceHolderText() {
		return placeHolderText;
	}
	
	public void setPlaceHolderText(String placeHolderText) {
		this.placeHolderText = placeHolderText;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(!getText().isEmpty() || placeHolderText.trim().isEmpty()) {
			return;
		}
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Font font = getFont();
		Rectangle2D rFont = getFontMetrics(font).getStringBounds(placeHolderText, g2d);
		
		Insets bi = getBorder().getBorderInsets(this);
		
		int x = bi.left;
		int y = (int) ((getHeight()/2)+(rFont.getHeight()/2));
		
		g2d.setFont(font);
		g2d.setColor(placeHolderForeground);
		g2d.drawString(placeHolderText, x, y);
		
		g2d.dispose();
	}
	
}
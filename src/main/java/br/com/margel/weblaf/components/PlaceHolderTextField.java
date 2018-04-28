package br.com.margel.weblaf.components;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlaceHolderTextField extends JTextField {

	private String placeHolder;
	private boolean showingPlaceHolder;
	private Color placeHolderForeground = Color.GRAY;
	private Color customForeground = Color.GRAY;

	public PlaceHolderTextField(final String placeHolder) {
		super(placeHolder);
		this.placeHolder = placeHolder;
		customForeground = getForeground();
		super.setForeground(placeHolderForeground);
		showingPlaceHolder = true;
		addFocusListener(new PlaceHolderFocusListener());
		addKeyListener(new PlaceHolderKeyListener());
		addMouseListener(new PlaceHolderMouseListener());
	}
	
	private class PlaceHolderKeyListener extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent e) {
			String trueText = PlaceHolderTextField.super.getText();
			if(showingPlaceHolder && !trueText.equals(placeHolder)) {
				PlaceHolderTextField.super.setForeground(customForeground);
				String newText = trueText.replace(placeHolder, "");
				int caret = getCaretPosition();
				PlaceHolderTextField.super.setText(newText);
				if(newText.equals(trueText)){
					int l = newText.length();
					setCaretPosition(caret>l?l:caret);
				}
				showingPlaceHolder = false;
			}else if(!showingPlaceHolder && trueText.isEmpty()){
				showPlaceHolder();
			}
		}
	}

	private class PlaceHolderFocusListener extends FocusAdapter{
		
		@Override
		public void focusLost(FocusEvent e) {
			if(PlaceHolderTextField.this.getText().isEmpty()) {
				showPlaceHolder();
			}
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			if(showingPlaceHolder){
				setCaretPosition(0);
			}
		}
	}
	
	private class PlaceHolderMouseListener extends MouseAdapter{
		@Override
		public void mouseReleased(MouseEvent e) {
			if(showingPlaceHolder){
				setCaretPosition(0);
			}
		}
	}
	
	@Override
	public void setSelectionStart(int selectionStart) {
		if(showingPlaceHolder){
			return;
		}
		super.setSelectionStart(selectionStart);
	}
	
	@Override
	public void setSelectionEnd(int selectionEnd) {
		if(showingPlaceHolder){
			return;
		}
		super.setSelectionEnd(selectionEnd);
	}

	@Override
	public void select(int selectionStart, int selectionEnd) {
		if(showingPlaceHolder){
			return;
		}
		super.select(selectionStart, selectionEnd);
	}
	
	@Override
	public void selectAll() {
		if(showingPlaceHolder){
			return;
		}
		super.selectAll();
	}
	
	@Override
	public void setForeground(Color fg) {
		this.customForeground = fg;
		if(showingPlaceHolder){
			super.setForeground(placeHolderForeground);
		}else{
			super.setForeground(fg);
		}
	}
	
	private synchronized void showPlaceHolder() {
		if(showingPlaceHolder){
			return;
		}
		PlaceHolderTextField.super.setText(placeHolder);
		showingPlaceHolder = true;
		PlaceHolderTextField.super.setForeground(placeHolderForeground);
		setCaretPosition(0);
	}


	@Override
	public void setText(String t) {
		if(t == null || t.isEmpty()){
			showPlaceHolder();
		}else{
			showingPlaceHolder = false;
			super.setText(t);
			super.setForeground(customForeground);
		}
	}

	@Override
	public String getText() {
		return showingPlaceHolder ? "" : super.getText();
	}

	public Color getPlaceHolderForeground() {
		return placeHolderForeground;
	}

	public void setPlaceHolderForeground(Color placeHolderForeground) {
		this.placeHolderForeground = placeHolderForeground;
		if(showingPlaceHolder){
			super.setForeground(placeHolderForeground);
		}
	}


	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
		if(showingPlaceHolder){
			super.setText(placeHolder);
		}
	}
}
package br.com.margel.weblaf.uis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalFileChooserUI;

import br.com.margel.weblaf.WebTheme;
import br.com.margel.weblaf.borders.WebBorder;
import br.com.margel.weblaf.components.WebChooserFileView;
import br.com.margel.weblaf.utils.ComponentUtils;

public class UIWebFileChooser extends MetalFileChooserUI {

	public static ComponentUI createUI(JComponent c) {
		return new UIWebFileChooser((JFileChooser) c);
	}

	public UIWebFileChooser(JFileChooser filechooser) {
		super(filechooser);
	}

	private JFileChooser chooser;

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		chooser = (JFileChooser) c;
		chooser.setPreferredSize(new Dimension(800, 500));
		configButtons();
		configLabel();
		configCombo();
		configTextField();
	}

	private void configButtons() {
		for (AbstractButton btn : ComponentUtils.searchAssignableFrom(AbstractButton.class, chooser, true)) {
			btn.setOpaque(false);
			btn.setForeground(Color.DARK_GRAY);
			if(btn.getIcon()!=null){
				btn.setBorder(new WebBorder(new Insets(4, 4, 4, 4)).arcSize(5));
			}
			if (btn.getBorder() instanceof WebBorder) {
				((WebBorder) btn.getBorder()).setFixedColor(WebTheme.TEXT_FIELD_BORDER_COLOR);
			}
			if (btn instanceof JToggleButton) {
				btn.setBackground(new Color(235, 240, 240));
			}
		}
	}
	private void configTextField() {
		for (JTextField tf : ComponentUtils.searchAssignableFrom(JTextField.class, chooser, true)) {
			tf.setBorder(new WebBorder(new Insets(6, 4, 6, 4)).arcSize(5));
		}
	}

	private void configLabel() {
		for (JLabel label : ComponentUtils.searchAssignableFrom(JLabel.class, chooser, true)) {
			label.setFont(label.getFont().deriveFont(Font.PLAIN, 11f));
		}
	}

	private void configCombo() {
		for (JComboBox<?> combo : ComponentUtils.searchAssignableFrom(JComboBox.class, chooser, true)) {
			if (combo.getModel() instanceof DirectoryComboBoxModel) {
				combo.setRenderer(new WebDirectoryComboBoxRenderer(combo));
			}
			combo.setFocusable(false);
		}
	}
	
	@Override
	protected JPanel createDetailsView(JFileChooser fc) {
		JPanel panel = super.createDetailsView(fc);
		for (JTable table : ComponentUtils.searchAssignableFrom(JTable.class, panel, true)) {
			table.getTableHeader().setFont(table.getFont().deriveFont(Font.PLAIN, 12f));
			table.setSelectionBackground(new Color(235, 240, 240));
			table.putClientProperty("Table.focusCellColor", table.getSelectionBackground());
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			updateTableColsSize(table);
			table.getModel().addTableModelListener(e->updateTableColsSize(table));
		}
		return panel;
	}
	
	@Override
	protected JPanel createList(JFileChooser fc) {
		JPanel panel = super.createList(fc);
		for (JList<?> list : ComponentUtils.searchAssignableFrom(JList.class, panel, true)) {
			list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			list.setFixedCellWidth(250);
			list.addPropertyChangeListener(evt -> {
				if(evt.getPropertyName().equals("layoutOrientation") && !evt.getNewValue().equals(JList.HORIZONTAL_WRAP)){
					list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				}
			});
		}
		return panel;
	}

	protected void updateTableColsSize(JTable table) {
		try {
			table.getColumnModel().getColumn(1).setMinWidth(100);
			table.getColumnModel().getColumn(1).setMaxWidth(100);
			table.getColumnModel().getColumn(2).setMinWidth(150);
			table.getColumnModel().getColumn(2).setMaxWidth(150);
			table.getColumnModel().getColumn(3).setMinWidth(150);
			table.getColumnModel().getColumn(3).setMaxWidth(150);
		} catch (Exception e) {/*nothing to do here*/}
	}

	@Override
	public FileView getFileView(JFileChooser fc) {
		return new WebChooserFileView(fc);
	}

	@Override
	protected FilterComboBoxRenderer createFilterComboBoxRenderer() {
		return new WebFilterComboBoxRenderer();
	}

	@SuppressWarnings("serial")
	private class WebDirectoryComboBoxRenderer extends DefaultListCellRenderer {
		private IndentIcon ii = new IndentIcon();
		private DirectoryComboBoxModel model;

		private WebDirectoryComboBoxRenderer(JComboBox<?> combo) {
			this.model = (DirectoryComboBoxModel) combo.getModel();
		}

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			if (value == null) {
				setText("");
				return this;
			}
			File directory = (File) value;
			setText(getFileChooser().getName(directory));
			Icon icon = getFileChooser().getFileSystemView().getSystemIcon(directory);
			if (icon == null) {
				icon = UIWebFileChooser.super.getFileView(chooser).getIcon(directory);
			}
			ii.icon = icon;
			ii.depth = model.getDepth(index);
			setIcon(ii);

			if (isSelected) {
				setBackground(WebTheme.LIST_SELECTION_BG);
				setForeground(WebTheme.LIST_SELECTION_FG);
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			setBorder(new EmptyBorder(2, 2, 2, 2));

			return this;
		}

	}

	@SuppressWarnings("serial")
	private class WebFilterComboBoxRenderer extends FilterComboBoxRenderer {
		@SuppressWarnings("rawtypes")
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (isSelected) {
				setBackground(WebTheme.LIST_SELECTION_BG);
				setForeground(WebTheme.LIST_SELECTION_FG);
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			setBorder(new EmptyBorder(2, 2, 2, 2));
			return comp;
		}
	}

	private class IndentIcon implements Icon {
		final static int space = 10;
		Icon icon = null;
		int depth = 0;

		public void paintIcon(Component c, Graphics g, int x, int y) {
			if (c.getComponentOrientation().isLeftToRight()) {
				icon.paintIcon(c, g, x + depth * space, y);
			} else {
				icon.paintIcon(c, g, x, y);
			}
		}

		public int getIconWidth() {
			return icon.getIconWidth() + depth * space;
		}

		public int getIconHeight() {
			return icon.getIconHeight();
		}

	}

	public static void installAncestorListener(JComponent c) {
		c.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent event) {
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
			}

			@Override
			public void ancestorAdded(AncestorEvent event) {
				Container parent = c.getParent();
				if (parent != null && parent.getClass().getName().contains("sun.swing.FilePane")) {
					c.setBorder(new WebBorder(new Insets(1, 4, 1, 4)));
					c.setFont(c.getFont().deriveFont(11f));
				}
			}
		});
	}

}
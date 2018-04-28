package br.com.margel.weblaf.components;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileView;

import br.com.margel.weblaf.utils.IconUtils;

public class WebChooserFileView extends FileView{

	private JFileChooser chooser;
	private static final ImageIcon FILE_ICON = IconUtils.getImageIcon("file20x20.png");
	private static final ImageIcon DIR_ICON = IconUtils.getImageIcon("close_folder_yellow20x20.png");

	public WebChooserFileView(JFileChooser chooser) {
		super();
		this.chooser = chooser;
	}

	@Override
	public String getName(File file) {
		String fileName = null;
        if(file != null) {
            fileName = chooser.getFileSystemView().getSystemDisplayName(file);
        }
        return fileName;
	}
	
	@Override
	public String getDescription(File f) {
        return f.getAbsolutePath();
    }
	
	@Override
	public String getTypeDescription(File f) {
        String type = chooser.getFileSystemView().getSystemTypeDescription(f);
        if (type == null) {
            type = f.getAbsolutePath();
        }
        return type;
    }
	
	@Override
	public Icon getIcon(File file) {
		Icon icon = chooser.getFileSystemView().getSystemIcon(file);
        if(icon == null){
        	icon = file.isDirectory()? DIR_ICON : FILE_ICON;
        }
        return icon;
	}

}
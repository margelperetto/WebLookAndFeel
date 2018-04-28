package br.com.margel.weblaf.utils;

import java.awt.Window;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileChooserUtil {
	
	public static String[] IMAGE_EXTENSIONS = {".jpg",".png",".gif",".bmp",".tiff",".tif",".ico",".jpeg"};

	public static File openImageDialog(Window owner){
		File file = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Selecione uma imagem");
		chooser.setFileFilter(new ExtensionFileFilter(IMAGE_EXTENSIONS));
		if(chooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		return file;
	}

	public static String choose(Window owner, String title, String initialFile, int mode) {
		return choose(owner, title, initialFile, mode, null);
	}
	
	/**
	 * Abre um diálogo para escolha de arquivo/pasta
	 * @param title - Título do diálogo
	 * @param initialFile - Local onde o FileChooser irá abrir
	 * @param mode - Modo de escolha, podendo ser {@link JFileChooser#DIRECTORIES_ONLY}, {@link JFileChooser#FILES_ONLY}
	 * ou {@link JFileChooser#FILES_AND_DIRECTORIES})
	 * @param extensoes Array de extensoes permitidas
	 * @return - String com o local escolhido (nulo caso o usuário desista ou não escolha nenhum arquivo)
	 */
	public static String choose(Window owner, String title, String initialFile, int mode, String[] extensoes) {

		JFileChooser fileChooser = new JFileChooser(initialFile);
		fileChooser.setDialogTitle(title);
		fileChooser.setFileSelectionMode(mode);
		if(extensoes!=null && extensoes.length>0){
			fileChooser.setFileFilter(new ExtensionFileFilter(extensoes));
		}

		if (fileChooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile() != null)
			return fileChooser.getSelectedFile().getAbsolutePath();

		return null;
	}

	public static File openFileDialog(Window owner, String... extensions){
		File file = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Selecione um arquivo");
		if(extensions!=null && extensions.length>0){
			chooser.setFileFilter(new ExtensionFileFilter(extensions));
		}
		if(chooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
		}
		return file;
	}

	public static File saveImageDialog(Window owner, String fileName){
		return saveFileDialog(owner, fileName, IMAGE_EXTENSIONS);
	}

	public static File saveFileDialog(Window owner, String fileName, String[] extensions){
		File file = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Salvar arquivo");
		chooser.setSelectedFile(new File(fileName));
		if(extensions!=null && extensions.length>0){
			chooser.setFileFilter(new ExtensionFileFilter(extensions));
		}
		if(chooser.showSaveDialog(owner) == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
		}
		return file;
	}

	public static String openDirectoryDialog(Window owner){
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Selecione uma pasta");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		if(chooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION){
			return chooser.getSelectedFile().getPath();
		}
		return null;
	}

	public static class ExtensionFileFilter extends FileFilter {
		private String[] extensions; 

		public ExtensionFileFilter(String... extensions) {
			this.extensions = extensions;
		}
		@Override
		public String getDescription() {
			String str = "Arquivos (";
			for(String s : extensions){
				str = str + " '"+s+"' ";
			}
			return str+")";
		}
		@Override
		public boolean accept(File f) {
			if(f.isDirectory()) return true;
			for(String s : extensions){
				if(f.getName().toLowerCase().endsWith(s.toLowerCase()))
					return true;
			}
			return false;
		}
	}

}
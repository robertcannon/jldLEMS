package org.lemsml.jld.viz;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SwingDialogs {

	
	static SwingDialogs instance;
	
	JFileChooser fileChooser;
	
	
	public static SwingDialogs getInstance() {
		if (instance == null) {
			instance = new SwingDialogs();
		}
		return instance;
	}
	
	
	
	
	public File getFileToRead() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
		}
		
		int retval = fileChooser.showDialog(null, "Import");
		File ret = null;
		if (retval == JFileChooser.APPROVE_OPTION) {
			ret=  fileChooser.getSelectedFile();
		}
		return ret;
	}




	public File getFileToWrite() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
		}
		
		int retval = fileChooser.showDialog(null, "Save");
		File ret = null;
		if (retval == JFileChooser.APPROVE_OPTION) {
			ret=  fileChooser.getSelectedFile();
		}
		return ret;
	}




	public void showMessage(String str) {
		 	JOptionPane.showMessageDialog(null, str);
		
	}
	
	
}

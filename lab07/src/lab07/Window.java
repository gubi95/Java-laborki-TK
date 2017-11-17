package lab07;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window extends JFrame {
	private JTextArea _objJTextAreaInput = null;
	private JTextArea _objJTextAreaOutput = null;
	private JComboBox<String> _objJComboBoxFiles = null;
	private JButton _objJButtonRun = null;
	
	public Window() {
		this.setup();
	}	
	
	private void setup() {
		this.setSize(600, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("JavaScript");		
		
		this.setLayout(new GridLayout(3, 2, 10, 10));
		
		_objJTextAreaInput = new JTextArea();
		_objJTextAreaOutput = new JTextArea();
		
		ArrayList<String> listFileNames = FileManager.getAllFileNamesInDirectory(System.getProperty("user.dir") + "\\js_scripts");
		_objJComboBoxFiles = new JComboBox<>(listFileNames.toArray(new String[listFileNames.size()]));
		
		_objJButtonRun = new JButton("Run");
		_objJButtonRun.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strJSFile = (String) _objJComboBoxFiles.getSelectedItem();
				String strInput = _objJTextAreaInput.getText();
				String strOutput = JSManager.callStringReturnFunction(System.getProperty("user.dir") + "\\js_scripts\\" + strJSFile, strJSFile.replaceAll(".js", ""), strInput);
				_objJTextAreaOutput.setText(strOutput);
				FileManager.writeToFile(System.getProperty("user.dir") + "\\scripts_results\\" + strJSFile.replaceAll(".js", ".txt"), strOutput);
			}
		});
		
		this.add(new JLabel("Input:"));
		this.add(_objJTextAreaInput);		
		this.add(new JLabel("Output:"));
		this.add(_objJTextAreaOutput);
		this.add(_objJComboBoxFiles);
		this.add(_objJButtonRun);
		
		this.show();		
	}
}

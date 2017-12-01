package lab09;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveFileConfirmModal extends JFrame {
	private JTextField _objJTextFieldFileName = null;
	private JButton _objJButtonOK = null;
	private JButton _objJButtonCancel = null;
	private IOnAcceptFileToSave _objIOnAcceptFileToSave;
	private JFrame _objJFrameParent = null;
	
	public SaveFileConfirmModal(JFrame objJFrameParent, IOnAcceptFileToSave objIOnAcceptFileToSave) {
		_objIOnAcceptFileToSave = objIOnAcceptFileToSave;
		_objJFrameParent = objJFrameParent;
		this.setupGUI();
	}

	private void setupGUI() {
		this.setTitle("Zapisywanie pliku");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(_objJFrameParent);		
		
		this._objJTextFieldFileName = new JTextField();
		
		JPanel objJPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		objJPanel.add(new JLabel("Nazwa pliku:"));
		objJPanel.add(this._objJTextFieldFileName);
		
		JPanel objJPanelSouth = new JPanel(new GridLayout(1, 2, 10, 10));
		SaveFileConfirmModal objSaveFileConfirmModal = this;
		_objJButtonOK = new JButton("Zapisz");
		_objJButtonOK.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(_objJTextFieldFileName.getText().trim().length() > 0) {
					if(_objIOnAcceptFileToSave != null) {					
						_objIOnAcceptFileToSave.doAction(_objJTextFieldFileName.getText());
					}	
				}			
				objSaveFileConfirmModal.dispose();
			}
		});
		
		
		_objJButtonCancel = new JButton("Anuluj");
		_objJButtonCancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				objSaveFileConfirmModal.dispose();								
			}
		});
		
		objJPanelSouth.add(this._objJButtonOK);
		objJPanelSouth.add(this._objJButtonCancel);
		
		objJPanel.add(objJPanelSouth);
		
		this.add(objJPanel);
		this.show();
	}
	
}

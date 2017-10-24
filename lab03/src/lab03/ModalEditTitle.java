package lab03;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModalEditTitle extends JFrame {	
	private static final long serialVersionUID = 1L;

	public static interface IOnAddAction {
		void doAction(String strTitle);
	}
	
	private IOnAddAction _objIOnAddAction = null;	
	private JTextField _objJTextFieldTitle = null;	
	
	public ModalEditTitle(IOnAddAction objIOnAddAction) {
		this._objIOnAddAction = objIOnAddAction;		
		this.setup();
	}
	
	private void setup() {
		this.setTitle("Edytuj tytu³");
		this.setLocationRelativeTo(null);		
		this.setSize(400, 125);		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		_objJTextFieldTitle = new JTextField();				
		
		JFrame objJFrame = this;
		
		JPanel objJPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		objJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JButton objJButtonAdd = new JButton("Zapisz");
		objJButtonAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(_objIOnAddAction != null) {
					_objIOnAddAction.doAction(_objJTextFieldTitle.getText());
					objJFrame.setVisible(false);
					objJFrame.dispose();					
				}
			}
		});
		
		JButton objJButtonCancel = new JButton("Anuluj");
		objJButtonCancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				objJFrame.setVisible(false);
				objJFrame.dispose();				
			}
		});
		
		objJPanel.add(new JLabel("Tytu³:"));
		objJPanel.add(_objJTextFieldTitle);			
		objJPanel.add(objJButtonAdd);
		objJPanel.add(objJButtonCancel);
		this.add(objJPanel);
		this.show();
	}
	
}

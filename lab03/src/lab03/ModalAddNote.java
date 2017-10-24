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

public class ModalAddNote extends JFrame {	
	private static final long serialVersionUID = 1L;

	public static interface IOnAddAction {
		void doAction(Calendar objCalendar, String strMessage);
	}
	
	private IOnAddAction _objIOnAddAction = null;	
	private JTextField _objJTextFieldDate = null;
	private JTextField _objJTextFieldNote = null;
	
	public ModalAddNote(IOnAddAction objIOnAddAction) {
		this._objIOnAddAction = objIOnAddAction;		
		this.setup();
	}
	
	private void setup() {
		this.setTitle("Dodaj notkê");
		this.setLocationRelativeTo(null);		
		this.setSize(400, 150);		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		_objJTextFieldDate = new JTextField();
		_objJTextFieldNote = new JTextField();		
		
		JFrame objJFrame = this;
		
		JPanel objJPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		objJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JButton objJButtonAdd = new JButton("Dodaj");
		objJButtonAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(_objIOnAddAction != null) {
					Calendar objCalendar = Calendar.getInstance();					
					try {
						objCalendar.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(_objJTextFieldDate.getText()));
						_objIOnAddAction.doAction(objCalendar, _objJTextFieldNote.getText());
						objJFrame.setVisible(false);
						objJFrame.dispose();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}				
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
		
		objJPanel.add(new JLabel("Data (dd.MM.yyyy):"));
		objJPanel.add(_objJTextFieldDate);		
		objJPanel.add(new JLabel("Notka:"));
		objJPanel.add(_objJTextFieldNote);
		objJPanel.add(objJButtonAdd);
		objJPanel.add(objJButtonCancel);
		this.add(objJPanel);
		this.show();
	}
	
}

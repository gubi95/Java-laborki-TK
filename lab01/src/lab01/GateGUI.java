package lab01;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import lab01.Gate.ICallbackAction;

public class GateGUI extends JFrame {
	private JButton _btnEnter = new JButton("Wejœcie");
	private JButton _btnExit = new JButton("Wyjœcie");
	private JButton _btnClose = new JButton("Wy³¹cz");
	private Gate _objGate = null;
	
	public GateGUI(Gate objGate) {
		this._objGate = objGate;
		
		_objGate.setShutdownCallbackAction(new ICallbackAction() {			
			@Override
			public void doAction() {
				_btnClose.setText(_objGate.getIsRunning() ? "Wy³¹cz" : "W³¹cz");				
				combineTitle();
			}
		});
	}
	
	public void setupGUI() {
		combineTitle();
		this.setSize(500, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		JPanel objJPanel = new JPanel();
		objJPanel.setLayout(new GridLayout(1, 3, 10, 10));
		
		setupListeners();
		objJPanel.add(_btnEnter);
		objJPanel.add(_btnExit);
		objJPanel.add(_btnClose);	
		
		Border objBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);		
		objJPanel.setBorder(objBorder);
		
		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e) {
		    	if(_objGate != null) {
		    		try {		    			
						_objGate.shutdown();						
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
		    	}
		    }
		});
		
		
		this.add(objJPanel);
		this.show();
	}
	
	private void combineTitle() {
		try {
			this.setTitle("Bramka " + _objGate.getID() + " - (" + 
					_objGate.getEntryCount() + " | " + 
					_objGate.getExitCount() + " | " + (_objGate.getIsRunning() ? "Otwarta" : "Zamkniêta") + ")");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setupListeners() {
		_btnEnter.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				_objGate.incremenetEntryCount();
				combineTitle();
			}
		});
		
		_btnExit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				_objGate.incremenetExitCount();
				combineTitle();
			}
		});
		
		_btnClose.addActionListener(new  ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(_objGate.getIsRunning()) {
					try {
						_objGate.shutdown();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else {
					_objGate.register();
				}				
								
				_btnClose.setText(_objGate.getIsRunning() ? "Wy³¹cz" : "W³¹cz");				
				combineTitle();
			}
		});
	}
}
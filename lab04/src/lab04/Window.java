package lab04;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	
	private JLabel _objJLabelCurrentlyUsedRAM = null;
	private JLabel _objJLabelCurrentlyUsedRAMPercentage = null;
	private TimerTask _objTimerTask = null;
	
	public Window() {
		this.setupGUI();
	}
	
	private void setupGUI() {
		this.setTitle("U¿ycie pamiêci");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		
		_objJLabelCurrentlyUsedRAM = new JLabel(formatBytes(JNI.getCurrentlyUsedRAM(), true));
		_objJLabelCurrentlyUsedRAMPercentage = new JLabel((int)(((double)JNI.getCurrentlyUsedRAM() / (double) JNI.getTotalRAM()) * 100) + "%");
		
		this.setLayout(new GridLayout(3, 2));		
		this.add(new JLabel("Ca³kowita pamiêæ RAM:"));
		this.add(new JLabel(formatBytes(JNI.getTotalRAM(), true)));		
		this.add(new JLabel("Pamiêæ RAM w u¿yciu:"));
		this.add(_objJLabelCurrentlyUsedRAM);
		this.add(new JLabel("U¿ycie procentowe:"));
		this.add(_objJLabelCurrentlyUsedRAMPercentage);		
		
		_objTimerTask = new TimerTask() {			
			@Override
			public void run() {
				System.out.println("A");
				_objJLabelCurrentlyUsedRAM.setText(formatBytes(JNI.getCurrentlyUsedRAM(), true));		
				_objJLabelCurrentlyUsedRAMPercentage.setText((int)(((double)JNI.getCurrentlyUsedRAM() / (double) JNI.getTotalRAM()) * 100) + "%");
			}
		};
		
		new Timer().scheduleAtFixedRate(_objTimerTask, 0, 1000);
		
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					_objTimerTask.cancel();
				}
				catch (Exception exc) { }				
			}
			
			@Override
			public void windowOpened(WindowEvent e) { }			
			@Override
			public void windowIconified(WindowEvent e) { }			
			@Override
			public void windowDeiconified(WindowEvent e) { }			
			@Override
			public void windowDeactivated(WindowEvent e) { }			
			@Override
			public void windowClosed(WindowEvent e) { }			
			@Override
			public void windowActivated(WindowEvent e) { }
		});
		
		this.show();
	}
	
	public static String formatBytes(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
}

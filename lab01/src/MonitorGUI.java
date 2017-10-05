import java.awt.GridLayout;

import javax.swing.JFrame;

public class MonitorGUI extends JFrame {		
	public void setupGUI() {
		this.setTitle("Monitor");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		this.setLayout(new GridLayout(1, 1));
		this.show();
	}		
	
	public static void main(String[] args) {
		MonitorGUI objMonitor = new MonitorGUI();
		objMonitor.setupGUI();
	}
	
	
}

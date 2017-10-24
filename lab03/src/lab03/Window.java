package lab03;

import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Window extends JFrame {	
	public void setupGUI() {		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("Kalendarz - super æwiczenie!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		NotesPanel objNotesPanel = new NotesPanel();
		objNotesPanel.setTitle("Mój kalendarz - OMG");	
		
		objNotesPanel.addNote(Calendar.getInstance(), "Note 1");
		objNotesPanel.addNote(Calendar.getInstance(), "Note 2");
		objNotesPanel.addNote(Calendar.getInstance(), "Note 3");
		
		add(objNotesPanel);
		this.show();
		
	}
}
package lab03;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class NotesPanel extends JPanel implements Serializable {	
	private static final long serialVersionUID = 1L;
	private NotesTable _objNotesTable = null;
	private JLabel _objJLabelTitle = null;	
	
	public NotesPanel() {
		this._objNotesTable = new NotesTable();
		
		this.setLayout(new BorderLayout());
		
		JPanel objJPanelNorth = new JPanel(new GridLayout(2, 1));
		
		_objJLabelTitle = new JLabel("Kalendarz", SwingConstants.CENTER);		
		objJPanelNorth.add(_objJLabelTitle);
		
		JButton objJButtonLabelEdit = new JButton("Edytuj tytu³");
		objJButtonLabelEdit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModalEditTitle(new ModalEditTitle.IOnAddAction() {					
					@Override
					public void doAction(String strTitle) {
						_objJLabelTitle.setText(strTitle);
					}
				});
			}
		});		
		objJPanelNorth.add(objJButtonLabelEdit);
		objJPanelNorth.setMaximumSize(new Dimension(100, 30));
		this.add(objJPanelNorth,BorderLayout.NORTH);		
		this.add(new JScrollPane(_objNotesTable), BorderLayout.CENTER);
		
		JPanel objJPanelButtons = new JPanel(new GridLayout(1, 2));		
		objJPanelButtons.setMaximumSize(new Dimension(100,  50));
		objJPanelButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JButton objJButtonAddNote = new JButton("Dodaj");
		objJButtonAddNote.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModalAddNote(new ModalAddNote.IOnAddAction() {					
					@Override
					public void doAction(Calendar objCalendar, String strMessage) {						
						_objNotesTable.addNote(new Note(objCalendar, strMessage));
					}
				});
			}
		});
		
		JButton objJButtonDelete = new JButton("Usuñ");
		objJButtonDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				_objNotesTable.removeSelectedRows();				
			}
		});
		
		objJPanelButtons.add(objJButtonAddNote);
		objJPanelButtons.add(objJButtonDelete);		
		this.add(objJPanelButtons, BorderLayout.SOUTH);
	}

	public void addNote(Calendar objCalendar, String strMessage) {
		_objNotesTable.addNote(new Note(objCalendar, strMessage));
	}
	
	public void setTitle(String strTitle) {
		this._objJLabelTitle.setText(strTitle);
	}
	
	public String getTitle() {
		return this._objJLabelTitle.getText();
	}	
}

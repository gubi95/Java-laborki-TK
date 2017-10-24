package lab03;

import javax.swing.JTable;

public class NotesTable extends JTable {	
	private static final long serialVersionUID = 1L;
	private NoteManager _objNoteManager = null;
	
	public NotesTable() {
		_objNoteManager = new NoteManager();		
		this.setModel(new NotesTableAdapter(_objNoteManager.getNotes()));
	}	
	
	public void addNote(Note objNote) {
		_objNoteManager.addNote(objNote);
		this.refreshTable();
	}
	
	public void removeSelectedRows() {
		int[] arrIndexes = this.getSelectedRows();
		
		if(arrIndexes == null) {
			return;
		}
		
		for(int i = 0; i < arrIndexes.length; i++) {
			_objNoteManager.getNotes().remove(arrIndexes[i] - i);
		}
		
		this.refreshTable();
	}
	
	private void refreshTable() {
		this.setModel(new NotesTableAdapter(_objNoteManager.getNotes()));
		this.revalidate();
		this.repaint();
	}
}

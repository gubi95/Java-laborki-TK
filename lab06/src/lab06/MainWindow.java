package lab06;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

import db_data.RoomFactory;
import db_data.UserFactory;
import db_data.VisitFactory;
import db_data.VisitingHourFactory;

public class MainWindow extends JFrame {
	
	public MainWindow() {
		this.setupGUI();
	}
	
	private void setupGUI() {		
		this.setSize(600,  100);
		this.setLayout(new GridLayout(1, 4));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Przychodnia");
		
		JButton btnUsers = new JButton("U¿ytkownicy");
		JButton btnRooms = new JButton("Pokoje");
		JButton btnVisitingHours = new JButton("Godziny przyjêæ");
		JButton btnVisits = new JButton("Wizyty");
		
		btnUsers.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModalDataTable(new UsersDataTableAdapter(new UserFactory().getAll()), "U¿ytkownicy");				
			}
		});
		
		btnRooms.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) { 
				new ModalDataTable(new RoomDataTableAdapter(new RoomFactory().getAll()), "Pokoje");
			}
		});		
		
		btnVisitingHours.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) { 
				new ModalDataTable(new VisitingHoursDataTableAdapter(new VisitingHourFactory().getAll()), "Godziny przyjêæ");
			}
		});
		
		btnVisits.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) { 
				new ModalDataTable(new VisitDataTableAdapter(new VisitFactory().getAll()), "Wizyty");
			}
		});	
		
		this.add(btnUsers);
		this.add(btnRooms);
		this.add(btnVisitingHours);
		this.add(btnVisits);
		
		this.show();
	}
	
}

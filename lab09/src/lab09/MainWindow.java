package lab09;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.myprojects.xsd.testscheme.AddressXML;
import net.myprojects.xsd.testscheme.CardXML;

public class MainWindow extends JFrame {
	private JComboBox<FileComboBoxItem> _objJComboBoxAddress = null;
	private JComboBox<FileComboBoxItem> _objJComboBoxCard = null;
	private JComboBox<FileComboBoxItem> _objJComboBoxStylesheet = null;	
	private JTextArea _objJTextAreaGreetings = null;
	private JTextField _objJTextFieldFirstname = null;
	private JTextField _objJTextFieldLastname = null;
	private JTextField _objJTextFieldAddressLine1 = null;
	private JTextField _objJTextFieldAddressLine2 = null;
	private JButton _objJButtonSaveAddress = null;
	private JButton _objJButtonSaveCard = null;
	private JButton _objJButtonGenerateCard = null;

	public MainWindow() {
		this.setupGUI();
	}
	
	private void setupGUI() {		
		this.setTitle("JAXB");
		this.setSize(600, 350);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		this.setLocationRelativeTo(null);
		
		_objJComboBoxAddress = new JComboBox<>();
		_objJComboBoxCard = new JComboBox<>();
		_objJComboBoxStylesheet = new JComboBox<>();
		_objJTextAreaGreetings = new JTextArea();
		_objJTextFieldFirstname = new JTextField();
		_objJTextFieldLastname = new JTextField();
		_objJTextFieldAddressLine1 = new JTextField();
		_objJTextFieldAddressLine2 = new JTextField();
		_objJButtonSaveAddress = new JButton("Zapisz adres");
		_objJButtonSaveCard = new JButton("Zapisz ¿yczenia");
		_objJButtonGenerateCard = new JButton("Generuj kartkê");
		
		this.bindFileComboBox(true, this._objJComboBoxAddress, System.getProperty("user.dir") + "\\xml_addresses");
		this.bindFileComboBox(true, this._objJComboBoxCard, System.getProperty("user.dir") + "\\xml_cards");
		this.bindFileComboBox(false, this._objJComboBoxStylesheet, System.getProperty("user.dir") + "\\css_cards");
		
		this.setAddressComboBox(_objJComboBoxAddress);
		this.setCardComboBox(_objJComboBoxCard);
		this.setSaveAddressButton(_objJButtonSaveAddress);
		this.setSaveCardButton(_objJButtonSaveCard);
		this.setGenerateCardButton(_objJButtonGenerateCard);
		
		JPanel objJPanelLeft = new JPanel(new GridLayout(10, 2, 10, 10));
		
		objJPanelLeft.add(new JLabel("Adres:"));
		objJPanelLeft.add(this._objJComboBoxAddress);		
		objJPanelLeft.add(new JLabel("Kartka:"));
		objJPanelLeft.add(this._objJComboBoxCard);		
		objJPanelLeft.add(new JLabel("Style:"));
		objJPanelLeft.add(this._objJComboBoxStylesheet);		
		objJPanelLeft.add(new JLabel("Imiê:"));
		objJPanelLeft.add(this._objJTextFieldFirstname);
		objJPanelLeft.add(new JLabel("Nazwisko:"));
		objJPanelLeft.add(this._objJTextFieldLastname);
		objJPanelLeft.add(new JLabel("Adres linia 1:"));
		objJPanelLeft.add(this._objJTextFieldAddressLine1);
		objJPanelLeft.add(new JLabel("Adres linia 2:"));
		objJPanelLeft.add(this._objJTextFieldAddressLine2);		
		objJPanelLeft.add(new JLabel("¯yczenia:"));
		objJPanelLeft.add(this._objJTextAreaGreetings);
		objJPanelLeft.add(_objJButtonSaveAddress);
		objJPanelLeft.add(_objJButtonSaveCard);
		objJPanelLeft.add(_objJButtonGenerateCard);
		
		this.add(objJPanelLeft);
		this.show();
	}
	
	private void bindFileComboBox(boolean bAddNewItem, JComboBox<FileComboBoxItem> objJComboBox, String strCatalogPath) {
		ArrayList<String> listPaths = this.getFilesInCatalog(strCatalogPath);
				
		objJComboBox.removeAllItems();
		
		if(bAddNewItem) {
			objJComboBox.addItem(new FileComboBoxItem(true, "Nowy"));
		}
		
		for(String strFilePath : listPaths) {
			objJComboBox.addItem(new FileComboBoxItem(strFilePath));
		}
	}
	
	private ArrayList<String> getFilesInCatalog(String strCatalogPath) {
		ArrayList<String> listPaths = new ArrayList<>();
		File objFolder = new File(strCatalogPath);
		File[] arrFiles = objFolder.listFiles();

		for (File objFile : arrFiles) {
			if (objFile.isFile()) {
				listPaths.add(objFile.getAbsolutePath());
			}
		}
		
		return listPaths;
	}

	private void setAddressComboBox(JComboBox<FileComboBoxItem> objJComboBox) {
		objJComboBox.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileComboBoxItem objFileComboBoxItem = (FileComboBoxItem) objJComboBox.getSelectedItem();				
				if(objFileComboBoxItem.getIsCustom()) {
					_objJTextFieldFirstname.setText("");
					_objJTextFieldLastname.setText("");
					_objJTextFieldAddressLine1.setText("");
					_objJTextFieldAddressLine2.setText("");
				}				
				else {
					AddressXML objAddressXML = XMLManager.deserialize(objFileComboBoxItem.getFilePath(), AddressXML.class);
					if(objAddressXML != null) {
						_objJTextFieldFirstname.setText(objAddressXML.getFirstname());
						_objJTextFieldLastname.setText(objAddressXML.getLastname());
						_objJTextFieldAddressLine1.setText(objAddressXML.getLine1());
						_objJTextFieldAddressLine2.setText(objAddressXML.getLine2());
					}
				}
			}
		});
	}

	private void setCardComboBox(JComboBox<FileComboBoxItem> objJComboBox) {
		objJComboBox.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileComboBoxItem objFileComboBoxItem = (FileComboBoxItem) objJComboBox.getSelectedItem();				
				if(objFileComboBoxItem.getIsCustom()) {
					_objJTextAreaGreetings.setText("");
				}				
				else {
					CardXML objCardXML = XMLManager.deserialize(objFileComboBoxItem.getFilePath(), CardXML.class);
					if(objCardXML != null) {
						_objJTextAreaGreetings.setText(objCardXML.getGreetings());
					}
				}
			}
		});
	}

	private void setSaveAddressButton(JButton objJButton) {
		JFrame objJFrame = this;
		objJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strFirstName = _objJTextFieldFirstname.getText();
				String strLastName = _objJTextFieldLastname.getText();
				String strLine1 = _objJTextFieldAddressLine1.getText();
				String strLine2 = _objJTextFieldAddressLine2.getText();
				AddressXML objAddressXML = new AddressXML();
				objAddressXML.setFirstname(strFirstName);
				objAddressXML.setLastname(strLastName);
				objAddressXML.setLine1(strLine1);
				objAddressXML.setLine2(strLine2);
				
				FileComboBoxItem objFileComboBoxItem = (FileComboBoxItem) _objJComboBoxAddress.getSelectedItem();
				
				if(objFileComboBoxItem.getIsCustom()) {		
					new SaveFileConfirmModal(objJFrame, new IOnAcceptFileToSave() {			
						@Override
						public void doAction(String strFilename) {
							XMLManager.serialize(System.getProperty("user.dir") + "\\xml_addresses\\" + strFilename + ".xml", objAddressXML);							
							_objJComboBoxAddress.addItem(new FileComboBoxItem(System.getProperty("user.dir") + "\\xml_addresses\\" + strFilename + ".xml"));
							_objJComboBoxAddress.setSelectedIndex(_objJComboBoxAddress.getItemCount() - 1);
						}
					});		
				}
				else {
					XMLManager.serialize(objFileComboBoxItem.getFilePath(), objAddressXML);
				}
				
			}
		});				
	}

	private void setSaveCardButton(JButton objJButton) {
		JFrame objJFrame = this;
		objJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strGreetings = _objJTextAreaGreetings.getText();
				CardXML objCardXML = new CardXML();
				objCardXML.setGreetings(strGreetings);
				
				FileComboBoxItem objFileComboBoxItem = (FileComboBoxItem) _objJComboBoxCard.getSelectedItem();
				
				if(objFileComboBoxItem.getIsCustom()) {		
					new SaveFileConfirmModal(objJFrame, new IOnAcceptFileToSave() {			
						@Override
						public void doAction(String strFilename) {
							XMLManager.serialize(System.getProperty("user.dir") + "\\xml_cards\\" + strFilename + ".xml", objCardXML);							
							_objJComboBoxCard.addItem(new FileComboBoxItem(System.getProperty("user.dir") + "\\xml_cards\\" + strFilename + ".xml"));
							_objJComboBoxCard.setSelectedIndex(_objJComboBoxCard.getItemCount() - 1);
						}
					});		
				}
				else {
					XMLManager.serialize(objFileComboBoxItem.getFilePath(), objCardXML);
				}
				
			}
		});				
	}

	private void setGenerateCardButton(JButton objJButton) {
		JFrame objJFrame = this;
		objJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileComboBoxItem objFileComboBoxItemStyles = (FileComboBoxItem) _objJComboBoxStylesheet.getSelectedItem();
				String strStyles = FileManager.readTextFile(objFileComboBoxItemStyles.getFilePath());
				
				FileComboBoxItem objFileComboBoxItemAddress = (FileComboBoxItem) _objJComboBoxAddress.getSelectedItem();
				FileComboBoxItem objFileComboBoxItemgreetings = (FileComboBoxItem) _objJComboBoxCard.getSelectedItem();
				
				AddressXML objAddressXML = XMLManager.deserialize(objFileComboBoxItemAddress.getFilePath(), AddressXML.class);				
				CardXML objCardXML = XMLManager.deserialize(objFileComboBoxItemgreetings.getFilePath(), CardXML.class);
				
				StringBuilder objStringBuilder = new StringBuilder();				
				objStringBuilder.append("<html>");
				objStringBuilder.append("<head>");
				objStringBuilder.append("</head>");
				objStringBuilder.append("<body>");				
				objStringBuilder.append("<style>");
				objStringBuilder.append(strStyles);
				objStringBuilder.append("</style>");				
				objStringBuilder.append("<div class=\"main\">");
				objStringBuilder.append("¯yczenia:<br/>");
				objStringBuilder.append(objCardXML.getGreetings() + "<br/>");				
				objStringBuilder.append("<div class=\"address\">");
				objStringBuilder.append("Do:<br/>");
				objStringBuilder.append(objAddressXML.getFirstname() + "&nbsp;" + objAddressXML.getLastname() + "<br/>");
				objStringBuilder.append(objAddressXML.getLine1() + "<br/>");
				objStringBuilder.append(objAddressXML.getLine2() + "<br/>");
				objStringBuilder.append("</div>");				
				objStringBuilder.append("</div>");
				objStringBuilder.append("</body>");
				objStringBuilder.append("</html>");
				
				new SaveFileConfirmModal(objJFrame, new IOnAcceptFileToSave() {			
					@Override
					public void doAction(String strFilename) {
						FileManager.writeToFile(System.getProperty("user.dir") + "\\html_cards\\" + strFilename + ".html", objStringBuilder.toString());
					}
				});					
			}
		});
	}
}

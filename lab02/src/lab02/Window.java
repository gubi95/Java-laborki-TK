package lab02;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Window extends JFrame {
	private JList<ClassListItem> _objJListClasses = null;
	private JPanel _objJPanelReflectionControls = null;	
	private CustomClassLoader _objCustomClassLoader = null;	
	
	public Window() {
		_objCustomClassLoader = new CustomClassLoader(CustomClassLoader.class.getClassLoader());
	}	
	
	public void setupGUI() {
		this.setTitle("Class loader");	
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);		
		
		this.setLayout(new GridLayout(1, 2));
		this.loadClassList();			
		
		_objJPanelReflectionControls = new JPanel();		
		this.add(_objJPanelReflectionControls);
			
		this.show();
	}	
	
	private class ClassListItem {
		private String _strName;
		private boolean _bIsLoaded;
		
		public String getName() {
			return this._strName.replace(".class", "");
		}
		
		public boolean isLoaded() {
			return this._bIsLoaded;
		}
		
		public ClassListItem(String strName, boolean bIsLoaded) {
			this._strName = strName;
			this._bIsLoaded = bIsLoaded;
		}
		
		@Override
		public String toString() {
			return this._strName + " (" + (this._bIsLoaded ? "za³adowana" : "nie za³adowana") + ")";
		}
	}
	
	private void loadClassList() {
		DefaultListModel<ClassListItem> listModel = new DefaultListModel<>();
		ArrayList<File> listFile = new ArrayList(CustomClassLoader.getAllClassesToLoad());
		
		for(File objFile : listFile) {
			listModel.addElement(new ClassListItem(objFile.getName(), false));
		}		
		
		if(this._objJListClasses == null) {
			_objJListClasses = new JList(listModel);				
			_objJListClasses.addListSelectionListener(new ListSelectionListener() {				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(!e.getValueIsAdjusting()) {
						loadGUIClass(_objCustomClassLoader.getClass(_objJListClasses.getSelectedValue().getName()), _objJListClasses.getSelectedValue().getName());
					}
					
				}
			});
			
			this.add(new JScrollPane(_objJListClasses));
		}
	}
	
	private void loadGUIClass(Class objClass, String strName) {
		this.remove(_objJPanelReflectionControls);		
		_objJPanelReflectionControls = new JPanel(new GridLayout(objClass != null ? objClass.getDeclaredMethods().length + 1 : 1, 1, 10, 10));
		
		
		if(objClass != null) {
			for(Method method : objClass.getDeclaredMethods()) {
				String strMethod = method.getName() + "(";
				
				List<Class> listArgsTypes = new ArrayList();
				
				for(int i = 0; i < method.getParameters().length; i++) {					
					strMethod += method.getParameters()[i].getType().getTypeName() + ",";					
					listArgsTypes.add(method.getParameters()[i].getType());
				}
				
				if(strMethod.endsWith(",")) {
					strMethod = strMethod.substring(0, strMethod.length() - 1);
				}
				strMethod += ")";
				
				JPanel objJPanel = new JPanel(new GridLayout(1,3));				
				objJPanel.add(new JLabel(strMethod));
				
				JTextField objJTextField = new JTextField();
				objJPanel.add(objJTextField);
				
				JButton objJButton = new JButton("Run");
				
				objJPanel.add(objJButton);
				
				boolean bIsStatic = Modifier.isStatic(method.getModifiers());
				
				objJButton.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String[] arrArgs = objJTextField.getText().split(",");
						List<Object> listArgs = new ArrayList();
						for(int i = 0; i < arrArgs.length; i++) {
							Class objArgType = listArgsTypes.get(i);
							
							if(objArgType.equals(int.class)) {
								listArgs.add(Integer.parseInt(arrArgs[i]));
							}
							else if(objArgType.equals(String.class)) {
								listArgs.add(arrArgs[i]);
							}
							else if(objArgType.equals(int[].class)) {
								String[] arrIntigers = arrArgs[i].replace("{", "").replace("}", "").split("\\|");								
								int[] arrInts = new int[arrIntigers.length];
								for(int j = 0; j < arrIntigers.length; j++) {
									arrInts[j] = Integer.parseInt(arrIntigers[j]);
								}
								listArgs.add(arrInts);
							}	
							else if(objArgType.equals(String[].class)) {
								String[] arrStrings = arrArgs[i].replace("{", "").replace("}", "").split("\\|");									
								listArgs.add(arrStrings);
							}	
						}						
						
						try {							
							method.invoke(bIsStatic ? null : objClass.newInstance(), listArgs.toArray());							
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							e.printStackTrace();
						}
					}
				});
				
				_objJPanelReflectionControls.add(objJPanel);	
				
				
				System.out.println(method.getName());
			
			}
		}
		
		JButton objJButton = new JButton(objClass != null ? "Wy³aduj" : "Za³aduj");
		objJButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(objClass != null) {
					_objCustomClassLoader.unloadClass(strName);
				}
				else {
					try {
						loadGUIClass(_objCustomClassLoader.loadClass(strName), strName);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		_objJPanelReflectionControls.add(objJButton);		
		this.add(_objJPanelReflectionControls);
		this.revalidate();
	}
	
	private void loadClass(ClassListItem objClassListItem) {		
		
		//_objJPanelReflectionControls.setLayout(new GridLayout(2, 1));
		//_objJPanelReflectionControls.add(new JPanel());
		
				
	}
}



















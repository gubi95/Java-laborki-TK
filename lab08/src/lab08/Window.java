package lab08;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceAlreadyExistsException;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.Notification;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.swing.JTextArea;

public class Window extends javax.swing.JFrame implements WindowMBean, NotificationBroadcaster {
    private String _strMap;
    private Map<String, String> _dictWordsToReplacement = new HashMap<>();
    private NotificationBroadcasterSupport _objNotificationBroadcasterSupport = new NotificationBroadcasterSupport();
    private long _lNotificationSequence = 0;    
    private JTextArea _objJTextArea = null;

    public Window() {
    	setMap("slowo1:slowo2");
        setupGUI();        
    }    
    
    private void setupGUI() {
    	this.setTitle("JMX");
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setSize(400, 400);
    	this.setLocationRelativeTo(null);

    	_objJTextArea = new JTextArea();
    	_objJTextArea.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) { }			
			@Override
			public void keyReleased(KeyEvent e) {
				sendNotification();
			}			
			@Override
			public void keyPressed(KeyEvent e) { }            
        });        

        this.add(_objJTextArea);        
        this.show();
    }    
    
    public static void main(String args[]) {        
        try {   
        	ManagementFactory.getPlatformMBeanServer().registerMBean((WindowMBean) new Window(), new ObjectName("MyJMX:name=Editor"));            
        } 
        catch (Exception ex) {
        	System.out.println("Failed to register bean and create window...");
        }
    }    

    @Override
    public void setMap(String wordsMap) {
        this._strMap = wordsMap;
        String[] arrWordsToReplacement = this._strMap.split(";");
        for (int i = 0; i < arrWordsToReplacement.length; i++) {            
            _dictWordsToReplacement.put(arrWordsToReplacement[i].split(":")[0], arrWordsToReplacement[i].split(":")[1]);
        }
    }

    @Override
    public String getMap() {
        return this._strMap;
    }
    
    public boolean check() {
        for (String strWord : _dictWordsToReplacement.keySet()) {
            if (_objJTextArea.getText().trim().toLowerCase().contains(strWord.toLowerCase().trim())) {
                return true;
            }
        }        
        
        return false;
    }

    @Override
    public void replace() {
        String strTextToEdit = _objJTextArea.getText();
        for (String strWord : _dictWordsToReplacement.keySet()) {
            strTextToEdit = strTextToEdit.replaceAll(strWord, (String) _dictWordsToReplacement.get(strWord));
        }
        _objJTextArea.setText(strTextToEdit);        
    }
    
    private void sendNotification() {
        if(check()) {        	         
            _objNotificationBroadcasterSupport.sendNotification(
               new Notification("lab08", this, ++_lNotificationSequence, "Word to replace has been found!")
            );
        }
    }

    @Override
    public void addNotificationListener(NotificationListener listener, NotificationFilter filter, Object handback) throws IllegalArgumentException {
        _objNotificationBroadcasterSupport.addNotificationListener(listener, filter, handback);
    }

    @Override
    public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {
        _objNotificationBroadcasterSupport.removeNotificationListener(listener);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[] {
            new MBeanNotificationInfo(new String[] { "lab08" }, Notification.class.getName(), "Notification")
        };
    }
}
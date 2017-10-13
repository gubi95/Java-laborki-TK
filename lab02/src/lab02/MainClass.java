package lab02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainClass {
	public static void main(String[] args) {	
		new Window().setupGUI();
//		ClassLoader objClassLoader = CustomClassLoader.class.getClassLoader();		
//		try {
//			CustomClassLoader loader = new CustomClassLoader(objClassLoader);
//			Class cls = loader.loadClass("ClassAToLoad.class");
//			System.out.println(loader.isClassLoaded("ClassAToLoad"));
//			
//			
////			for(Method method : cls.getDeclaredMethods()) {
////				System.out.println(method.getName());
////				if(method.getName() == "printStatic") {
////					Object[] methodArgs = new Object[] {"Ala ma kota", 100, new int[] {200,300}};	
////					System.out.println(method.getReturnType());
////					for(int i = 0; i < method.getParameters().length; i++) {
////						System.out.println(method.getParameters()[i].getType().getTypeName());
////					}
////					
////					try {
////						method.invoke(null, methodArgs);
////					} catch (IllegalAccessException e) {
////						e.printStackTrace();
////					} catch (IllegalArgumentException e) {
////						e.printStackTrace();
////					} catch (InvocationTargetException e) {
////						e.printStackTrace();
////					}
////				}
////				//System.out.println(method.getName());
////			}
//			
//			//cls.getMethods()[0].invoke(null, null);
//		} catch (ClassNotFoundException e) {			
//			e.printStackTrace();
//		}		
	}
}

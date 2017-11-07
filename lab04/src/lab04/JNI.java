package lab04;

public class JNI {
	static {
		System.out.println(System.getProperty("user.dir") + "\\JNI.dll");
		System.load(System.getProperty("user.dir") + "\\JNI.dll");
	}
	
	public static native long getTotalRAM();
	public static native long getCurrentlyUsedRAM();	
}

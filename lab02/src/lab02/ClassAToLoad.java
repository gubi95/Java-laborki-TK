package lab02;

public class ClassAToLoad {
	public ClassAToLoad() {
		
	}
	
	public static void printStatic(String string, int number, int[] numbers) {
		System.out.println("Klasa A");
		System.out.println("Napis: " + string);
		System.out.println("Numer: " + number);
		System.out.println("Tablica:");
		
		for(int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
	}
	
	public void print(String string, int number, int[] numbers) {
		System.out.println("Klasa A");
		System.out.println("Napis: " + string);
		System.out.println("Numer: " + number);
		System.out.println("Tablica:");
		
		for(int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
	}
}
package lab02;

public class ClassBToLoad {
	public ClassAToLoad classA;
	public ClassBToLoad() {
		
	}
	
	public static void printStatic(String string, int number, int[] numbers) {
		System.out.println("Klasa B");
		System.out.println("Napis: " + string);
		System.out.println("Numer: " + number);
		System.out.println("Tablica:");
		
		for(int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
	}
	
	public void print(String string, int number, int[] numbers) {
		System.out.println("Klasa B");
		System.out.println("Napis: " + string);
		System.out.println("Numer: " + number);
		System.out.println("Tablica:");
		
		for(int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
	}
}
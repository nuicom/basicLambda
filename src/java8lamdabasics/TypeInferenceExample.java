package java8lamdabasics;

public class TypeInferenceExample {
	public static void main(String[] args) {
//		StringLengthLabmbda myLambda = s -> s.length();
//		System.out.println(myLambda.getLength("Hellow Lamda"));
		
//		printLambda(new StringLengthLabmbda() {
//			
//			@Override
//			public int getLength(String s) {
//				return s.length();
//			}
//		});
		
		printLambda(s -> s.length());
		
	}
	
	public static void printLambda(StringLengthLabmbda l) {
		System.out.println(l.getLength("Hello Lambda"));
	}

	interface StringLengthLabmbda {
		int getLength(String s);
	}
}

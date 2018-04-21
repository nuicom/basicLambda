package java8lamdabasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Unit1ExcersicseSolutionJava8 {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("Charles", "Dickens", 60), new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51), new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39));

		// Step 1 : Sort list by last name;
		Collections.sort(people, (Person p1, Person p2) -> p1.getLastName().compareTo(p2.getLastName()));

		// Step 2 : Create a method that prints all element in the list
		System.out.println("Printing all persons");
		printConditionally(people, p -> true);
		
		// step 3 : Create a method that print all people that have last name beginning
		// with c
		System.out.println("Printing all persons with lastname begening whit C");
		printConditionally(people, p -> p.getLastName().startsWith("C"));

		System.out.println("Printing all persons with firstname begening whit C");
		printConditionally(people, p -> p.getFirstName().startsWith("C"));
	}

	private static void printConditionally(List<Person> people, Condition condition) {
		for (Person p : people) {
			if (condition.test(p)) {
				System.out.println(p);
			}
		}
	}
	public interface Condition {
		boolean test(Person p);
	}
}


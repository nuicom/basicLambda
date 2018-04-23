package java8lamdabasics.unit3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java8lamdabasics.Person;

public class ConstructorReferencesExample {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(new Person("Charles", "Dickens", 60), new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51), new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39));
		
		List<String> stringList = Arrays.asList("Charles","Lewis","Thomas","Carlyle","Charlotte","Matthew");
		
		List<Person> persons = stringList.stream().map(Person::new)
		.collect(Collectors.toList());
		
		List<String> personName = persons.stream().map(Person::getFirstName)
				.collect(Collectors.toList());
	}

}

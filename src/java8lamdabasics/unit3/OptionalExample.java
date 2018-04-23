package java8lamdabasics.unit3;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalExample {

	public static void main(String[] args) {

		Optional<String> data = Stream.of("Charles", "Lewis", "Thomas").filter(p -> p.startsWith("D")).findFirst();
		System.out.println(data.isPresent() ? data.get() : "no value");
//		System.out.println(data.orElse("no value"));
//		System.out.println(data.orElseThrow(NoSuchElementException::new));
	}

}

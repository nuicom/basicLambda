package java8lamdabasics.unit3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java8lamdabasics.Person;

public class StreamsExample1 {

	public static void main(String args[]) {
		List<Person> people = Arrays.asList(new Person("Charles", "Dickens", 60), new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51), new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39));

		people.stream().filter(p -> p.getLastName().startsWith("C")).forEach(p -> System.out.println(p.getFirstName()));

		long count = people.parallelStream().filter(p -> p.getLastName().startsWith("D")).count();

		System.out.println(count);

		List<Integer> nums = Stream
				.iterate(1, n -> n + 1)
				.limit(10)
				.collect(Collectors.toList());
		
		List<Integer> nums1 = Stream
				.generate(() -> new Random().nextInt(10))
				.limit(10)
				.collect(Collectors.toList());
		
		List<Integer> nums2 = IntStream.of(1,2,3,4)
				.boxed()
				.collect(Collectors.toList());
		
		List<Integer> nums3 = IntStream.of(1,2,3,4)
				.mapToObj(Integer::valueOf)
				.collect(Collectors.toList());
		
		List<Integer> nameLength = people
				.stream()
				.map(p -> p.getFirstName().length())
				.collect(Collectors.toList());
		
		String[][] data = new String[][] {{"Charles","Lewis"},{"Thomas"}};
		
		List<String> datas = Arrays.stream(data)
				.flatMap(p -> Arrays.stream(p))
				.collect(Collectors.toList());
		
		List<String> datas1 = Arrays.stream(data)
				.flatMap(p -> Arrays.stream(p))
				.filter(p -> p.startsWith("C"))
				.collect(Collectors.toList());
		
		String name = Arrays.stream(data)
				.flatMap(p -> Arrays.stream(p))
				.reduce((firstname , lastname) -> firstname + lastname)
				.get();
		
		Double average = IntStream.of(1,2,3,4,5).average().getAsDouble();
		System.out.println(average);
		
		Integer sum = IntStream.of(1,2,3,4,5).sum();
		System.out.println(sum);
		
		
	}
}

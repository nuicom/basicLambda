package java8lamdabasics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class OptionalTest {

	@Test
	void whenCreatesEmptyOptional_thenCorrect() {
		Optional<String> empty = Optional.empty();
		assertFalse(empty.isPresent());
	}

	@Test
	public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
		String name = "Hello";
		Optional.of(name);
	}

	@Test
	public void givenNonNull_whenCreatesOptional_thenCorrect() {
		String name = "Hello";
		Optional<String> opt = Optional.of(name);
		assertEquals("Optional[Hello]", opt.toString());
	}

	@Test
	public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
		String name = null;
		Optional<String> opt = Optional.of(name);
	}

	@Test
	public void givenNonNull_whenCreatesNullable_thenCorrect() {
		String name = "Hello";
		Optional<String> opt = Optional.ofNullable(name);
		assertEquals("Optional[Hello]", opt.toString());
	}

	@Test
	public void givenNull_whenCreatesNullable_thenCorrect() {
		String name = null;
		Optional<String> opt = Optional.ofNullable(name);
		assertEquals("Optional.empty", opt.toString());
	}

	@Test
	public void givenOptional_whenIsPresentWorks_thenCorrect() {
		Optional<String> opt = Optional.of("Hello");
		assertTrue(opt.isPresent());

		opt = Optional.ofNullable(null);
		assertFalse(opt.isPresent());
	}

	@Test
	public void givenOptional_whenIfPresentWorks_thenCorrect() {
		Optional<String> opt = Optional.of("Hello");
		opt.ifPresent(name -> System.out.println(name.length()));
	}

	@Test
	public void whenOrElseWorks_thenCorrect() {
		String nullName = null;
		String name = Optional.ofNullable(nullName).orElse("Hello");
		assertEquals("Hello", name);
	}

	@Test
	public void whenOrElseGetWork_thenCorrect() {
		String nullName = null;
		String name = Optional.ofNullable(nullName).orElseGet(() -> "Hello");
		assertEquals("Hello", name);
	}

	public String getMyDefault() {
		System.out.println("Getting Default Value");
		return "Default Value";
	}

	// when the wrapped value is not present, then both orElse and orElseGet APIs
	// work exactly the same way.
	/*
	 * Using orElseGet: Getting default value... Using orElse: Getting default
	 * value...
	 */
	@Test
	public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
		String text = null;

		System.out.println("Using orElseGet:");
		String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
		assertEquals("Default Value", defaultText);

		System.out.println("Using orElse:");
		defaultText = Optional.ofNullable(text).orElse(getMyDefault());
		assertEquals("Default Value", defaultText);
	}

	// using orElse, whether the wrapped value is present or not, the default object
	// is created.
	// So in this case, we have just created one redundant object that is never
	// used.
	/*
	 * Using orElseGet: Using orElse: Getting Default Value
	 */
	@Test
	public void whenOrElseGetAndOrElseDiffer_thenCorreect() {
		String text = "Text present";

		System.out.println("Using orElseGet:");
		String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
		assertEquals("Text present", defaultText);

		System.out.println("Using orElse:");
		defaultText = Optional.ofNullable(text).orElse(getMyDefault());
		assertEquals("Text present", defaultText);
	}

	@Test
	public void whenOrElseThrowWorks_thenCorrect() {
		String nullName = null;
		String name = Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
	}

	@Test
	public void givenOptional_whenGetsValue_thenCorrect() {
		Optional<String> opt = Optional.of("Hello");
		String name = opt.get();
		assertEquals("Hello", name);
	}

	@Test
	public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
		Optional<String> opt = Optional.ofNullable(null);
		String name = opt.get();
	}

	@Test
	public void whenOptionalFilterWorks_thenCorrect() {
		Integer year = 2016;
		Optional<Integer> yearOptional = Optional.of(year);
		boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
		assertTrue(is2016);

		boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
		assertFalse(is2017);
	}

	public boolean priceIsInRange(Modem modem) {
		return Optional.ofNullable(modem).map(Modem::getPrice).filter(p -> p >= 10).filter(p -> p <= 15).isPresent();
	}

	@Test
	public void whenFiltersWithOptional_thenCorrect() {
		assertTrue(priceIsInRange(new Modem(10.0)));
		assertFalse(priceIsInRange(new Modem(9.9)));
		assertFalse(priceIsInRange(new Modem(null)));
		assertFalse(priceIsInRange(new Modem(15.5)));
		assertFalse(priceIsInRange(null));
	}

	@Test
	public void givenOptional_whenMapWorks_thenCorrect() {
		List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
		Optional<List<String>> listOptional = Optional.of(companyNames);

		int size = listOptional.map(List::size).orElse(0);
		assertEquals(6, size);
	}

	@Test
	public void givenOptional_whenMapWorks_thenCorrect2() {
		String name = "Hello";
		Optional<String> nameOptional = Optional.of(name);

		int len = nameOptional.map(String::length).orElse(0);
		assertEquals(5, len);
	}

	@Test
	public void givenOptional_whenMapWorksWithFilter_thenCorrect() {
		String password = " password ";
		Optional<String> passOpt = Optional.of(password);
		boolean correctPassword = passOpt.filter(pass -> pass.equals("password")).isPresent();
		assertFalse(correctPassword);

		correctPassword = passOpt.map(String::trim).filter(pass -> pass.equals("password")).isPresent();
		assertTrue(correctPassword);
	}

}

class Modem {
	private Double price;

	public Modem(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}

class Person1 {
	private String name;
	private int age;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}	

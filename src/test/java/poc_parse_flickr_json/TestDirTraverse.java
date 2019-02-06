package poc_parse_flickr_json;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestDirTraverse {
	@Test
	public void test1() throws Throwable {
		Path root = Paths.get(this.getClass().getClassLoader().getResource("./").toURI());
		Files.walk(root)
				.map(p -> p.toFile())
				.filter(f -> f.isFile())
				.filter(f -> f.getName().matches("^.+\\.zip$"))
				.forEach(f -> System.out.println(f.getName()));
	}

	@Test
	public void test2() {
		Arrays.asList("A", "B", "C").forEach(System.out::println);
	}

	@Test
	public void test3() {
		List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
		integerList.stream() // streamの取得
				.filter(i -> i % 2 == 0) // 中間操作
				.forEach(i -> System.out.println(i)); // 終端操作
	}

	@Test
	public void test4() {
		List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
		integerList.stream() // streamの取得
				.map(i -> i * 2) // 中間操作
				.forEach(i -> System.out.println(i)); // 終端操作
	}

	@Test
	public void test5() throws Throwable {
		Path baseDir = Paths.get(System.getProperty("user.home"), "Music", "iTunes");
		String extension = "m4a";
		String extensionPattern = ("(?i)^.*\\." + Pattern.quote(extension) + "$");
		long count = Files.walk(baseDir)
				.map(p -> p.toFile())
				.filter(f -> f.isFile())
				.filter(f -> f.getName().matches(extensionPattern))
				.count();
		System.out.println(count);
	}
}

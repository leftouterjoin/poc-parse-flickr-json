package poc_parse_flickr_json;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Test;

public class TestLoadFile {
	@Test
	public void test1() throws Throwable {
		String actual = Files
				.lines(Paths.get(getClass().getClassLoader().getResource("photo_16270223832.json").toURI()),
						StandardCharsets.UTF_8)
				.collect(Collectors.joining(System.getProperty("line.separator")));
		assertNotNull("", actual);
		System.out.println(actual);
	}
}

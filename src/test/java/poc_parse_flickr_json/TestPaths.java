package poc_parse_flickr_json;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class TestPaths {
	@Test
	public void test1() {
		Path actual = Paths.get(".");
		assertThat(actual.toString(), is("."));
	}
}

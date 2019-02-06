package poc_parse_flickr_json;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import poc_parse_flickr_json.model.PhotoInfo;

public class TestModel {
	@Test
	public void test1() throws Throwable {
		String json = loadTextFromFile(
				this.getClass().getClassLoader().getResource("photo_16270223832.json").toURI());

		ObjectMapper mapper = new ObjectMapper();
		PhotoInfo info = mapper.readValue(json, PhotoInfo.class);

		System.out.println(info.geo.latitude);
	}

	private static String loadTextFromFile(URI uri) throws IOException {
		return Files
				.lines(Paths.get(uri),
						StandardCharsets.UTF_8)
				.collect(Collectors.joining(System.getProperty("line.separator")));
	}
}
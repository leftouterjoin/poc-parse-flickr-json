package poc_parse_flickr_json;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMapper {
	@Test
	public void test1() {
		try {
			String json = loadTextFromFile(
					this.getClass().getClassLoader().getResource("photo_16270223832.json").toURI());

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(json);

			JsonNode cur = root.get("date_taken");
			System.out.println(cur.asText());

			cur = root.get("geo").get("latitude");
			System.out.println(cur.asText());

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static String loadTextFromFile(URI uri) throws IOException {
		return Files
				.lines(Paths.get(uri),
						StandardCharsets.UTF_8)
				.collect(Collectors.joining(System.getProperty("line.separator")));
	}
}

package poc_parse_flickr_json;

import java.io.PrintWriter;

import org.junit.Test;

import com.opencsv.CSVWriter;

public class TestCSVWriter {
	@Test
	public void test1() throws Throwable {
		String test[]={"\"",","};
		try (CSVWriter writer = new CSVWriter(new PrintWriter(System.out))) {
			writer.writeNext(test);
			writer.flush();
		}
	}
}

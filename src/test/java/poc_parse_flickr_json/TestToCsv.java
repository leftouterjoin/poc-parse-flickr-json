package poc_parse_flickr_json;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.opencsv.CSVWriter;

import poc_parse_flickr_json.model.PhotoInfo;

public class TestToCsv {
	private int counter;

	@Test
	public void test1() throws Throwable {
		final ObjectMapper mapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		Path root = Paths.get("C:\\Users\\lefto\\Downloads\\flickr\\ok");
//		try (CSVWriter writer = new CSVWriter(new PrintWriter(System.out))) {
		try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream("C:\\tmp\\Flickr.csv")))) {
			writer.writeNext(new String [] {
					"info.id",
					"info.dateTaken",
					"info.dateImported",
					"info.original",
					"info.geo.latitude",
					"info.geo.longitude",
					"info.exif.dateTimeOriginal",
					"info.exif.createDate",
					"info.exif.gPSLatitude",
					"info.exif.gPSLongitude",
					"info.albums.get(0).title",
					"info.albums.get(1).title",
					"info.albums.get(2).title",
					"info.albums.get(3).title",
					});
			counter = 0;
			Files.walk(root)
					.map(p -> p.toFile())
					.filter(f -> f.isFile())
					.filter(f -> f.getName().matches("^72157677516885148.+\\.zip$"))
					.forEach(f -> {
						System.out.println(f);
						try (ZipFile zipFile = new ZipFile(f)) {
							zipFile.stream()
									.filter(z -> z.getName().matches("^photo_.+\\.json$"))
									.forEach(z -> {
										try (Scanner s = new Scanner(zipFile.getInputStream(z))
												.useDelimiter(System.getProperty("line.separator"))) {
											if (!s.hasNext())
												throw new RuntimeException("空のファイル");
											String json = s.next();
											try {
												counter++;
												PhotoInfo info = mapper.readValue(json, PhotoInfo.class);
												writer.writeNext(new String [] {
														info.id,
														info.dateTaken,
														info.dateImported,
														info.original,
														(info.geo == null)? "": info.geo.latitude,
														(info.geo == null)? "": info.geo.longitude,
														(info.exif == null)? "": info.exif.dateTimeOriginal,
														(info.exif == null)? "": info.exif.createDate,
														(info.exif == null)? "": info.exif.gPSLatitude,
														(info.exif == null)? "": info.exif.gPSLongitude,
														(info.albums.size() < 1)? "": info.albums.get(0).title,
														(info.albums.size() < 2)? "": info.albums.get(1).title,
														(info.albums.size() < 3)? "": info.albums.get(2).title,
														(info.albums.size() < 4)? "": info.albums.get(3).title,
														});
												writer.flush();
											} catch (MismatchedInputException e) {
												throw new RuntimeException(e);
											}
										} catch (IOException e) {
											throw new RuntimeException(e);
										}
									});
						} catch (ZipException e) {
							throw new RuntimeException(e);
						} catch (IOException e) {
						}
					});
			System.out.println("counter: " + counter);
		}
	}
}

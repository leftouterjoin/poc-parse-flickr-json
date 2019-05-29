package poc_parse_flickr_json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import poc_parse_flickr_json.model.PhotoInfo;

public class TestZipTraverse {
	private static final String ROOT_DIR = "C:\\Users\\lefto\\Downloads\\flickr\\ok\\";

	@Test
	public void test1() throws Throwable {
		//InputStream is = this.getClass().getClassLoader()
		//		.getResourceAsStream("72157677516885148_d560360dc7c4_part1.zip");
		InputStream is = Files.newInputStream(
				Paths.get(ROOT_DIR + "72157677516885148_d560360dc7c4_part1.zip"));
		ZipInputStream zis = new ZipInputStream(is, StandardCharsets.ISO_8859_1);
		ZipEntry ze = null;
		while (null != (ze = zis.getNextEntry())) {
			System.out.println(ze.getName());
		}
	}

	@Test
	public void test2() throws Throwable {
		// File f = new File(this.getClass().getClassLoader()
		// 		.getResource("72157677516885148_d560360dc7c4_part1.zip").toURI());
		File f = Paths.get(ROOT_DIR + "72157677516885148_d560360dc7c4_part1.zip").toFile();
		try (ZipFile zipFile = new ZipFile(f)) {
			zipFile.stream()
					.forEach(System.out::println);
		}
	}

	@Test
	public void test3() throws Throwable {
		// Path root = Paths.get(this.getClass().getClassLoader().getResource("./").toURI());
		Path root = Paths.get(ROOT_DIR);
		Files.walk(root)
				.map(p -> p.toFile())
				.filter(f -> f.isFile())
				.filter(f -> f.getName().matches("^.+\\.zip$"))
				.forEach(f -> {
					try (ZipFile zipFile = new ZipFile(f)) {
						zipFile.stream()
								.filter(z -> z.getName().matches("^photo_.+\\.json$"))
								.forEach(System.out::println);
					} catch (ZipException e) {
						throw new RuntimeException(e);
					} catch (IOException e) {
					}
				});
	}

	@Test
	public void test4() throws Throwable {
		final ObjectMapper mapper = new ObjectMapper();
		// Path root = Paths.get(this.getClass().getClassLoader().getResource("./").toURI());
		Path root = Paths.get(ROOT_DIR);
		Files.walk(root)
				.map(p -> p.toFile())
				.filter(f -> f.isFile())
				.filter(f -> f.getName().matches("^.+\\.zip$"))
				.forEach(f -> {
					try (ZipFile zipFile = new ZipFile(f)) {
						zipFile.stream()
								.filter(z -> z.getName().matches("^photo_.+\\.json$"))
								.forEach(z -> {
									System.out.println(z);
									try (Scanner s = new Scanner(zipFile.getInputStream(z))
											.useDelimiter(System.getProperty("line.separator"))) {
										if (!s.hasNext())
											throw new RuntimeException("空のファイル");
										String json = s.next();
										PhotoInfo info = mapper.readValue(json, PhotoInfo.class);
										// System.out.println(info.geo.latitude);
									} catch (IOException e) {
										throw new RuntimeException(e);
									}
								});
					} catch (ZipException e) {
						throw new RuntimeException(e);
					} catch (IOException e) {
					}
				});
	}

	int dateTaken = 0;
	int geo = 0;
	int exif = 0;

	@Test
	public void test5() throws Throwable {
		final ObjectMapper mapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		Path root = Paths.get("C:\\Users\\lefto\\Downloads\\flickr\\ok");
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
									System.out.println(z);
									try (Scanner s = new Scanner(zipFile.getInputStream(z))
											.useDelimiter(System.getProperty("line.separator"))) {
										if (!s.hasNext())
											throw new RuntimeException("空のファイル");
										String json = s.next();
										try {
											// Resource info = mapper.readValue(json, Resource.class);
											// System.out.println(info.photoInfo);
											PhotoInfo info = mapper.readValue(json, PhotoInfo.class);
											if (info.albums.size() > 0)
												info.albums.forEach(a -> {System.out.println(a.title);});
											if (info.dateTaken == null || info.dateTaken.isEmpty())
												System.err.println("'date_taken' not exists. " + ++dateTaken);
											if (info.geo == null || info.geo.latitude == null)
												System.err.println("'geo' not exists. " + ++geo);
											if (info.exif == null || info.exif.dateTimeOriginal == null)
												System.err.println("'exif' not exists. " + ++exif);
											// System.out.println(info.geo.latitude);
										} catch (MismatchedInputException e) {
											throw new RuntimeException(e);
										}
									} catch (IOException e) {
										throw new RuntimeException(e);
									}
								});
						System.err.println("'date_taken' not exists. " + dateTaken);
						System.err.println("'geo' not exists. " + geo);
						System.err.println("'exif' not exists. " + exif);
					} catch (ZipException e) {
						throw new RuntimeException(e);
					} catch (IOException e) {
					}
				});
	}
}

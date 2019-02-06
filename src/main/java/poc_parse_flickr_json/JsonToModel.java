package poc_parse_flickr_json;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

import poc_parse_flickr_json.TestModel;

public class JsonToModel {

	public static void main(String[] args) {
		try {
			String json = loadTextFromFile(
					TestModel.class.getClassLoader().getResource("photo_16270223832.json").toURI());
			JsonToModel jsonToJava = new JsonToModel(json, Paths.get("./src/main/java"), "poc_jackson.model",
					"PhotoInfo");
			jsonToJava.execute();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static String loadTextFromFile(URI uri) throws IOException {
		return Files
				.lines(Paths.get(uri),
						StandardCharsets.UTF_8)
				.collect(Collectors.joining(System.getProperty("line.separator")));
	}

	private String jsonString;
	private Path outputPath;
	private String packageName;
	private String rootClassName;

	public JsonToModel(String jsonString, Path outputPath, String packageName, String rootClassName) {
		this.jsonString = jsonString;
		this.outputPath = outputPath;
		this.packageName = packageName;
		this.rootClassName = rootClassName;
	}

	public void execute() throws IOException {

		JCodeModel codeModel = new JCodeModel();
		GenerationConfig generationConfig = new DefaultGenerationConfig() {
			@Override
			public SourceType getSourceType() {
				return SourceType.JSON;
			}

			@Override
			public boolean isIncludeHashcodeAndEquals() {
				return false;
			}

			@Override
			public boolean isIncludeToString() {
				return false;
			}

			@Override
			public boolean isIncludeAdditionalProperties() {
				return false;
			}

			@Override
			public boolean isIncludeDynamicAccessors() {
				return false;
			}

			@Override
			public boolean isIncludeSetters() {
				return false;
			}

			@Override
			public boolean isIncludeGetters() {
				return false;
			}
		};

		SchemaMapper mapper = new SchemaMapper(
				new RuleFactory(generationConfig, new Jackson2Annotator(generationConfig), new SchemaStore()),
				new SchemaGenerator());
		mapper.generate(codeModel, rootClassName, packageName, jsonString);

		codeModel.build(outputPath.toFile());
	}

}
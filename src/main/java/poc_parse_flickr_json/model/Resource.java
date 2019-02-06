package poc_parse_flickr_json.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Resource {
	@JsonDeserialize(using = PhotoInfoDeserializer.class)
	public PhotoInfo photoInfo;

	public static class PhotoInfoDeserializer extends JsonDeserializer<PhotoInfo> {
		private ObjectMapper objectMapper = new ObjectMapper();

		@Override
		public PhotoInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			TreeNode treeNode = p.getCodec().readTree(p);
			return treeNode.isObject() ? objectMapper.readValue(treeNode.toString(), PhotoInfo.class) : null;
		}
	}
}
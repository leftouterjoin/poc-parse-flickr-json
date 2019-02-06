
package poc_parse_flickr_json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tag",
    "user",
    "date_create"
})
public class Tag {

    @JsonProperty("tag")
    public String tag;
    @JsonProperty("user")
    public String user;
    @JsonProperty("date_create")
    public String dateCreate;

}

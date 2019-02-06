
package poc_parse_flickr_json.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonDeserialize(using = PhotoInfoDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "count_views",
    "count_faves",
    "count_comments",
    "date_taken",
    "count_tags",
    "count_notes",
    "date_imported",
    "photopage",
    "original",
    "license",
    "geo",
    "exif",
    "groups",
    "albums",
    "tags",
    "people",
    "notes",
    "privacy",
    "comment_permissions",
    "tagging_permissions",
    "safety",
    "comments"
})
public class PhotoInfo {

    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("count_views")
    public String countViews;
    @JsonProperty("count_faves")
    public String countFaves;
    @JsonProperty("count_comments")
    public String countComments;
    @JsonProperty("date_taken")
    public String dateTaken;
    @JsonProperty("count_tags")
    public String countTags;
    @JsonProperty("count_notes")
    public String countNotes;
    @JsonProperty("date_imported")
    public String dateImported;
    @JsonProperty("photopage")
    public String photopage;
    @JsonProperty("original")
    public String original;
    @JsonProperty("license")
    public Object license;
    @JsonProperty("geo")
    public Geo geo;
    @JsonProperty("exif")
    public Exif exif;
    @JsonProperty("groups")
    public List<Object> groups = new ArrayList<Object>();
    @JsonProperty("albums")
    public List<Album> albums = new ArrayList<Album>();
    @JsonProperty("tags")
    public List<Tag> tags = new ArrayList<Tag>();
    @JsonProperty("people")
    public List<Object> people = new ArrayList<Object>();
    @JsonProperty("notes")
    public List<Object> notes = new ArrayList<Object>();
    @JsonProperty("privacy")
    public String privacy;
    @JsonProperty("comment_permissions")
    public String commentPermissions;
    @JsonProperty("tagging_permissions")
    public String taggingPermissions;
    @JsonProperty("safety")
    public String safety;
    @JsonProperty("comments")
    public List<Object> comments = new ArrayList<Object>();

}

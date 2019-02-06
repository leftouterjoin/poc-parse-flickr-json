
package poc_parse_flickr_json.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Orientation",
    "XResolution",
    "YResolution",
    "Software",
    "ModifyDate",
    "YCbCrPositioning",
    "ExposureTime",
    "FNumber",
    "ExposureProgram",
    "ISO",
    "DateTimeOriginal",
    "CreateDate",
    "BrightnessValue",
    "ExposureCompensation",
    "MeteringMode",
    "Flash",
    "FocalLength",
    "SubjectArea",
    "SubSecTimeOriginal",
    "SubSecTimeDigitized",
    "ColorSpace",
    "SensingMethod",
    "ExposureMode",
    "WhiteBalance",
    "FocalLengthIn35mmFormat",
    "SceneCaptureType",
    "LensInfo",
    "LensMake",
    "LensModel",
    "RunTimeFlags",
    "RunTimeValue",
    "RunTimeEpoch",
    "RunTimeScale",
    "GPSLatitude",
    "GPSLongitude",
    "GPSAltitudeRef",
    "GPSAltitude",
    "GPSTimeStamp",
    "GPSSpeedRef",
    "GPSSpeed",
    "GPSImgDirectionRef",
    "GPSImgDirection",
    "GPSDestBearingRef",
    "GPSDestBearing",
    "GPSDateStamp",
    "Compression"
})
public class Exif {

    @JsonProperty("Orientation")
    public String orientation;
    @JsonProperty("XResolution")
    public String xResolution;
    @JsonProperty("YResolution")
    public String yResolution;
    @JsonProperty("Software")
    public String software;
    @JsonProperty("ModifyDate")
    public String modifyDate;
    @JsonProperty("YCbCrPositioning")
    public String yCbCrPositioning;
    @JsonProperty("ExposureTime")
    public String exposureTime;
    @JsonProperty("FNumber")
    public String fNumber;
    @JsonProperty("ExposureProgram")
    public String exposureProgram;
    @JsonProperty("ISO")
    public String iso;
    @JsonProperty("DateTimeOriginal")
    public String dateTimeOriginal;
    @JsonProperty("CreateDate")
    public String createDate;
    @JsonProperty("BrightnessValue")
    public String brightnessValue;
    @JsonProperty("ExposureCompensation")
    public String exposureCompensation;
    @JsonProperty("MeteringMode")
    public String meteringMode;
    @JsonProperty("Flash")
    public String flash;
    @JsonProperty("FocalLength")
    public String focalLength;
    @JsonProperty("SubjectArea")
    public String subjectArea;
    @JsonProperty("SubSecTimeOriginal")
    public String subSecTimeOriginal;
    @JsonProperty("SubSecTimeDigitized")
    public String subSecTimeDigitized;
    @JsonProperty("ColorSpace")
    public String colorSpace;
    @JsonProperty("SensingMethod")
    public String sensingMethod;
    @JsonProperty("ExposureMode")
    public String exposureMode;
    @JsonProperty("WhiteBalance")
    public String whiteBalance;
    @JsonProperty("FocalLengthIn35mmFormat")
    public String focalLengthIn35mmFormat;
    @JsonProperty("SceneCaptureType")
    public String sceneCaptureType;
    @JsonProperty("LensInfo")
    public String lensInfo;
    @JsonProperty("LensMake")
    public String lensMake;
    @JsonProperty("LensModel")
    public String lensModel;
    @JsonProperty("RunTimeFlags")
    public String runTimeFlags;
    @JsonProperty("RunTimeValue")
    public String runTimeValue;
    @JsonProperty("RunTimeEpoch")
    public String runTimeEpoch;
    @JsonProperty("RunTimeScale")
    public String runTimeScale;
    @JsonProperty("GPSLatitude")
    public String gPSLatitude;
    @JsonProperty("GPSLongitude")
    public String gPSLongitude;
    @JsonProperty("GPSAltitudeRef")
    public String gPSAltitudeRef;
    @JsonProperty("GPSAltitude")
    public String gPSAltitude;
    @JsonProperty("GPSTimeStamp")
    public String gPSTimeStamp;
    @JsonProperty("GPSSpeedRef")
    public String gPSSpeedRef;
    @JsonProperty("GPSSpeed")
    public String gPSSpeed;
    @JsonProperty("GPSImgDirectionRef")
    public String gPSImgDirectionRef;
    @JsonProperty("GPSImgDirection")
    public String gPSImgDirection;
    @JsonProperty("GPSDestBearingRef")
    public String gPSDestBearingRef;
    @JsonProperty("GPSDestBearing")
    public String gPSDestBearing;
    @JsonProperty("GPSDateStamp")
    public String gPSDateStamp;
    @JsonProperty("Compression")
    public String compression;

}

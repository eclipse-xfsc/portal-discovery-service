package eu.gaia_x.discovery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class DataDetails {
    private String id;
    private String name;
    @JsonProperty("img_preview_url")
    private String imgPreviewUrl;
    @JsonProperty("ppr_name")
    private String pprName;
    @JsonProperty("ppr_url")
    private String pprUrl;
    private String description;
    private String source;
    @JsonProperty("cloud_service")
    private String cloudService;
    private String location;
    @JsonProperty("location_flag")
    private String locationFlag;
    @JsonProperty("frequency_of_update")
    private String frequencyUpdate;
    @JsonProperty("last_updated")
    private String lastUpdated;
    private List<String> tags;
    private String category;

}

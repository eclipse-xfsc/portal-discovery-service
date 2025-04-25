package eu.gaia_x.discovery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class SampleRecord {
    private String id;
    private String description;
    @JsonProperty("run_url")
    private String runURL;
}

package eu.gaia_x.discovery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class PriceInfo {
    private String id;
    private String name;
    private String price;
}

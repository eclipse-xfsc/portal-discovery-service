package eu.gaia_x.discovery.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

@ToString
public enum ContactType {
  TECH_PHONE,
  TECH_EMAIL,
  SUPP_PHONE,
  SUPP_EMAIL;

  @JsonValue
  public String getTheValue() {
    return name().toLowerCase();
  }
}

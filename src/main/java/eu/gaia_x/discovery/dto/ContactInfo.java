package eu.gaia_x.discovery.dto;

import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@ToString
@Value(staticConstructor = "of")
public class ContactInfo {
  @NonNull
  ContactType type;

  @NonNull
  String value;

  public static ContactInfo from(Map<String, String> m) {
    return of(ContactType.valueOf(m.get("type").toUpperCase()), m.get("value"));
  }

  public Map<String, String> map() {
    final Map<String, String> m = new HashMap<>();
    m.put("type", type.getTheValue());
    m.put("value", value);

    return m;
  }
}

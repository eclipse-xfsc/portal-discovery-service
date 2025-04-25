package eu.gaia_x.discovery.dto.chips;

import lombok.*;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class ChipsEntry {
  @NonNull
  String label;

  @NonNull
  String term;
}

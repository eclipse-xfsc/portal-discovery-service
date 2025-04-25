package eu.gaia_x.discovery.dto.chips;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@ToString
@Getter
@Setter
@AllArgsConstructor
public class ChipsDTO {
  @NonNull
  List<ChipsEntry> results;
}

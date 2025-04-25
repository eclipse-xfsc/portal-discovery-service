package eu.gaia_x.discovery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.util.List;
import java.util.Map;

@ToString
@Value(staticConstructor = "of")
public class ScreenshotInfo {

    @NonNull
    String serviceId;
    @JsonProperty("preview_imgs")
    @NonNull
    PreviewImg[] previewImgs;

    public static ScreenshotInfo from(Map<String, ?> m) {
        String serviceId = (String) m.get("serviceId");
        PreviewImg[] imgs = ((List<Map<String, String>>) m.get("previewImgs")).stream()
                .map(mm -> new PreviewImg(mm.get("url")))
                .toArray(PreviewImg[]::new);
        return of(serviceId, imgs);
    }

    @Getter
    public static class PreviewImg {
        private final String url;

        public PreviewImg(String url) {
            this.url = url;
        }
    }
}

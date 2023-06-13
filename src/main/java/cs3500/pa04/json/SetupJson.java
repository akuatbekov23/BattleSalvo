package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ShipType;
import java.util.Map;

public record SetupJson
    (
        @JsonProperty("width") int width,
        @JsonProperty("height") int height,
        @JsonProperty("spec") Map<ShipType, Integer> spec
    )
{
}

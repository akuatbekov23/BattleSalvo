package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;
import java.util.List;

/**
 * represents the take shot json
 */
public record TakeShotJson(
    @JsonProperty("coordinates") List<CoordJson> coordinates
) {
}

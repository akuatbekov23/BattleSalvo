package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ShipType;
import java.util.Map;

/**
 * @param width  the width of the board
 * @param height the height of the board
 * @param spec the hash-map of ship type to amount of that ship-type
 */
public record SetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("spec") Map<ShipType, Integer> spec) {
}

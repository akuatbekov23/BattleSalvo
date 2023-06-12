package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;

/**
 * represents a json record for the setup
 */
public record SetupJson(
    @JsonProperty("coord") Coord coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction
) {
}

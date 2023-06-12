package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * represents a ship in a Json format
 */
public record ShipJson(
    @JsonProperty("coord") CoordJson coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction
) {
}

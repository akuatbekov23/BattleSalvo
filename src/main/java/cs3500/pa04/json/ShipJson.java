package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param coord the starting "origin" coord of the ship
 * @param length the length of the ship
 * @param direction the direction in which the ship is placed
 */
public record ShipJson(
    @JsonProperty("coord") CoordJson coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction
) {
}

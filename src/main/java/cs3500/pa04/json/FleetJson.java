package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @param fleet a fleet of ships represented in a Json format
 */
public record FleetJson(
    @JsonProperty("fleet") List<ShipJson> fleet
) {
}

package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;
import java.util.List;

/**
 * Represents the ReportDamage in the BattleSalvo.
 */
public record ReportDamageJson(
    @JsonProperty("coordinates") CoordinatesJson coordinates
) {
}
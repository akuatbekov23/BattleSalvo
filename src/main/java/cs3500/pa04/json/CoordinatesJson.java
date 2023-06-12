package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.Coord;
import java.util.List;

/**
 * @param coordinates represents a volley of shots
 */
public record CoordinatesJson (
    @JsonProperty("coordinates") List<CoordJson> coordinates
){
}

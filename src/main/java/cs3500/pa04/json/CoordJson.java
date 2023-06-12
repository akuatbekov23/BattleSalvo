package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param x the column index
 * @param y the row index
 */
public record CoordJson (
    @JsonProperty("x") int x,
    @JsonProperty("y") int y
)
{}

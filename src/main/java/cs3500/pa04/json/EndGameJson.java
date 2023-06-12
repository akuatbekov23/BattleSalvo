package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.GameResult;

/**
 * @param result the game's result TIE, WIN, or LOSE
 * @param reason the reason for the result
 */
public record EndGameJson (
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason
)
{}

package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param name the user's name
 * @param gameType the type of game to be played
 */
public record JoinJson(

    @JsonProperty("name") String name,
    @JsonProperty("gameType") String gameType
) {
}

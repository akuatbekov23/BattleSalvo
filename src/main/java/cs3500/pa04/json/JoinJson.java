package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents joining the BattleSalvo server
 */
public record JoinJson(

    @JsonProperty("name") String name,
    @JsonProperty("gameType") String gameType
) {
}

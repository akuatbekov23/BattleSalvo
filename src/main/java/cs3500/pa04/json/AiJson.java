package cs3500.pa04.json;

/**
 * Represents the AI in the Battle.
 */
public record AiJson(
    String name,
    String board,
    String shotsTaken,
    String successfulHits,
    String reportDamage
) {
}

package cs3500.pa04.json;

/**
 * Represents the Board in the BattleSalvo.
 */
public record BoardJson(
    ShipJson[] ships,
    CoordJson[] hits,
    CoordJson[] misses,
    AiJson ai
) {
}

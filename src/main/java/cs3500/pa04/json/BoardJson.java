package cs3500.pa04.json;

public record BoardJson(
    ShipJson[] ships,
    CoordJson[] hits,
    CoordJson[] misses,
    AiJson ai
) {
}

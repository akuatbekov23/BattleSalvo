package cs3500.pa04.json;

import cs3500.pa03.model.ShipType;

public record ShipJson(
    boolean sunk,
    ShipType type,
    CoordJson[] coords
) {}

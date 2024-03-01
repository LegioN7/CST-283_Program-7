package org.example.program7;

/**
 * The HikeDirection enum represents the eight cardinal directions for a hike.
 * Each direction has an opposite direction associated with it.
 */
public enum HikeDirection {
    /**
     * Represents the North direction.
     * The opposite is South.
     */
    N("S"),
    /**
     * Represents the Northeast direction.
     * The opposite is Southwest.
     */
    NE("SW"),
    /**
     * Represents the East direction.
     * The opposite is West.
     */
    E("W"),
    /**
     * Represents the Southeast direction.
     * The opposite is Northwest.
     */
    SE("NW"),
    /**
     * Represents the South direction.
     * The opposite is North.
     */
    S("N"),
    /**
     * Represents the Southwest direction.
     * The opposite is Northeast.
     */
    SW("NE"),
    /**
     * Represents the West direction.
     * The opposite is East.
     */
    W("E"),
    /**
     * Represents the Northwest direction.
     * The opposite is Southeast.
     */
    NW("SE");

    /**
     * The opposite direction.
     */
    private final String opposite;

    /**
     * Constructs a HikeDirection with the specified opposite direction.
     *
     * @param opposite the opposite direction
     */
    HikeDirection(String opposite) {
        this.opposite = opposite;
    }

    /**
     * Returns the opposite direction.
     *
     * @return the opposite direction
     */
    public String getOpposite() {
        return opposite;
    }
}

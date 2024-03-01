package org.example.program7;

/**
 * The HikeDistance class represents a segment of a hike.
 * Each segment has a distance and a direction associated with it.
 */
public class HikeDistance {

    /**
     * The distance of the hike segment.
     */
    private final String distance;

    /**
     * The direction of the hike segment.
     */
    private final HikeDirection direction;

    /**
     * Constructs a HikeDistance with the specified distance and direction.
     *
     * @param distance the distance of the hike segment
     * @param direction the direction of the hike segment
     */
    public HikeDistance(String distance, HikeDirection direction) {
        this.distance = distance;
        this.direction = direction;
    }

    /**
     * Returns the distance of the hike segment.
     *
     * @return the distance of the hike segment
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Returns the direction of the hike segment.
     *
     * @return the direction of the hike segment
     */
    public HikeDirection getDirection() {
        return direction;
    }

    /**
     * Returns a string representation of the hike segment.
     *
     * @return a string representation of the hike segment
     */
    @Override
    public String toString() {
        return String.format("%-15s | %s", distance, direction);
    }
}
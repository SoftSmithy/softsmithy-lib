/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.time;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Objects;
import org.softsmithy.lib.time.range.AdjacentTimeRangeComparisonStragegy;
import org.softsmithy.lib.time.range.InTimeRangeComparisonStragegy;
import org.softsmithy.lib.time.range.PartialOverlappingTimeRangeComparisonStragegy;

/**
 *
 * @author puce
 */
// TODO: generic Range? Generic unit of measure range?
public class TimeRange implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ZonedDateTime startDateTime;
    private final ZonedDateTime endDateTime;

    public TimeRange(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * @return the startDateTime
     */
    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * @return the endDateTime
     */
    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public boolean isInRange(TimeRange other, InTimeRangeComparisonStragegy stragegy) {
        return false; // TODO
    }

    public boolean isInRange(ChronoZonedDateTime<?> other, InTimeRangeComparisonStragegy stragegy) {
        return false; // TODO

    }

    public boolean isAdjacent(TimeRange other) {
        return isAdjacent(other, AdjacentTimeRangeComparisonStragegy.BEFORE_OR_AFTER);
    }

    public boolean isAdjacent(TimeRange other, AdjacentTimeRangeComparisonStragegy stragegy) {
        return stragegy.isAdjacent(this, other);
    }

    public boolean isPartialOverlapping(TimeRange other, PartialOverlappingTimeRangeComparisonStragegy stragegy) {
        return false; // TODO

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.startDateTime);
        hash = 19 * hash + Objects.hashCode(this.endDateTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeRange)) {
            return false;
        }

        TimeRange other = (TimeRange) obj;
        return Objects.equals(this.startDateTime, other.startDateTime)
                && Objects.equals(this.endDateTime, other.endDateTime);
    }
}

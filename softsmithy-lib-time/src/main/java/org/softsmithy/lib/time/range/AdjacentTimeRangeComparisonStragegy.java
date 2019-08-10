/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.time.range;

import org.softsmithy.lib.time.TimeRange;

/**
 *
 * @author puce
 */
public enum AdjacentTimeRangeComparisonStragegy {
    BEFORE {
        @Override
        public boolean isAdjacent(TimeRange current, TimeRange other) {
            return other.getEndDateTime().equals(current.getStartDateTime());
        }
    },
    AFTER {
        @Override
        public boolean isAdjacent(TimeRange current, TimeRange other) {
            return current.getEndDateTime().equals(other.getStartDateTime());
        }
    },
    BEFORE_OR_AFTER {
        @Override
        public boolean isAdjacent(TimeRange current, TimeRange other) {
            return BEFORE.isAdjacent(current, other) || AFTER.isAdjacent(current, other);
        }
    };

    public abstract boolean isAdjacent(TimeRange current, TimeRange other);
}
